package amc.controller.share;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.DataUtil;
import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.Appointment;
import amc.model.entity.Comment;
import amc.model.entity.Customer;
import amc.model.entity.Doctor;
import amc.model.entity.Employee;
import amc.model.entity.Password;
import amc.model.entity.User;
import amc.model.entity.UserAuth;
import amc.view.share.ProfilePanel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ProfileCtl extends AbstractSubCtl {
    private final ProfilePanel viewProfile = new ProfilePanel();
    private User target;
    private final ProfileNode node;

    public ProfileCtl(AmcCtl ROOT, User target, ProfileNode node) {
        super(ROOT);
        this.target = target;
        this.node   = node;
        showInit();
        if (node.isEditLogout)      editLogoutProc();
        if (node.isSendComment)     sendCmtProc();
        if (node.isShowEmpComment)  empCommentProc();
        if (node.isShowCusFeedback) cusFeedbackProc();
    }

    private void showInit() {
        viewProfile.showInit(node);
        viewProfile.renderDetail(target);
    }

    private void refreshSendCmt() {
        if (!(getROOT().getCurrentUser() instanceof Customer currentCus)) return;
        List<Comment> sendLs = Db.Comment.select(-1, model -> {
            boolean isSelect = model.getTargetId().equals(target.getId()) &&
                model.getAppointment().getCustomerId().equals(currentCus.getId());

            if (isSelect) model.getAppointment().setCustomer(currentCus);
            return isSelect;
        });
        viewProfile.renderSendCmtList(sendLs);
    }

    private void refreshEmpComment() {
        List<Comment> cmtLs = Db.Comment.select(-1, model -> {
            return model.getTargetId().equals(target.getId());
        });
        viewProfile.renderCommentList(cmtLs);
    }

    private void refreshCusFeedback() {
        List<Appointment> apptLs = Db.Appointment.select(-1, model -> {
            return model.getCustomerId().equals(target.getId()) &&
                model.getDoctorId() != null &&
                model.getFeedback() != null;
        });
        viewProfile.renderFeedbackList(apptLs);
    }

    private <T> void addOpenApptListener(JList<T> listComp) {
        listComp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() != 2) return;
                T selected = listComp.getSelectedValue();

                Appointment selectedAppt = switch (selected) {
                    case Comment cmt -> cmt.getAppointment();
                    case Appointment appt -> appt;
                    default -> null;
                };
                if (selectedAppt == null) return;

                AppointmentCtl apptCtl = new AppointmentCtl(
                    getROOT(), selectedAppt, node.nextApptNode
                );
                apptCtl.startView();
            }
        });
    }

    private void editLogoutProc() {
        viewProfile.btnEdit.addActionListener((ActionEvent evt) -> {
            viewProfile.initComboDept(Db.Department.select(-1, model->true));
            viewProfile.renderEditDialog(target);
        });

        viewProfile.btnEditConfirm.addActionListener((ActionEvent evt) -> {
            ProfilePanel.EditContext editCtx = viewProfile.getEditContext();
            boolean kEmail   = true;
            boolean kContact = !DataUtil.validContact(editCtx.contact());
            boolean kLicense = (target instanceof Doctor) && !DataUtil.validLicense(editCtx.license());
            if (!editCtx.email().isEmpty()) {
                List<UserAuth> authLs = Db.UserAuth.select(1, DbMan.checkUserRole(editCtx.email()));
                kEmail = !authLs.isEmpty();
            }
            viewProfile.clearEditECL(kEmail, kContact, kLicense);

            boolean enableUpdate = true;
            if (editCtx.userName().isEmpty() || kEmail || kContact || kLicense) {
                enableUpdate = false;
                JOptionPane.showMessageDialog(viewProfile,
                    "Please confirm your input value.",
                    "Failed Edit",
                    JOptionPane.WARNING_MESSAGE
                );
            }

            if (!editCtx.newPassword().equals(editCtx.confirmPassword())) {
                enableUpdate = false;
                JOptionPane.showMessageDialog(viewProfile,
                    "Please enter the new password again.",
                    "Incorrect Password",
                    JOptionPane.WARNING_MESSAGE
                );
            }
            if (!enableUpdate) return;

            Db.UserAuth.update(1, DbMan.checkUserRole(target.getEmail()), (UserAuth model) -> {
                model.setEmail(editCtx.email());
                if (editCtx.newPassword() != null && !editCtx.newPassword().isEmpty()) {
                    model.setPassword(Password.build(editCtx.newPassword()));
                }
                return model;
            });
            target.getRole().getHandle().update(1, DbMan.checkById(target.getId()), model -> {
                model.setUserName(editCtx.userName());
                model.setDateOfBirth(editCtx.dateOfBirth());
                model.setGender(editCtx.gender());
                model.setEmail(editCtx.email());
                model.setContact(editCtx.contact());
                if (model instanceof Employee employee) {
                    employee.setDepartmentId(editCtx.department().getId());
                    employee.setDepartment(editCtx.department());
                }
                if (model instanceof Doctor doctor) {
                    doctor.setLicense(editCtx.license());
                }
                target = model;
                return model;
            });
            viewProfile.clearEditDialog();
            viewProfile.renderDetail(target);
        });

        viewProfile.btnLogout.addActionListener((ActionEvent evt) -> {
            getROOT().setCurrentUser(null);
            getROOT().UserChange.fire();
            getROOT().popPage(viewProfile);
        });
    }

    private void sendCmtProc() {
        refreshSendCmt();
        viewProfile.btnSend.addActionListener((ActionEvent evt) -> {
            ProfilePanel.SendCmtContext sendCtx = viewProfile.getSendCmtCtx();
            if (sendCtx.content() == null) {
                JOptionPane.showMessageDialog(viewProfile,
                    "Cannot send empty comment. Please write something.",
                    "Invalid Content",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            Comment newCmt = new Comment(
                Db.Comment.newId(),
                node.getRecordedAppt(),
                target.getId(),
                sendCtx.rating(),
                sendCtx.content()
            );
            Db.Comment.insert(List.of(newCmt));
        });
        if (node.nextApptNode != null) addOpenApptListener(viewProfile.lstSendCmt);
        Db.Comment.addTblListener(() -> refreshSendCmt());
    }

    private void empCommentProc() {
        refreshEmpComment();
        if (node.nextApptNode != null) addOpenApptListener(viewProfile.lstComment);
        Db.Comment.addTblListener(() -> refreshEmpComment());
    }

    private void cusFeedbackProc() {
        refreshCusFeedback();
        if (node.nextApptNode != null) addOpenApptListener(viewProfile.lstFeedback);
        Db.Appointment.addTblListener(() -> refreshCusFeedback());
    }

    public void startView() {
        getROOT().pushPage(viewProfile);
    }

    public static void main(String[] args) {
        Doctor gan = Db.Doctor.getById("DOC-001");
        Customer con = Db.Customer.getById("050313690069");
        var amc = new AmcCtl();
        amc.setCurrentUser(con);
        amc.startView();
        var pro = new ProfileCtl(amc, gan, JumpTree.CusAppt.docProfileNode);
        pro.startView();
    }
}

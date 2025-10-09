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
import amc.model.entity.Department;
import amc.model.entity.User;
import amc.model.entity.UserAuth;
import amc.view.share.ProfilePanel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
        viewProfile.picUndo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                getROOT().popPage(viewProfile);
            }
        });
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
        Customer currentCus = getROOT().getCurrentUser() instanceof Customer ? 
            (Customer) getROOT().getCurrentUser() : null;
        
        List<Comment> cmtLs = Db.Comment.select(-1, model -> {
            boolean isTarget = model.getTargetId().equals(target.getId());
            // 如果是客户视角且同时启用了发送评论功能，排除自己发送的评论（避免重复显示）
            if (currentCus != null && node.isSendComment) {
                return isTarget && !model.getAppointment().getCustomerId().equals(currentCus.getId());
            }
            return isTarget;
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

            String newName     = editCtx.userName();
            String newEmail    = editCtx.email();
            String newContact  = editCtx.contact();
            String newLicense  = editCtx.license();
            String newPassword = editCtx.newPassword();
            String confirmPass = editCtx.confirmPassword();

            boolean invalidName = newName == null || newName.isBlank();

            boolean emailChanged = !newEmail.equalsIgnoreCase(target.getEmail());
            boolean invalidEmail = newEmail.isEmpty();
            if (!invalidEmail && emailChanged) {
                List<UserAuth> authLs = Db.UserAuth.select(1, DbMan.checkUserAuth(newEmail));
                invalidEmail = !authLs.isEmpty();
            }

            boolean invalidContact = !DataUtil.validContact(newContact);
            boolean invalidLicense = (target instanceof Doctor) && !DataUtil.validLicense(newLicense);
            boolean passwordMismatch = !newPassword.equals(confirmPass);

            viewProfile.clearEditECL(invalidEmail, invalidContact, invalidLicense);

            List<String> errors = new ArrayList<>();
            if (invalidName)       errors.add("Please enter your name.");
            if (invalidEmail)      errors.add(emailChanged ? "Email is already in use." : "Please enter a valid email.");
            if (invalidContact)    errors.add("Please enter a valid contact number.");
            if (invalidLicense)    errors.add("Please enter a valid medical license.");
            if (passwordMismatch)  errors.add("Please enter the new password again.");

            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(
                    viewProfile,
                    String.join("\n", errors),
                    "Failed Edit",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Db.UserAuth.update(1, DbMan.checkUserAuth(target.getEmail()), (UserAuth model) -> {
                model.setEmail(newEmail);
                if (newPassword != null && !newPassword.isEmpty()) {
                    model.setPassword(Password.build(newPassword));
                }
                return model;
            });
            target.getRole().getHandle().update(1, DbMan.checkById(target.getId()), model -> {
                model.setUserName(newName);
                model.setDateOfBirth(editCtx.dateOfBirth());
                model.setGender(editCtx.gender());
                model.setEmail(newEmail);
                model.setContact(newContact);
                if (model instanceof Employee employee) {
                    Department dept = editCtx.department();
                    if (dept != null) {
                        employee.setDepartmentId(dept.getId());
                        employee.setDepartment(dept);
                    }
                }
                if (model instanceof Doctor doctor) {
                    doctor.setLicense(newLicense);
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
            getROOT().clearToMenu();
        });
    }

    private void sendCmtProc() {
        refreshSendCmt();
        viewProfile.btnSend.addActionListener((ActionEvent evt) -> {
            ProfilePanel.SendCmtContext sendCtx = viewProfile.getSendCmtCtx();
            if (sendCtx.content() == null || sendCtx.content().trim().isEmpty()) {
                JOptionPane.showMessageDialog(viewProfile,
                    "Cannot send empty comment. Please write something.",
                    "Invalid Content",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            String apptId = node.getRecordedAppt();
            if (apptId == null || apptId.isEmpty()) {
                JOptionPane.showMessageDialog(viewProfile,
                    "No appointment associated. Cannot send comment.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            Comment newCmt = new Comment(
                Db.Comment.newId(),
                apptId,
                target.getId(),
                sendCtx.rating(),
                sendCtx.content()
            );
            Db.Comment.insert(List.of(newCmt));
            
            // Clear the text field after successful send
            viewProfile.clearSendCmtField();
            
            // Refresh the list
            refreshSendCmt();
            if(node.isShowEmpComment){
                refreshEmpComment();
            }
        });
        if (node.nextApptNode != null) addOpenApptListener(viewProfile.lstSendCmt);
    }

    private void empCommentProc() {
        refreshEmpComment();
        if (node.nextApptNode != null) addOpenApptListener(viewProfile.lstComment);
    }

    private void cusFeedbackProc() {
        refreshCusFeedback();
        if (node.nextApptNode != null) addOpenApptListener(viewProfile.lstFeedback);
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

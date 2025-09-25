package amc.controller.share;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.controller.share.ProfileNode;
import amc.model.db_impl.Db;
import amc.model.entity.Doctor;
import amc.model.entity.Employee;
import amc.model.entity.Role;
import amc.model.entity.User;
import amc.view.share.ProfilePanel;

public class ProfileCtl extends AbstractSubCtl {
    private final ProfilePanel viewProfile = new ProfilePanel();
    private final User target;
    private final ProfileNode node;

    public ProfileCtl(AmcCtl ROOT, User target, ProfileNode node) {
        super(ROOT);
        this.target = target;
        this.node   = node;
        showInit();
        if (node.isEditLogout)      editLogoutProc();
        if (node.isSendComment)     sendCmtProc();
        if (node.isShowCusFeedback) cusFeedbackProc();
        if (node.isShowEmpComment)  empCommentProc();
    }

    private void showInit() {
        viewProfile.showInit(node);
        viewProfile.renderDetail(target);

        String department = null;
        String license    = null;
        if (target.getRole() == Role.Doctor || target.getRole() == Role.Staff) {
            String deptId = ((Employee) target).getDepartmentId();
            department = Db.Department.getById(deptId).getDepartmentName();
        }
        if (target.getRole() == Role.Doctor) {
            license = ((Doctor) target).getLicense();
        }
        viewProfile.renderEmpDetail(department, license);
    }

    private void editLogoutProc() {
    }

    private void sendCmtProc() {
    }

    private void cusFeedbackProc() {
    }

    private void empCommentProc() {
    }

    public void startView() {
        getROOT().pushPage(viewProfile);
    }
}

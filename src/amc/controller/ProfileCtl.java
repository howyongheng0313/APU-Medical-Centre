package amc.controller;

import amc.model.entity.Department;
import amc.model.entity.Doctor;
import amc.model.entity.Employee;
import amc.model.entity.Role;
import amc.model.entity.User;
import amc.view.share.ProfilePanel;

public class ProfileCtl extends AbstractSubCtl {
    private final ProfilePanel viewProfile = new ProfilePanel();

    public ProfileCtl(AmcCtl ROOT) {
        super(ROOT);
        User loggedUser  = getROOT().getCurrentUser();
        ProfileNode node = loggedUser.getRole().getProfileNode();
        viewProfile.renderDetail(loggedUser);

        String department = null;
        String license    = null;
        if (loggedUser.getRole() == Role.Doctor || loggedUser.getRole() == Role.Staff) {
            String deptId = ((Employee) loggedUser).getDepartmentId();
            department = Department.getById(deptId).getDepartmentName();
        }
        if (loggedUser.getRole() == Role.Doctor) {
            license = ((Doctor) loggedUser).getLicense();
        }
        viewProfile.renderEmpDetail(department, license);

        // NEXT STEP
    }

//    public ProfileCtl(AmcCtl ROOT, User looker, User owner, ProfileNode node) {
//        super(ROOT);
//    }
//
//    public ProfileCtl(AmcCtl ROOT, User looker, User owner, ProfileNode node) {
//        super(ROOT);
//    }
//
//    public ProfileCtl(AmcCtl ROOT, User looker, User owner, ProfileNode node) {
//        super(ROOT);
//    }
//
//    public ProfileCtl(AmcCtl ROOT, User looker, User owner, ProfileNode node) {
//        super(ROOT);
//    }

    public void startView() {
        getROOT().pushPage(viewProfile);
    }
}

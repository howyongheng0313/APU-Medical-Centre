package amc.model.entity;

import amc.controller.AmcCtl;
import amc.controller.CustomerCtl;
import amc.controller.DoctorCtl;
import amc.controller.share.JumpTree;
import amc.controller.ManagerCtl;
import amc.controller.share.ProfileNode;
import amc.controller.StaffCtl;
import amc.controller.UserCtl;
import amc.model.DbWithId;
import amc.model.db_impl.Db;
import java.time.LocalDate;
import java.util.function.Function;

public enum Role {
    Customer(Db.Customer, CustomerCtl::new, JumpTree.UsrSelf) {
        @Override
        public User newUsr(String id, String name, LocalDate dateOfBirth, User.Gender gender, String email, String contact, String departmentId, String license) {
            return new Customer(id, name, dateOfBirth, gender, email, contact);
        }
    },
    Manager (Db.Manager , ManagerCtl::new , JumpTree.UsrSelf) {
        @Override
        public User newUsr(String id, String name, LocalDate dateOfBirth, User.Gender gender, String email, String contact, String departmentId, String license) {
            return new Manager(id, name, dateOfBirth, gender, email, contact);
        }
    },
    Doctor  (Db.Doctor  , DoctorCtl::new  , JumpTree.DocSelf) {
        @Override
        public User newUsr(String id, String name, LocalDate dateOfBirth, User.Gender gender, String email, String contact, String departmentId, String license) {
            return new Staff(id, name, dateOfBirth, gender, email, contact, departmentId);
        }
    },
    Staff   (Db.Staff   , StaffCtl::new   , JumpTree.StfSelf) {
        @Override
        public User newUsr(String id, String name, LocalDate dateOfBirth, User.Gender gender, String email, String contact, String departmentId, String license) {
            return new Doctor(id, name, dateOfBirth, gender, email, contact, departmentId, license);
        }
    };

    private final DbWithId<? extends User> handle;
    private final Function<AmcCtl, ? extends UserCtl> ctlConst;
    private final ProfileNode profileNode;

    Role(
        DbWithId<? extends User> handle,
        Function<AmcCtl, ? extends UserCtl> ctlConst,
        ProfileNode profileNode
    ) {
        this.handle      = handle;
        this.ctlConst    = ctlConst;
        this.profileNode = profileNode;
    }

    public DbWithId<? extends User> getHandle() { return handle; }
    public ProfileNode getProfileNode() { return profileNode; }

    public UserCtl newCtl(AmcCtl root) {
        return ctlConst.apply(root);
    }

    public abstract User newUsr(
        String id,
        String name,
        LocalDate dateOfBirth,
        User.Gender gender,
        String email,
        String contact,
        String departmentId,
        String license
    );
}

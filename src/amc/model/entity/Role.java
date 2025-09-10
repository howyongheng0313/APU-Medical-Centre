package amc.model.entity;

import amc.controller.AmcCtl;
import amc.controller.CustomerCtl;
import amc.controller.DoctorCtl;
import amc.controller.JumpTree;
import amc.controller.ManagerCtl;
import amc.controller.ProfileNode;
import amc.controller.StaffCtl;
import amc.controller.UserCtl;
import amc.model.DbHandle;
import amc.model.db_impl.Db;
import java.util.function.Function;

public enum Role {
    Customer(Db.Customer, CustomerCtl::new, JumpTree.UsrSelf),
    Manager (Db.Manager , ManagerCtl::new , JumpTree.UsrSelf),
    Doctor  (Db.Doctor  , DoctorCtl::new  , JumpTree.DocSelf),
    Staff   (Db.Staff   , StaffCtl::new   , JumpTree.StfSelf);

    private final DbHandle<? extends User> handle;
    private final Function<AmcCtl, ? extends UserCtl> ctlConst;
    private final ProfileNode profileNode;

    Role(
        DbHandle<? extends User> handle,
        Function<AmcCtl, ? extends UserCtl> ctlConst,
        ProfileNode profileNode
    ) {
        this.handle      = handle;
        this.ctlConst    = ctlConst;
        this.profileNode = profileNode;
    }

    public DbHandle<? extends User> getHandle() { return handle; }
    public ProfileNode getProfileNode() { return profileNode; }

    public UserCtl newCtl(AmcCtl root) {
        return ctlConst.apply(root);
    }
}

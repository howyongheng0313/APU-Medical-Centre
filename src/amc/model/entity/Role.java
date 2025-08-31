package amc.model.entity;

import amc.controller.AmcCtl;
import amc.controller.CustomerCtl;
import amc.controller.DoctorCtl;
import amc.controller.ManagerCtl;
import amc.controller.StaffCtl;
import amc.controller.UserCtl;
import amc.model.DbHandle;
import amc.model.db_impl.Db;
import java.util.function.Function;

public enum Role {
    Customer(Db.Customer, CustomerCtl::new),
    Manager (Db.Manager , ManagerCtl::new),
    Doctor  (Db.Doctor  , DoctorCtl::new),
    Staff   (Db.Staff   , StaffCtl::new);

    private final DbHandle<? extends User> handle;
    private final Function<AmcCtl, ? extends UserCtl> ctlConst;

    Role(DbHandle<? extends User> handle, Function<AmcCtl, ? extends UserCtl> ctlConst) {
        this.handle   = handle;
        this.ctlConst = ctlConst;
    }

    public DbHandle<? extends User> getHandle() { return handle; }

    public UserCtl newCtl(AmcCtl root) {
        return ctlConst.apply(root);
    }
}

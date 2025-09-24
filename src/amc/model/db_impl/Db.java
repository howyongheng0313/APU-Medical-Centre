package amc.model.db_impl;

import amc.model.DbHandle;
import amc.model.DbWithId;
import amc.model.entity.*;
import java.nio.file.Path;

public final class Db {
    public static final DbWithId<Customer>     Customer     = new DbWithId<>(
        Adapt.Customer    , Path.of("./database/Customer.txt")   , ""
    );
    public static final DbWithId<Manager>      Manager      = new DbWithId<>(
        Adapt.Manager     , Path.of("./database/Manager.txt")    , "MNG"
    );
    public static final DbWithId<Staff>        Staff        = new DbWithId<>(
        Adapt.Staff       , Path.of("./database/Staff.txt")      , "STF"
    );
    public static final DbWithId<Doctor>       Doctor       = new DbWithId<>(
        Adapt.Doctor      , Path.of("./database/Doctor.txt")     , "DOC"
    );
    public static final DbWithId<Department>   Department   = new DbWithId<>(
        Adapt.Department  , Path.of("./database/Department.txt") , "DPT"
    );
    public static final DbWithId<Service>      Service      = new DbWithId<>(
        Adapt.Service     , Path.of("./database/Service.txt")    , "SVC"
    );
    public static final DbWithId<Medicine>     Medicine     = new DbWithId<>(
        Adapt.Medicine    , Path.of("./database/Medicine.txt")   , "MDC"
    );
    public static final DbWithId<Appointment>  Appointment  = new DbWithId<>(
        Adapt.Appointment , Path.of("./database/Appointment.txt"), "APT"
    );
    public static final DbWithId<Comment>      Comment      = new DbWithId<>(
        Adapt.Comment     , Path.of("./database/Comment.txt")    , "CMT"
    );
    public static final DbWithId<Payment>      Payment      = new DbWithId<>(
        Adapt.Payment     , Path.of("./database/Payment.txt")    , "PAY"
    );
    public static final DbHandle<ApptService>  ApptService  = new DbHandle<>(
        Adapt.ApptService , Path.of("./database/ApptService.txt")
    );
    public static final DbHandle<ApptMedicine> ApptMedicine = new DbHandle<>(
        Adapt.ApptMedicine, Path.of("./database/ApptMedicine.txt")
    );
    public static final DbHandle<UserAuth>     UserAuth     = new DbHandle<>(
        Adapt.UserAuth    , Path.of("./database/UserAuth.txt")
    );

    private Db() {}
}

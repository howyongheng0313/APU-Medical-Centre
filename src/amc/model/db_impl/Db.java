package amc.model.db_impl;

import amc.model.DbHandle;
import amc.model.entity.*;
import java.nio.file.Path;

public final class Db {
    public static final DbHandle<Customer>     Customer     = new DbHandle<>(Adapt.Customer    , Path.of("./database/Customer.txt"));
    public static final DbHandle<Manager>      Manager      = new DbHandle<>(Adapt.Manager     , Path.of("./database/Manager.txt"));
    public static final DbHandle<Staff>        Staff        = new DbHandle<>(Adapt.Staff       , Path.of("./database/Staff.txt"));
    public static final DbHandle<Doctor>       Doctor       = new DbHandle<>(Adapt.Doctor      , Path.of("./database/Doctor.txt"));
    public static final DbHandle<Department>   Department   = new DbHandle<>(Adapt.Department  , Path.of("./database/Department.txt"));
    public static final DbHandle<Service>      Service      = new DbHandle<>(Adapt.Service     , Path.of("./database/Service.txt"));
    public static final DbHandle<Medicine>     Medicine     = new DbHandle<>(Adapt.Medicine    , Path.of("./database/Medicine.txt"));
    public static final DbHandle<Appointment>  Appointment  = new DbHandle<>(Adapt.Appointment , Path.of("./database/Appointment.txt"));
    public static final DbHandle<Comment>      Comment      = new DbHandle<>(Adapt.Comment     , Path.of("./database/Comment.txt"));
    public static final DbHandle<ApptService>  ApptService  = new DbHandle<>(Adapt.ApptService , Path.of("./database/ApptService.txt"));
    public static final DbHandle<ApptMedicine> ApptMedicine = new DbHandle<>(Adapt.ApptMedicine, Path.of("./database/ApptMedicine.txt"));
    public static final DbHandle<Payment>      Payment      = new DbHandle<>(Adapt.Payment     , Path.of("./database/Payment.txt"));
    public static final DbHandle<UserAuth>     UserAuth     = new DbHandle<>(Adapt.UserAuth    , Path.of("./database/UserAuth.txt"));
    private Db() {}
}

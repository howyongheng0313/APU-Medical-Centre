package amc.model.db_impl;

public final class Adapt {
    public static final CustomerAdapt     Customer     = new CustomerAdapt();
    public static final ManagerAdapt      Manager      = new ManagerAdapt();
    public static final StaffAdapt        Staff        = new StaffAdapt();
    public static final DoctorAdapt       Doctor       = new DoctorAdapt();
    public static final DepartmentAdapt   Department   = new DepartmentAdapt();
    public static final ServiceAdapt      Service      = new ServiceAdapt();
    public static final MedicineAdapt     Medicine     = new MedicineAdapt();
    public static final AppointmentAdapt  Appointment  = new AppointmentAdapt();
    public static final CommentAdapt      Comment      = new CommentAdapt();
    public static final ApptServiceAdapt  ApptService  = new ApptServiceAdapt();
    public static final ApptMedicineAdapt ApptMedicine = new ApptMedicineAdapt();
    public static final PaymentAdapt      Payment      = new PaymentAdapt();
    public static final UserAuthAdapt     UserAuth     = new UserAuthAdapt();
    public static final NoneAdapt         None         = new NoneAdapt();
    private Adapt() {}
}

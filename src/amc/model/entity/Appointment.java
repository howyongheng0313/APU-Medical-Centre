package amc.model.entity;

import amc.model.db_impl.Db;
import java.time.LocalDateTime;

public class Appointment extends WithId {
    public enum Status {
        Pending  ( 0, "/amc/image/status_pending_30.png"),
        Booked   ( 1, "/amc/image/status_booked_30.png"),
        EndCons  ( 2, "/amc/image/status_end_con_30.png"),
        Completed( 3, "/amc/image/status_completed.png"),
        Cancelled(-1, "/amc/image/status_rejected.png");

        private final int step;
        private final String iconPath;

        private Status(int step, String iconPath) {
            this.step = step;
            this.iconPath = iconPath;
        }

        public static Status valueOf(int step) {
            for (Status s: Status.values()) {
                if (s.getStep() == step) return s;
            }
            return Status.Pending;
        }

        public int getStep() { return step; }
        public String getIconPath() { return iconPath; }
    }

    private String customerId;
    private String doctorId = null;
    private String staffId  = null;
    private String departmentId;
    private LocalDateTime dateTime;
    private Status status;
    private String feedback = null;

    private Customer   customer   = null;
    private Doctor     doctor     = null;
    private Staff      staff      = null;
    private Department department = null;
    
    public Appointment(
        String id,
        String customerId,
        String departmentId,
        LocalDateTime dateTime,
        Status status
    ) {
        super(id);
        this.customerId = customerId;
        this.departmentId = departmentId;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getCustomerId() { return customerId; }
    public String getDoctorId() { return doctorId; }
    public String getStaffId() { return staffId; }
    public String getDepartmentId() { return departmentId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public Status getStatus() { return status; }
    public String getFeedback() { return feedback; }

    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setStatus(Status status) { this.status = status; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public Customer getCustomer() {
        if (customer == null) customer = Db.Customer.getById(customerId);
        return customer;
    }

    public Doctor getDoctor() {
        if (doctor == null) doctor = Db.Doctor.getById(doctorId);
        return doctor;
    }

    public Staff getStaff() {
        if (staff == null) staff = Db.Staff.getById(staffId);
        return staff;
    }

    public Department getDepartment() {
        if (department == null) department = Db.Department.getById(departmentId);
        return department;
    }

    public void setCustomer(Customer customer) {
        if (this.customerId == null || !customerId.equals(customer.getId())) return;
        this.customer = customer;
    }

    public void setDoctor(Doctor doctor) {
        if (this.doctorId == null || !doctorId.equals(doctor.getId())) return;
        this.doctor = doctor;
    }

    public void setStaff(Staff staff) {
        if (this.staffId == null || !staffId.equals(staff.getId())) return;
        this.staff = staff;
    }

    public void setDepartment(Department department) {
        if (departmentId == null || !departmentId.equals(department.getId())) return;
        this.department = department;
    }
    public static void main(String[] args) {
        System.out.println(Db.Appointment.getById("APT-002").getDoctor());
    }
}

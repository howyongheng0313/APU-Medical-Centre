package amc.model.entity;

import java.time.LocalDateTime;

public class Appointment {
    public enum Status {
        Pending(0),
        Booked(1),
        EndCons(2),
        Completed(3),
        Cancelled(-1);

        private final int step;
        private Status(int step) { this.step = step; }
        public static Status valueOf(int step) {
            for (Status s: Status.values()) {
                if (s.getStep() == step) return s;
            }
            return Status.Pending;
        }
        public int getStep() { return step; }
    }

    private String appointmentId;
    private String customerId;
    private String doctorId = null;
    private String staffId = null;
    private String departmentId;
    private LocalDateTime dateTime;
    private Status status;
    private String feedback = null;
    
    public Appointment(
        String appointmentId,
        String customerId,
        String departmentId,
        LocalDateTime dateTime,
        Status status) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.departmentId = departmentId;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getCustomerId() { return customerId; }
    public String getDoctorId() { return doctorId; }
    public String getStaffId() { return staffId; }
    public String getDepartmentId() { return departmentId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public Status getStatus() { return status; }
    public String getFeedback() { return feedback; }

    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setStatus(Status status) { this.status = status; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}

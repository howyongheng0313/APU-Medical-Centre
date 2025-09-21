package amc.model.entity;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private final String appointmentId;
    private final String customerId;
    private final String customerName;
    private final String doctorId;
    private final String doctorName;
    private final String staffId;
    private final String staffName;
    private final String departmentId;
    private final String departmentName;
    private final LocalDateTime dateTime;
    private final Appointment.Status status;
    private final String feedback;
    
    public AppointmentDTO(
        String appointmentId,
        String customerId,
        String customerName,
        String doctorId,
        String doctorName,
        String staffId,
        String staffName,
        String departmentId,
        String departmentName,
        LocalDateTime dateTime,
        Appointment.Status status,
        String feedback
    ) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.staffId = staffId;
        this.staffName = staffName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.dateTime = dateTime;
        this.status = status;
        this.feedback = feedback;
    }
    
    public String getAppointmentId() { return appointmentId; }
    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getDoctorId() { return doctorId; }
    public String getDoctorName() { return doctorName; }
    public String getStaffId() { return staffId; }
    public String getStaffName() { return staffName; }
    public String getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }
    public LocalDateTime getDateTime() { return dateTime; }
    public Appointment.Status getStatus() { return status; }
    public String getFeedback() { return feedback; }
}

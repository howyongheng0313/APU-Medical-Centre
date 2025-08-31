package amc.model.entity;

public class ApptService {
    private String appointmentId;
    private String serviceId;
    private double fee;

    public ApptService(
        String appointmentId,
        String serverId,
        double fee) {
        this.appointmentId = appointmentId;
        this.serviceId = serverId;
        this.fee = fee;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getServiceId() { return serviceId; }
    public double getFee() { return fee; }

    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public void setServiceId(String serviceId) { this.serviceId = serviceId; }
    public void setFee(double fee) { this.fee = fee; }
}

package amc.model.entity;

import amc.model.db_impl.Db;

public class ApptService {
    private String appointmentId;
    private String serviceId;
    private double fee;

    private Appointment appointment = null;
    private Service     service     = null;

    public ApptService(
        String appointmentId,
        String serviceId,
        double fee
    ) {
        this.appointmentId = appointmentId;
        this.serviceId = serviceId;
        this.fee = fee;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getServiceId() { return serviceId; }
    public double getFee() { return fee; }

    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public void setServiceId(String serviceId) { this.serviceId = serviceId; }
    public void setFee(double fee) { this.fee = fee; }

    public Appointment getAppointment() {
        if (appointment == null) appointment = Db.Appointment.getById(appointmentId);
        return appointment;
    }

    public Service getService() {
        if (service == null) service = Db.Service.getById(serviceId);
        return service;
    }

    public void setAppointment(Appointment appointment) {
        if (appointmentId == null || !appointmentId.equals(appointment.getId())) return;
        this.appointment = appointment;
    }

    public void setService(Service service) {
        if (serviceId == null || !serviceId.equals(service.getId())) return;
        this.service = service;
    }
}

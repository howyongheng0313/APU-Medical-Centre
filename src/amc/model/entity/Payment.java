package amc.model.entity;

import amc.model.db_impl.Db;

public class Payment extends WithId {
    public enum Method { Cash, CreditDebit, EWallet; }

    private String appointmentId;
    private Method paymentMethod;

    private Appointment appointment = null;

    public Payment(
        String id,
        String appointmentId,
        Method paymentMethod
    ) {
        super(id);
        this.appointmentId = appointmentId;
        this.paymentMethod = paymentMethod;
    }

    public String getAppointmentId() { return appointmentId; }
    public Method getPaymentMethod() { return paymentMethod; }

    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public void setPaymentMethod(Method paymentMethod) { this.paymentMethod = paymentMethod; }

    public Appointment getAppointment() {
        if (appointment == null) appointment = Db.Appointment.getById(appointmentId);
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        if (appointmentId == null || !appointmentId.equals(appointment.getId())) return;
        this.appointment = appointment;
    }
}

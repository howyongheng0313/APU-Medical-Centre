package amc.model.entity;

public class Payment {
    public enum Method { Cash, CreditDebit, EWallet; }

    private String paymentId;
    private String appointmentId;
    private Method paymentMethod;

    public Payment(
        String paymentId,
        String appointmentId,
        Method paymentMethod) {
        this.paymentId = paymentId;
        this.appointmentId = appointmentId;
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentId() { return paymentId; }
    public String getAppointmentId() { return appointmentId; }
    public Method getPaymentMethod() { return paymentMethod; }

    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public void setPaymentMethod(Method paymentMethod) { this.paymentMethod = paymentMethod; }
}

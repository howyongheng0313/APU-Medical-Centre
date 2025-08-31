package amc.model.entity;

public class ApptMedicine {
    private String appointmentId;
    private String medicineId;
    private double price;
    private int    quantity;

    public ApptMedicine(
        String appointmentId,
        String medicineId,
        double price,
        int    quantity) {
        this.appointmentId = appointmentId;
        this.medicineId = medicineId;
        this.price = price;
        this.quantity = quantity;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getMedicineId() { return medicineId; }
    public double getPrice() { return price; }
    public int    getQuantity() { return quantity; }

    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public void setMedicineId(String medicineId) { this.medicineId = medicineId; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

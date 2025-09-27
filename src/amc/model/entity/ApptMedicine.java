package amc.model.entity;

import amc.model.db_impl.Db;

public class ApptMedicine {
    private String appointmentId;
    private String medicineId;
    private double price;
    private int    quantity;

    private Appointment appointment = null;
    private Medicine    medicine    = null;

    public ApptMedicine(
        String appointmentId,
        String medicineId,
        double price,
        int    quantity
    ) {
        this.appointmentId = appointmentId;
        this.medicineId = medicineId;
        this.price = price;
        this.quantity = quantity;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getMedicineId() { return medicineId; }
    public double getPrice() { return price; }
    public int    getQuantity() { return quantity; }
    public double getTotalPrice() { return price * quantity; }

    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public void setMedicineId(String medicineId) { this.medicineId = medicineId; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Appointment getAppointment() {
        if (appointment == null) appointment = Db.Appointment.getById(appointmentId);
        return appointment;
    }

    public Medicine getMedicine() {
        if (medicine == null) medicine = Db.Medicine.getById(medicineId);
        return medicine;
    }

    public void setAppointment(Appointment appointment) {
        if (appointmentId == null || !appointmentId.equals(appointment.getId())) return;
        this.appointment = appointment;
    }

    public void setMedicine(Medicine medicine) {
        if (medicineId == null || !medicineId.equals(medicine.getId())) return;
        this.medicine = medicine;
    }
}

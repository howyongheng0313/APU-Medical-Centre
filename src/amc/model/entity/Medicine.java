package amc.model.entity;

public class Medicine {
    private String medicineId;
    private String medicineName;
    private double price;

    public Medicine(
        String medicineId,
        String medicineName,
        double price) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.price = price;
    }

    public String getMedicineId() { return medicineId; }
    public String getMedicineName() { return medicineName; }
    public double getPrice() { return price; }

    public void setMedicineId(String medicineId) { this.medicineId = medicineId; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public void setPrice(double price) { this.price = price; }
}

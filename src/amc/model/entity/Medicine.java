package amc.model.entity;

public class Medicine extends WithId {
    private String medicineName;
    private double price;

    public Medicine(
        String id,
        String medicineName,
        double price
    ) {
        super(id);
        this.medicineName = medicineName;
        this.price = price;
    }

    public String getMedicineName() { return medicineName; }
    public double getPrice() { return price; }

    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public void setPrice(double price) { this.price = price; }
}

package amc.model.entity;

public class Service {
    private String serviceId;
    private String serviceName;
    private double fee;
    private String departmentId;

    public Service(
        String serviceId,
        String serviceName,
        double fee,
        String departmentId) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.fee = fee;
        this.departmentId = departmentId;
    }

    public String getServiceId() { return serviceId; }
    public String getServiceName() { return serviceName; }
    public double getFee() { return fee; }
    public String getDepartmentId() { return departmentId; }

    public void setServiceId(String serviceId) { this.serviceId = serviceId; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public void setFee(double fee) { this.fee = fee; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
}

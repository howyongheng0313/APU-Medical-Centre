package amc.model.entity;

public class ServiceDTO {
    private final String serviceId;
    private final String serviceName;
    private final double fee;
    private final String departmentName;
    
    public ServiceDTO(String serviceId, String serviceName, double fee, String departmentName){
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.fee = fee;
        this.departmentName = departmentName;
    }   

    public String getServiceId() {return serviceId;}
    public String getServiceName() {return serviceName;}
    public double getFee() {return fee;}
    public String getDepartmentName() {return departmentName;}
}

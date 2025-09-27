package amc.model.entity;

import amc.model.db_impl.Db;

public class Service extends WithId {
    private String serviceName;
    private double fee;
    private String departmentId;

    private Department department = null;

    public Service(
        String id,
        String serviceName,
        double fee,
        String departmentId
    ) {
        super(id);
        this.serviceName = serviceName;
        this.fee = fee;
        this.departmentId = departmentId;
    }
    
    @Override
    public String toString() {
        return serviceName + " RM: " + fee + " Department ID: "+ departmentId;
    }

    public String getServiceName() { return serviceName; }
    public double getFee() { return fee; }
    public String getDepartmentId() { return departmentId; }

    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public void setFee(double fee) { this.fee = fee; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public Department getDepartment() {
        if (department == null) department = Db.Department.getById(departmentId);
        return department;
    }

    public void setDepartment(Department department) {
        if (departmentId == null || !departmentId.equals(department.getId())) return;
        this.department = department;
    }
}

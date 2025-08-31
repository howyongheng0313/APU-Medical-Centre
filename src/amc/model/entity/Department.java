package amc.model.entity;

public class Department {
    private String departmentId;
    private String departmentName;

    public Department(String departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public String getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }

    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
}

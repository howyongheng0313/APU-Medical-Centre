package amc.model.entity;

public class Department extends WithId {
    private String departmentName;

    public Department(String id, String departmentName) {
        super(id);
        this.departmentName = departmentName;
    }

    public String getDepartmentName() { return departmentName; }

    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
}

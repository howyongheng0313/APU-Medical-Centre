package amc.model.entity;

import amc.model.DbMan;
import amc.model.db_impl.Db;
import java.util.List;

public class Department {
    private String departmentId;
    private String departmentName;

    public Department(String departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public static Department getById(String departmentId) {
        List<Department> deptLs = Db.Department.select(1, DbMan.getDepartment(departmentId));
        if (deptLs.isEmpty()) return null;

        return deptLs.getFirst();
    }

    public String getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }

    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
}

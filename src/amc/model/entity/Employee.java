package amc.model.entity;

import amc.model.db_impl.Db;
import java.time.LocalDate;

public abstract class Employee extends User {
    protected String departmentId;
    protected Department department = null;

    public Employee(
        String    id,
        String    userName,
        LocalDate dateOfBirth,
        Gender    gender,
        String    email,
        String    contact,
        String    departmentId
    ) {
        super(id, userName, dateOfBirth, gender, email, contact);
        this.departmentId = departmentId;
    }

    public String getDepartmentId() { return departmentId; }

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

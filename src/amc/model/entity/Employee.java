package amc.model.entity;

import java.time.LocalDate;

public abstract class Employee extends User {
    protected String departmentId;

    public Employee(
        String    userId,
        String    userName,
        LocalDate dateOfBirth,
        Gender    gender,
        String    email,
        String    contact,
        String    departmentId
    ) {
        super(userId, userName, dateOfBirth, gender, email, contact);
        this.departmentId = departmentId;
    }

    public String getDepartmentId() { return departmentId; }

    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
}

package amc.model.entity;

import java.time.LocalDate;

public class Doctor extends Employee {
    private String license;

    public Doctor(
        String userId,
        String userName,
        LocalDate dateOfBirth,
        Gender gender,
        String email,
        String contact,
        String departmentId,
        String license
    ) {
        super(userId, userName, dateOfBirth, gender, email, contact, departmentId);
        this.license = license;
    }



    @Override
    public Role getRole() { return Role.Doctor; }

    public String getLicense() { return license; }

    public void setLicense(String license) { this.license = license; }
    
    @Override
    public String getDepartmentId() { return departmentId; }
}

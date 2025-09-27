package amc.model.entity;

import java.time.LocalDate;

public class Staff extends Employee {
    public Staff(
        String id,
        String userName,
        LocalDate dateOfBirth,
        Gender gender,
        String email,
        String contact,
        String departmentId
    ) {
        super(id, userName, dateOfBirth, gender, email, contact, departmentId);
    }

    @Override
    public Role getRole() { return Role.Staff; }
}

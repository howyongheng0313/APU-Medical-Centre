package amc.model.entity;

import java.time.LocalDate;

public class Manager extends User {
    public Manager(
        String    id,
        String    userName,
        LocalDate dateOfBirth,
        Gender    gender,
        String    email,
        String    contact
    ) {
        super(id, userName, dateOfBirth, gender, email, contact);
    }

    @Override
    public Role getRole() { return Role.Manager; }
}

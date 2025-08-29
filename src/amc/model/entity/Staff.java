package amc.model.entity;

import java.time.LocalDate;

public class Staff extends Employee {
    public Staff(
        String userId,
        String userName,
        LocalDate dateOfBirth,
        Gender gender,
        String email,
        String contact) {
        super(userId, userName, dateOfBirth, gender, email, contact);
    }
}

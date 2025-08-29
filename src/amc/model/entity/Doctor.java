package amc.model.entity;

import java.time.LocalDate;

public class Doctor extends Employee {
    public Doctor(
        String userId,
        String userName,
        LocalDate dateOfBirth,
        Gender gender,
        String email,
        String contact) {
        super(userId, userName, dateOfBirth, gender, email, contact);
    }
}

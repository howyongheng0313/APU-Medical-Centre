package amc.model.entity;

import java.time.LocalDate;

public abstract class Employee extends User {
    public Employee(
        String    userId,
        String    userName,
        LocalDate dateOfBirth,
        Gender    gender,
        String    email,
        String    contact) {
        super(userId, userName, dateOfBirth, gender, email, contact);
    }
}

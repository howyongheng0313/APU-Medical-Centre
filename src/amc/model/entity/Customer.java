package amc.model.entity;

import java.time.LocalDate;

public class Customer extends User {
    public Customer(
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
    public Role getRole() { return Role.Customer; }
}

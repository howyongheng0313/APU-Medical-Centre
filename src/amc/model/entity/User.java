package amc.model.entity;

import java.time.LocalDate;

public abstract class User {
    public enum Gender { Male, Female; }

    protected String    userId;
    protected String    userName;
    protected LocalDate dateOfBirth;
    protected Gender    gender;
    protected String    email;
    protected String    contact;

    public User(
        String    userId,
        String    userName,
        LocalDate dateOfBirth,
        Gender    gender,
        String    email,
        String    contact) {
        this.userId      = userId;
        this.userName    = userName;
        this.dateOfBirth = dateOfBirth;
        this.gender      = gender;
        this.email       = email;
        this.contact     = contact;
    }

    public String    getUserId() { return userId; }
    public String    getUserName() { return userName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public Gender    getGender() { return gender; }
    public String    getEmail() { return email; }
    public String    getContact() { return contact; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setGender(Gender gender) { this.gender = gender; }
    public void setEmail(String email) { this.email = email; }
    public void setContact(String contact) { this.contact = contact; }
}

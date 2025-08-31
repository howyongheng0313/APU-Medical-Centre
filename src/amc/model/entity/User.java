package amc.model.entity;

import java.util.List;
import java.time.LocalDate;

import amc.model.DbMan;
import amc.model.db_impl.Db;

public abstract class User {
    public enum Gender { Male, Female; }

    public record LoginContext(String email, String password) {}

    public record SignupContext(
        String icNumber,
        String userName,
        String email,
        String contact,
        Gender gender
    ) {}

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

    public static User login(LoginContext loginCtx) {
        List<UserAuth> authLs = Db.UserAuth.select(1, DbMan.checkUserRole(loginCtx.email));
        if (authLs.isEmpty()) return null;

        UserAuth auth = authLs.getFirst();
        if (!auth.getPassword().verify(loginCtx.password)) return null;

        List<? extends User> userLs = auth.getRole().getHandle().select(1, DbMan.checkUser(loginCtx.email));
        return userLs.getFirst();
    }

    public abstract Role getRole();

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

package amc.model.entity;

public class UserAuth {
    private String   email;
    private Role     role;
    private Password password;
    private String plainTextPassword;

    public UserAuth(String email, Role role, Password password, String plainTextPassword) {
        this.email    = email;
        this.role     = role;
        this.password = password;
        this.plainTextPassword = plainTextPassword;
    }

    public String   getEmail() { return email; }
    public Role     getRole() { return role; }
    public Password getPassword() { return password; }
    public String getPlainTextPassword() { return plainTextPassword; }
    

    public void setEmail(String email) { this.email = email; }
    public void setRole(Role role) { this.role = role; }
    public void setPassword(Password password) { this.password = password; }
    public void setPlainTextPassword(String plainTextPassword) { this.plainTextPassword = plainTextPassword; }
}

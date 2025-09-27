package amc.model.entity;

public class UserAuth {
    private String   email;
    private Role     role;
    private Password password;

    public UserAuth(String email, Role role, Password password) {
        this.email    = email;
        this.role     = role;
        this.password = password;
    }

    public String   getEmail() { return email; }
    public Role     getRole() { return role; }
    public Password getPassword() { return password; }

    public void setEmail(String email) { this.email = email; }
    public void setRole(Role role) { this.role = role; }
    public void setPassword(Password password) { this.password = password; }
}

package amc.model.entity;

import amc.model.entity.User.Gender;
import java.time.LocalDate;

public class EmployeeDTO {
    private final String userId;
    private final String userName;
    private final LocalDate dateOfBirth;
    private final Gender gender;
    private final String email;
    private final String contact;
    private final String departmentName;
    private final Role role;
    private final String license;
    
    // Constructor for doctor
    public EmployeeDTO(
            String userId, 
            String userName, 
            LocalDate dateOfBirth, 
            Gender gender, 
            String email, 
            String contact, 
            String departmentName, 
            Role role, 
            String license
    ){
        this.userId = userId;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.contact = contact;
        this.departmentName = departmentName;
        this.role = role;
        this.license = license;
    }
    
    // Constructor for non-doctor
    public EmployeeDTO(
            String userId, 
            String userName, 
            LocalDate dateOfBirth, 
            Gender gender, 
            String email, 
            String contact, 
            String departmentName, 
            Role role
    ){
        this(userId, userName, dateOfBirth, gender, email, contact, departmentName, role, null);
    }

    public String getUserId() {return userId;}
    public String getUserName() {return userName;}
    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public Gender getGender() {return gender;}
    public String getEmail() {return email;}
    public String getContact() {return contact;}
    public String getDepartmentName() {return departmentName;}
    public Role getRole() {return role;}
    public String getLicense() {return license;} 
}

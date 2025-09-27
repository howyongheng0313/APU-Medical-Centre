package amc.controller.manager;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.*;
import amc.view.manager.EmployeesPanel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EmployeesCtl extends AbstractSubCtl {
    
    private final EmployeesPanel viewEmployees = new EmployeesPanel();
    
    public EmployeesCtl(AmcCtl ROOT){
        super(ROOT);
        setupEmployeeFeatures();
        loadAllEmployees();
    }
   
    // Setup employee management features
    private void setupEmployeeFeatures() {
        // Role filter search
        viewEmployees.addPropertyChangeListener("searchByRole", evt -> {
            String selectedRole = (String) viewEmployees.getSelectedRole();
            Role role = null;
            if ("Manager".equals(selectedRole)) role = Role.Manager;
            else if ("Doctor".equals(selectedRole)) role = Role.Doctor;
            else if ("Staff".equals(selectedRole)) role = Role.Staff;
            else if ("All".equals(selectedRole)) role = null;
            
            if (role != null) {
                loadEmployeesByRole(role);
            }else{
                loadAllEmployees();
            }
        });
        
        // Text search
        viewEmployees.addPropertyChangeListener("searchByText", evt -> {
            String searchText = viewEmployees.getSearchInput();
            if(searchText != null && !searchText.trim().isEmpty() && !searchText.equals("Search users")) {
                this.loadEmployeesBySearch(searchText);
            } else {
                this.loadAllEmployees();
            }
        });
        
        // Create employee
        viewEmployees.addPropertyChangeListener("createEmployee", evt -> {
            createEmployee();
        });
        
        // Update employee
        viewEmployees.addPropertyChangeListener("updateEmployee", evt -> {
            updateEmployee();
        });
        
        // Delete employee
        viewEmployees.addPropertyChangeListener("deleteEmployee", evt -> {
            deleteEmployee();
        });
    }
    
    // Load all employees
    private void loadAllEmployees() {
        try {
            var employees = this.getAllEmployees();
            viewEmployees.showEmployees(employees);
            viewEmployees.resetSearchField();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error loading employees: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Load employees by role
    private void loadEmployeesByRole(Role role) {
        try {
            var employees = this.getEmployeesByRole(role);
            viewEmployees.showEmployees(employees);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error loading employees by role: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Load employees by search text
    private void loadEmployeesBySearch(String searchText) {
        try {
            var employees = this.getEmployeesBySearch(searchText);
            viewEmployees.showEmployees(employees);
            viewEmployees.resetSearchField();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, 
                "Error searching employees: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Create new employee
    private void createEmployee() {
        try {
            Role selectedRole = viewEmployees.getCreateRole();
            if (selectedRole == null) {
                JOptionPane.showMessageDialog(
                    viewEmployees, "Please select a role",
                    "Validation Error", JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            switch (selectedRole) {
                case (Role.Manager) -> createManager();
                case (Role.Doctor) -> createDoctor();
                case (Role.Staff) -> createStaff();
                default -> {
                    JOptionPane.showMessageDialog(
                        viewEmployees, "Invalid role selected",
                        "Validation Error", JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error creating employee: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Create manager
    private void createManager() {
        String name = viewEmployees.getCreateName();
        LocalDate birthDate = viewEmployees.getCreateBirthDate();
        String genderStr = viewEmployees.getCreateGender();
        String email = viewEmployees.getCreateEmail();
        String contact = viewEmployees.getCreateContact();
        
        // Validation
        if (!validateUserInput(name, birthDate, genderStr, email, contact)) {
            return;
        }
        
        // Generate new Manager ID
        String managerId = generateNewUserId("MGR");
        
        // Create manager
        User.Gender gender = User.Gender.valueOf(genderStr.trim()); 
        Manager newManager = new Manager(managerId, name, birthDate, gender, email, contact);
        
        // Insert into database
        boolean success = Db.Manager.insert(List.of(newManager));
        
        if (success) {
            String password = generatePassword();
            createUserAuth(email, Role.Manager, password);
            
            JOptionPane.showMessageDialog(
                viewEmployees, "Manager created successfully!\nGenerated Password: " + password ,
                "Success", JOptionPane.INFORMATION_MESSAGE
            );
            viewEmployees.closeCreateDialog();
            loadAllEmployees();
        } else {
            JOptionPane.showMessageDialog(
                viewEmployees, "Failed to create manager",
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Create doctor
    private void createDoctor() {
        String name = viewEmployees.getCreateName();
        LocalDate birthDate = viewEmployees.getCreateBirthDate();
        String genderStr = viewEmployees.getCreateGender();
        String email = viewEmployees.getCreateEmail();
        String contact = viewEmployees.getCreateContact();
        String departmentName = viewEmployees.getCreateDepartment();
        String license = viewEmployees.getCreateLicense();
        
        // Validation
        if (!validateUserInput(name, birthDate, genderStr, email, contact)) {
            return;
        }
        
        // Formatted license
        String formattedLicense = "MMC" + license;
        
        // Validate department
        if (!validateDepartment(departmentName)) return;
        
        //.Validate license
        if(!validateLicense(license)) return;
        
        // Get department ID
        String departmentId = getDepartmentIdByName(departmentName);
        if (departmentId == null) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Invalid department selected",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Generate new Doctor ID
        String doctorId = generateNewUserId("DOC");
        
        // Create doctor
        User.Gender gender = User.Gender.valueOf(genderStr.trim());   
        Doctor newDoctor = new Doctor(doctorId, name, birthDate, gender, email, contact, departmentId, formattedLicense);
        
        // Insert into database
        boolean success = Db.Doctor.insert(List.of(newDoctor));
        
        if (success) {
            String password = generatePassword();
            createUserAuth(email, Role.Doctor, password);
            
            JOptionPane.showMessageDialog(
                viewEmployees, "Doctor created successfully!\nGenerated Password: " + password,
                "Success", JOptionPane.INFORMATION_MESSAGE
            );
            viewEmployees.closeCreateDialog();
            loadAllEmployees();
        } else {
            JOptionPane.showMessageDialog(
                viewEmployees, "Failed to create doctor",
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Create staff
    private void createStaff() {
        String name = viewEmployees.getCreateName();
        LocalDate birthDate = viewEmployees.getCreateBirthDate();
        String genderStr = viewEmployees.getCreateGender();
        String email = viewEmployees.getCreateEmail();
        String contact = viewEmployees.getCreateContact();
        String departmentName = viewEmployees.getCreateDepartment();
        
        // Validation
        if (!validateUserInput(name, birthDate, genderStr, email, contact)) {
            return;
        }
        
        // Validate department
        if(!validateDepartment(departmentName)) return;
        
        // Get department ID
        String departmentId = getDepartmentIdByName(departmentName);
        if (departmentId == null) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Invalid department selected",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Generate new Staff ID
        String staffId = generateNewUserId("STF");
        
        // Create staff
        User.Gender gender = User.Gender.valueOf(genderStr.trim());
        Staff newStaff = new Staff(staffId, name, birthDate, gender, email, contact, departmentId);
        
        // Insert into database
        boolean success = Db.Staff.insert(List.of(newStaff));
        
        if (success) {
            String password = generatePassword();
            createUserAuth(email, Role.Staff, password);
            
            JOptionPane.showMessageDialog(
                viewEmployees, "Staff created successfully!\nGenerated Password: " + password,
                "Success", JOptionPane.INFORMATION_MESSAGE
            );
            viewEmployees.closeCreateDialog();
            loadAllEmployees();
        } else {
            JOptionPane.showMessageDialog(
                viewEmployees, "Failed to create staff",
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Update employee
    private void updateEmployee() {
        try {
            String userId = viewEmployees.getSelectedUserId();
            Role role = viewEmployees.getSelectedUserRole();
            
            if (userId == null || userId.isEmpty()) {
                JOptionPane.showMessageDialog(
                    viewEmployees,
                    "Please select an employee to update",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            switch (role) {
                case (Role.Manager) -> updateManager(userId);
                case (Role.Doctor) -> updateDoctor(userId);
                case (Role.Staff) -> updateStaff(userId);
                default -> {
                    JOptionPane.showMessageDialog(
                        viewEmployees, "Cannot update this user type",
                        "Error", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error updating employee: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Update manager
    private void updateManager(String userId) {
        String name = viewEmployees.getUpdateName();
        LocalDate birthDate = viewEmployees.getUpdateBirthDate();
        String genderStr = viewEmployees.getUpdateGender();
        String email = viewEmployees.getUpdateEmail();
        String contact = viewEmployees.getUpdateContact();

        // Validation
        if (!validateUserInput(name, birthDate, genderStr, email, contact)) {
            return;
        }
        
        // Get old email for userAuth update
        String oldEmail = "";
        try{
            List<Manager> managers = Db.Manager.select(1, DbMan.checkById(userId));
            if(!managers.isEmpty()){
                oldEmail = managers.get(0).getEmail();
            }
        } catch (Exception ex) {}
        
        User.Gender gender = User.Gender.valueOf(genderStr.trim());
        
        // Update manager in database
        int updated = Db.Manager.update(
            1,
            DbMan.checkById(userId),
            m -> {
                m.setUserName(name);
                m.setDateOfBirth(birthDate);
                m.setGender(gender);
                m.setEmail(email);
                m.setContact(contact);
                return m;
            }
        );
        
        if (updated > 0) {
            if(!oldEmail.isEmpty() && !oldEmail.equals(email)){
                updateUserAuth(oldEmail, email, Role.Manager);
            }
            
            JOptionPane.showMessageDialog(
                viewEmployees, "Manager updated successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE
            );
            viewEmployees.closeUpdateDialog();
            loadAllEmployees();
        } else {
            JOptionPane.showMessageDialog(
                viewEmployees, "Failed to update manager",
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Update doctor
    private void updateDoctor(String userId) {
        String name = viewEmployees.getUpdateName();
        LocalDate birthDate = viewEmployees.getUpdateBirthDate();
        String genderStr = viewEmployees.getUpdateGender();
        String email = viewEmployees.getUpdateEmail();
        String contact = viewEmployees.getUpdateContact();
        String departmentName = viewEmployees.getUpdateDepartment();
        String license = viewEmployees.getUpdateLicense();
        
        String formattedLicense = "MMC" + license;
        
        // Validation
        if (!validateUserInput(name, birthDate, genderStr, email, contact)) {
            return;
        }
        
        // Validate department
        if (!validateDepartment(departmentName)) return;
        
        // Validate license
        if (!validateLicense(license)) return;
        
        // Get department ID
        String departmentId = getDepartmentIdByName(departmentName);
        if (departmentId == null) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Invalid department selected",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Get old email for userAuth update
        String oldEmail = "";
        try{
            List<Doctor> doctors = Db.Doctor.select(1, DbMan.checkById(userId));
            if(!doctors.isEmpty()){
                oldEmail = doctors.get(0).getEmail();
            }
        } catch (Exception ex) {}
        
        User.Gender gender = User.Gender.valueOf(genderStr.trim());
        
        // Update doctor in database
        int updated = Db.Doctor.update(
            1,
            DbMan.checkById(userId),
            d -> {
                d.setUserName(name);
                d.setDateOfBirth(birthDate);
                d.setGender(gender);
                d.setEmail(email);
                d.setContact(contact);
                d.setDepartmentId(departmentId);
                d.setLicense(formattedLicense);
                return d;
            }
        );
        
        if (updated > 0) {
            if(!oldEmail.isEmpty() && !oldEmail.equals(email)){
                updateUserAuth(oldEmail, email, Role.Doctor);
            }
            
            JOptionPane.showMessageDialog(
                viewEmployees, "Doctor updated successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE
            );
            viewEmployees.closeUpdateDialog();
            loadAllEmployees();
        } else {
            JOptionPane.showMessageDialog(
                viewEmployees, "Failed to update doctor",
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Update staff
    private void updateStaff(String userId) {
        String name = viewEmployees.getUpdateName();
        LocalDate birthDate = viewEmployees.getUpdateBirthDate();
        String genderStr = viewEmployees.getUpdateGender();
        String email = viewEmployees.getUpdateEmail();
        String contact = viewEmployees.getUpdateContact();      
        String departmentName = viewEmployees.getUpdateDepartment();
        
        // Validation
        if (!validateUserInput(name, birthDate, genderStr, email, contact)) {
            return;
        }
        
        // Validate department
        if (!validateDepartment(departmentName)) return;
        
        // Get department ID
        String departmentId = getDepartmentIdByName(departmentName);
        if (departmentId == null) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Invalid department selected",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Get old email for userAuth update
        String oldEmail = "";
        try{
            List<Staff> staffs = Db.Staff.select(1, DbMan.checkById(userId));
            if(!staffs.isEmpty()){
                oldEmail = staffs.get(0).getEmail();
            }
        } catch (Exception ex) {}
        
        User.Gender gender = User.Gender.valueOf(genderStr.trim());
        
        // Update staff in database
        int updated = Db.Staff.update(
            1,
            DbMan.checkById(userId),
            s -> {
                s.setUserName(name);
                s.setDateOfBirth(birthDate);
                s.setGender(gender);
                s.setEmail(email);
                s.setContact(contact);
                s.setDepartmentId(departmentId);
                return s;
            }
        );
        
        if (updated > 0) {
            if(!oldEmail.isEmpty() && !oldEmail.equals(email)){
                updateUserAuth(oldEmail, email, Role.Staff);
            }
            
            JOptionPane.showMessageDialog(
                viewEmployees, "Staff updated successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE
            );
            viewEmployees.closeUpdateDialog();
            loadAllEmployees();
        } else {
            JOptionPane.showMessageDialog(
                viewEmployees, "Failed to update staff",
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
     
    // Delete employee
    private void deleteEmployee() {
        try {
            String userId = viewEmployees.getSelectedUserId();
            Role role = viewEmployees.getSelectedUserRole();
            
            if (userId == null || userId.isEmpty()) {
                JOptionPane.showMessageDialog(
                    viewEmployees, "Please select an employee to delete",
                    "Validation Error", JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            // Get email before deletion for UserAuth cleanup
            String email = "";
            switch (role) {
                case (Role.Manager) -> {
                    List<Manager> managers = Db.Manager.select(1, DbMan.checkById(userId));
                    if (!managers.isEmpty()) email = managers.get(0).getEmail();
                }
                case (Role.Doctor) -> {
                    List<Doctor> doctors = Db.Doctor.select(1, DbMan.checkById(userId));
                    if (!doctors.isEmpty()) email = doctors.get(0).getEmail();
                }
                case (Role.Staff) -> {
                    List<Staff> staffList = Db.Staff.select(1, DbMan.checkById(userId));
                    if (!staffList.isEmpty()) email = staffList.get(0).getEmail();
                }
            }
            
            // Confirm deletion
            int result = JOptionPane.showConfirmDialog(
                viewEmployees, "Are you sure you want to delete this employee?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION
            );
            
            if (result == JOptionPane.YES_OPTION) {
                int deleted = 0;
                
                switch (role) {
                    case (Role.Manager) -> deleted = Db.Manager.delete(1, DbMan.checkById(userId));
                    case (Role.Doctor) -> deleted = Db.Doctor.delete(1, DbMan.checkById(userId));
                    case (Role.Staff) -> deleted = Db.Staff.delete(1, DbMan.checkById(userId));
                }
                
                // Delete user auth entry if employee is delete successfully
                if (deleted > 0 && !email.isEmpty()){
                    deleteUserAuth(email);
                }
                
                if (deleted > 0) {
                    JOptionPane.showMessageDialog(
                        viewEmployees, "Employee deleted successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE
                    );
                    loadAllEmployees();
                } else {
                    JOptionPane.showMessageDialog(
                        viewEmployees, "Failed to delete employee",
                        "Error", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error deleting employee: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Get all employees with department and role information
    private List<EmployeeDTO> getAllEmployees() {
        try {
            List<EmployeeDTO> result = new ArrayList<>();
            
            // Load departments for lookup
            Map<String, String> deptMap = new HashMap<>();
            Db.Department.select(-1, dept -> {
                deptMap.put(dept.getId(), dept.getDepartmentName());
                return false;
            });
            
            // Load managers
            List<Manager> managers = Db.Manager.select(-1, m -> true);
            for (Manager manager : managers) {
                result.add(new EmployeeDTO(
                    manager.getId(),
                    manager.getUserName(),
                    manager.getDateOfBirth(),
                    manager.getGender(),
                    manager.getEmail(),
                    manager.getContact(),
                    "", // Managers don't have departments
                    manager.getRole()
                ));
            }
            
            // Load doctors
            List<Doctor> doctors = Db.Doctor.select(-1, d -> true);
            for (Doctor doctor : doctors) {
                String deptName = deptMap.getOrDefault(doctor.getDepartmentId(), "Unknown Department");
                result.add(new EmployeeDTO(
                    doctor.getId(),
                    doctor.getUserName(),
                    doctor.getDateOfBirth(),
                    doctor.getGender(),
                    doctor.getEmail(),
                    doctor.getContact(),
                    deptName,
                    doctor.getRole(),
                    doctor.getLicense()
                ));
            }
            
            // Load staff
            List<Staff> staffList = Db.Staff.select(-1, s -> true);
            for (Staff staff : staffList) {
                String deptName = deptMap.getOrDefault(staff.getDepartmentId(), "Unknown Department");
                result.add(new EmployeeDTO(
                    staff.getId(),
                    staff.getUserName(),
                    staff.getDateOfBirth(),
                    staff.getGender(),
                    staff.getEmail(),
                    staff.getContact(),
                    deptName,
                    staff.getRole()
                ));
            }
            
            return result;
            
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }
    
    // Get employees by role
    private List<EmployeeDTO> getEmployeesByRole(Role role) {
        try {
            // If not role then return all
            if (role == null) {
                return getAllEmployees();
            }
            
            List<EmployeeDTO> allEmployees = getAllEmployees();
            return allEmployees.stream()
                .filter(emp -> emp.getRole().equals(role))
                .toList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }
    
    // Get employees by search text
    private List<EmployeeDTO> getEmployeesBySearch(String searchText) {
        try {
            List<EmployeeDTO> allEmployees = this.getAllEmployees();
            String lowerSearchText = searchText.toLowerCase();
            
            return allEmployees.stream()
                .filter(emp -> 
                    emp.getUserName().toLowerCase().contains(lowerSearchText) ||
                    emp.getEmail().toLowerCase().contains(lowerSearchText) ||
                    emp.getContact().toLowerCase().contains(lowerSearchText)
                )
                .toList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    // Validate user input
    private boolean validateUserInput(String name, LocalDate birthDate, String gender, String email, String contact) {
        // Name validation
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                viewEmployees,
                "Please enter a name",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Name can only contain letters and spaces",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        // Birth date validation
        if (birthDate == null) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Please enter a valid age",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        if (birthDate.isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Birth date cannot be in the future",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        // Gender validation
        if (gender == null || gender.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Please select a gender",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        // Email validation
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Please enter an email",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Please enter a valid email address",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        // Contact validation
        if (contact == null || contact.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Please enter a contact number",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        if (!contact.matches("\\d{8,}")) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Contact number must be at least 8 digits and contain digits only",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
    
        return true;
    }

    private boolean validateDepartment(String departmentName) {
        if (departmentName == null || departmentName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Please select a department",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    private boolean validateLicense(String license){
        if (license == null || license.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Please enter medical license",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        if (!license.matches("\\d+")) {
            JOptionPane.showMessageDialog(
                viewEmployees,
                "Medical license must contain digits only",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        return true;
    }

    // Generate new user ID
    private String generateNewUserId(String prefix) {
        try {
            List<Manager> managers = Db.Manager.select(-1, m -> true);
            List<Doctor> doctors = Db.Doctor.select(-1, d -> true);
            List<Staff> staffList = Db.Staff.select(-1, s -> true);
            
            int maxId = 0;
            
            // Check managers
            for (Manager manager : managers) {
                String id = manager.getId();
                if (id.startsWith(prefix + "-")) {
                    int num = Integer.parseInt(id.substring(prefix.length() + 1));
                    maxId = Math.max(maxId, num);
                }
            }
            
            // Check doctors
            for (Doctor doctor : doctors) {
                String id = doctor.getId();
                if (id.startsWith(prefix + "-")) {
                    int num = Integer.parseInt(id.substring(prefix.length() + 1));
                    maxId = Math.max(maxId, num);
                }
            }
            
            // Check staff
            for (Staff staff : staffList) {
                String id = staff.getId();
                if (id.startsWith(prefix + "-")) {
                    int num = Integer.parseInt(id.substring(prefix.length() + 1));
                    maxId = Math.max(maxId, num);
                }
            }
            
            return String.format("%s-%03d", prefix, maxId + 1);
        } catch (Exception ex) {
            return prefix + "-001";
        }
    }

    // Get department ID by name
    private String getDepartmentIdByName(String departmentName) {
        try {
            List<Department> departments = Db.Department.select(-1, d -> d.getDepartmentName().equals(departmentName));
            return departments.isEmpty() ? null : departments.get(0).getId();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public JPanel getView() { return viewEmployees; }

    // Generate password randomly for new employees
    private String generatePassword(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        
        // Generate 8-character password
        for(int i = 0; i < 8; i++){
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    // Create UserAuth entry
    private void createUserAuth(String email, Role role, String password) {
        try {
            Password hashedPassword = Password.build(password);
            UserAuth userAuth = new UserAuth(email, role, hashedPassword);
            Db.UserAuth.insert(List.of(userAuth));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error creating user auth: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Update UserAuth entry when email changes
    private void updateUserAuth(String oldEmail, String newEmail, Role role) {
        try {
            // Get existing UserAuth entry
            List<UserAuth> existingAuth = Db.UserAuth.select(1, auth -> auth.getEmail().equals(oldEmail));
            if (!existingAuth.isEmpty()) {
                UserAuth oldAuth = existingAuth.get(0);
                
                // Delete old entry
                Db.UserAuth.delete(1, auth -> auth.getEmail().equals(oldEmail));
                
                // Create new entry with updated email but same password and plain text
                UserAuth newAuth = new UserAuth(newEmail, role, oldAuth.getPassword());
                Db.UserAuth.insert(List.of(newAuth));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error updating user authentication: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Delete UserAuth
    private void deleteUserAuth(String email) {
        try {
            Db.UserAuth.delete(1, auth -> auth.getEmail().equals(email));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error deleting user authentication: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
}



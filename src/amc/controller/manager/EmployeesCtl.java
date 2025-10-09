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
import java.util.function.Predicate;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EmployeesCtl extends AbstractSubCtl {
    private final EmployeesPanel viewEmployees = new EmployeesPanel();

    private List<User> allEmployees = new ArrayList<>();

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
            Role role = switch (selectedRole) {
                case "Manager" -> Role.Manager;
                case "Staff"   -> Role.Staff;
                case "Doctor"  -> Role.Doctor;
                case "All"     -> null;
                default -> null;
            };

            if (role != null) { loadEmployeesByRole(role); }
            else { loadAllEmployees(); }
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

        // Open create user
        viewEmployees.addPropertyChangeListener("openCreateUser", evt -> {
            viewEmployees.setupDepartmentOptions(true,
                Db.Department.select(-1, dept -> true)
            );
        });

        // Open update user
        viewEmployees.addPropertyChangeListener("openUpdateUser", evt -> {
            viewEmployees.setupDepartmentOptions(false,
                Db.Department.select(-1, dept -> true)
            );
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
            this.getAllEmployees();
            viewEmployees.showEmployees(allEmployees);
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
            var employees = this.getEmployeesBy(byRole(role));
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
            var employees = this.getEmployeesBy(bySearch(searchText));
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

            String name = viewEmployees.getCreateName();
            LocalDate birthDate = viewEmployees.getCreateBirthDate();
            User.Gender gender = viewEmployees.getCreateGender();
            String email = viewEmployees.getCreateEmail();
            String contact = viewEmployees.getCreateContact();
            Department department = viewEmployees.getCreateDepartment();
            String license = viewEmployees.getCreateLicense();

            String formattedLicense = selectedRole == Role.Doctor ? "MMC" + license : null;
            if (
                !validateUserInput(name, birthDate, gender, email, contact) ||
                (selectedRole == Role.Staff || selectedRole == Role.Doctor) && !validateDepartment(department) ||
                (selectedRole == Role.Doctor) && !validateLicense(license)
            ) return;

            String generatedId = selectedRole.getHandle().newId();
            String departmentId = department != null ? department.getId() : null;

            User newEmp = selectedRole.newUsr(
                generatedId,
                name,
                birthDate,
                gender,
                email,
                contact,
                departmentId,
                formattedLicense
            );
            boolean success = switch (newEmp) {
                case Manager mng -> Db.Manager.insert(List.of(mng));
                case Staff   stf -> Db.Staff.insert(List.of(stf));
                case Doctor  doc -> Db.Doctor.insert(List.of(doc));
                default -> false;
            };

            if (!success) {
                JOptionPane.showMessageDialog(
                    viewEmployees, "Failed to create " + selectedRole.toString().toLowerCase(),
                    "Error", JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String password = generatePassword();
            createUserAuth(email, selectedRole, password);

            JOptionPane.showMessageDialog(
                viewEmployees, selectedRole.toString() + " created successfully!\nGenerated Password: " + password,
                "Success", JOptionPane.INFORMATION_MESSAGE
            );
            viewEmployees.closeCreateDialog();
            loadAllEmployees();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error creating employee: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Update employee
    private void updateEmployee() {
        try {
            User employee = viewEmployees.getSelectedEmployee();

            if (employee == null) {
                JOptionPane.showMessageDialog(
                    viewEmployees,
                    "Please select an employee to update",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String name = viewEmployees.getUpdateName();
            LocalDate birthDate = viewEmployees.getUpdateBirthDate();
            User.Gender gender = viewEmployees.getUpdateGender();
            String email = viewEmployees.getUpdateEmail();
            String contact = viewEmployees.getUpdateContact();
            Department department = viewEmployees.getUpdateDepartment();
            String license = viewEmployees.getUpdateLicense();

            if (
                !validateUserInput(name, birthDate, gender, email, contact) ||
                (employee instanceof Employee) && !validateDepartment(department) ||
                (employee instanceof Doctor)   && !validateLicense(license)
            ) return;

            String formattedLicense = (employee instanceof Doctor) ? "MMC" + license : null;

            String oldEmail = employee.getEmail();
            int updated = employee.getRole().getHandle().update(1,
                DbMan.checkById(employee.getId()),
                usr -> {
                    usr.setUserName(name);
                    usr.setDateOfBirth(birthDate);
                    usr.setGender(gender);
                    usr.setEmail(email);
                    usr.setContact(contact);
                    if (usr instanceof Employee emp) {
                        emp.setDepartmentId(department.getId());
                        emp.setDepartment(department);
                    }
                    if (usr instanceof Doctor doc) doc.setLicense(formattedLicense);
                    return usr;
                }
            );

            if (updated <= 0) {
                JOptionPane.showMessageDialog(
                    viewEmployees, String.format("Failed to update %s", employee.getRole()),
                    "Error", JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if(!oldEmail.isEmpty() && !oldEmail.equals(email)){
                updateUserAuth(oldEmail, email, employee.getRole());
            }

            viewEmployees.closeUpdateDialog();
            loadAllEmployees();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error updating employee: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Delete employee
    private void deleteEmployee() {
        try {
            User employee = viewEmployees.getSelectedEmployee();

            if (employee == null) {
                JOptionPane.showMessageDialog(
                    viewEmployees, "Please select an employee to delete",
                    "Validation Error", JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Confirm deletion
            int result = JOptionPane.showConfirmDialog(
                viewEmployees, "Are you sure you want to delete this employee?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION
            );
            if (result != JOptionPane.YES_OPTION) return;

            int deleted;
            deleted = employee.getRole().getHandle().delete(1,
                DbMan.checkById(employee.getId())
            );

            // Delete user auth entry if employee is delete successfully
            if (deleted > 0) {
                deleteUserAuth(employee.getEmail());
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error deleting employee: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Get all employees with department and role information
    private void getAllEmployees() {
        try {
            List<User> result = new ArrayList<>();

            // Load departments for lookup
            Map<String, Department> deptMap = new HashMap<>();
            Db.Department.select(-1, dept -> {
                deptMap.put(dept.getId(), dept);
                return false;
            });

            // Load managers, doctors & staffs
            List<Manager> managerLs = Db.Manager.select(-1, mgr -> true);
            List<Doctor> doctorLs = Db.Doctor.select(-1, doc -> {
                doc.setDepartment(deptMap.get(doc.getDepartmentId()));
                return true;
            });
            List<Staff> staffLs = Db.Staff.select(-1, stf -> {
                stf.setDepartment(deptMap.get(stf.getDepartmentId()));
                return true;
            });

            result.addAll(managerLs);
            result.addAll(doctorLs);
            result.addAll(staffLs);
            allEmployees = result;

        } catch (Exception ex) {}
    }

    private static Predicate<User> byRole(Role role) {
        return emp -> emp.getRole().equals(role);
    }

    private static Predicate<User> bySearch(String searchText) {
        String lower = searchText.toLowerCase();
        return emp ->
            emp.getUserName().toLowerCase().contains(lower) ||
            emp.getEmail().toLowerCase().contains(lower) ||
            emp.getContact().toLowerCase().contains(lower);
    }

    // Get employees by role or search
    private List<User> getEmployeesBy(Predicate<User> filter) {
        try {
            return allEmployees.stream().filter(filter).toList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    // Validate user input
    private boolean validateUserInput(String name, LocalDate birthDate, User.Gender gender, String email, String contact) {
        String errorMessage =
            // Name validation
            (name == null || name.trim().isEmpty())
            ? "Please enter a name" :
            (!name.matches("^[a-zA-Z\\s]+$"))
            ? "Name can only contain letters and spaces" :
            // Birth date validation
            (birthDate == null)
            ? "Please enter a valid age" :
            (birthDate.isAfter(LocalDate.now()))
            ? "Birth date cannot be in the future" :
            // Gender validation
            (gender == null)
            ? "Please select a gender" :
            // Email validation
            (email == null || email.trim().isEmpty())
            ? "Please enter an email" :
            (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
            ? "Please enter a valid email address" :
            // Contact validation
            (contact == null || contact.trim().isEmpty())
            ? "Please enter a contact number" :
            (!contact.matches("\\d{8,}"))
            ? "Contact number must be at least 8 digits and contain digits only" :
            null;
        if (errorMessage == null) return true;

        JOptionPane.showMessageDialog(viewEmployees,
            errorMessage, "Validation Error",
            JOptionPane.WARNING_MESSAGE
        );
        return false;
    }

    private boolean validateDepartment(Department department) {
        if (department == null) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Please select a department",
                "Validation Error", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    private boolean validateLicense(String license){
        String errorMessage =
            (license == null || license.trim().isEmpty())
            ? "Please enter medical license" :
            (!license.matches("\\d+"))
            ? "Medical license must contain digits only" :
            null;
        if (errorMessage == null) return true;

        JOptionPane.showMessageDialog(viewEmployees,
            errorMessage, "Validation Error",
            JOptionPane.WARNING_MESSAGE
        );
        return false;
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
            Db.UserAuth.update(1, DbMan.checkUserAuth(oldEmail), auth -> {
                auth.setEmail(newEmail);
                auth.setRole(role);
                return auth;
            });
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
            Db.UserAuth.delete(1, DbMan.checkUserAuth(email));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewEmployees, "Error deleting user authentication: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

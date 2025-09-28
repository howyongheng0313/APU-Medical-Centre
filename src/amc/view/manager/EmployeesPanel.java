package amc.view.manager;

import amc.model.entity.Department;
import amc.model.entity.Doctor;
import amc.model.entity.Employee;
import amc.model.entity.Role;
import amc.model.entity.User;
import amc.view.comp.AmcComboBox;
import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmployeesPanel extends javax.swing.JPanel {

    private User selectedEmployee = null;
    private Role currentCreateRole;
    private List<User> currentEmployees = new ArrayList<>();

    public EmployeesPanel() {
        initComponents();
        AmcComboBox.styleComboBox(jcbUsers);
        setupTableSelection();
        showEmptyState();
    }

    private void setupTableSelection(){
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                int selectedRow = jTable1.getSelectedRow();
                if(selectedRow >= 0 && selectedRow < currentEmployees.size()){
                    selectedEmployee = currentEmployees.get(selectedRow);
                } else {
                    selectedEmployee = null;
                }
            }
        });
    }

    //Show employee in table
    public void showEmployees(List<User> employees){
        this.currentEmployees = employees;
        String[] columns = {"Name", "Date of Birth", "Gender", "Email", "Contact", "Department", "Role", "License"};
        DefaultTableModel model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int r, int c) {return false;}
        };
        for(User employee : employees){
            model.addRow(new Object[]{
                employee.getUserName(),
                employee.getDateOfBirth(),
                employee.getGender(),
                employee.getEmail(),
                employee.getContact(),
                (employee instanceof Employee emp) ? emp.getDepartment().getDepartmentName() : "",
                employee.getRole(),
                (employee instanceof Doctor doc) ? doc.getLicense() : ""
            });
        }
        jTable1.setModel(model);
    }

    // Show empty state 
    private void showEmptyState(){
        String[] columns = {"Name", "Date of Birth", "Gender", "Email", "Contact", "Department", "Role", "License"};
        DefaultTableModel model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int r, int c) {return false;}
        };
        jTable1.setModel(model);
    }

    // Populate update form
    private void populateUpdateForm(){
        int selectedRow = jTable1.getSelectedRow();
        if(selectedRow >= 0 && selectedRow < currentEmployees.size()){
            User employee = currentEmployees.get(selectedRow);

            //Populate based on role
            ftfUDName.setText(employee.getUserName());
            dfUDBirthDate.set$date(employee.getDateOfBirth());
            cbxUDGender.setSelectedItem(employee.getGender());
            jtfUDEmail.setText(employee.getEmail());
            ftfUDContact.setText(employee.getContact());

            if (employee instanceof Employee emp)
                cbxUDDepartment.setSelectedItem(emp.getDepartment().getDepartmentName());

            if (employee instanceof Doctor doc) {
                String license = doc.getLicense();
                if (license != null && license.startsWith("MMC")) {
                    license = license.substring(3); // Remove "MMC" prefix
                }
                jtfUDMedicalLicense.setText(license);
            }
        }
    }

    // Setup department options
    public void setupDepartmentOptions(boolean isCreateUser, List<Department> deptLs) {
        (isCreateUser ? cbxCDDepartment : cbxUDDepartment)
            .setModel(new DefaultComboBoxModel<Department>(deptLs.toArray(new Department[0])));
    }

    // Get selected employee
    public User getSelectedEmployee() { return selectedEmployee; }

    // Get search input
    public String getSearchInput() { return jtfSearch.getText(); }

    // Get create role
    public Role getCreateRole() { return currentCreateRole; }

    // Get create name
    public String getCreateName() { return ftfCDName.getText(); }

    // Get create birth date
    public LocalDate getCreateBirthDate() { return dfCDBirthDate.get$date(); }

    // Get create gender
    public User.Gender getCreateGender() { return (User.Gender)cbxCDGender.getSelectedItem(); }

    // Get create email
    public String getCreateEmail() { return jtfCDEmail.getText(); }

    // Get create contact
    public String getCreateContact() { return ftfCDContact.getText(); }

    // Get create department (doctor/ Staff)
    public Department getCreateDepartment() { return (Department)cbxCDDepartment.getSelectedItem(); }

    public String getCreateLicense() { return jtfCDMedicalLicense.getText(); }

    // Get create name
    public String getUpdateName() { return ftfUDName.getText(); }

    // Get create birth date
    public LocalDate getUpdateBirthDate() { return dfUDBirthDate.get$date(); }

    // Get create gender
    public User.Gender getUpdateGender() { return (User.Gender)cbxUDGender.getSelectedItem(); }

    // Get create email
    public String getUpdateEmail() { return jtfUDEmail.getText(); }

    // Get create contact
    public String getUpdateContact() { return ftfUDContact.getText(); }

    // Get create department (doctor/ Staff)
    public Department getUpdateDepartment() { return (Department)cbxUDDepartment.getSelectedItem(); }

    public String getUpdateLicense() { return jtfUDMedicalLicense.getText(); }

    // Close create dialogs
    public void closeCreateDialog() { createEmployee.setVisible(false); }

    // Close update dialogs
    public void closeUpdateDialog() { updateEmployee.setVisible(false); }

    // get Selected Role in combobox
    public String getSelectedRole(){
        return (String) jcbUsers.getSelectedItem();
    }

    // Reset search field
    public void resetSearchField() {
        jtfSearch.setText("Search users");
        jtfSearch.setForeground(new Color(153, 153, 153));
    }

    private void deptInputVisible(boolean visible) {
        lblCDDepartment.setVisible(visible);
        cbxCDDepartment.setVisible(visible);
        lblUDDepartment.setVisible(visible);
        cbxUDDepartment.setVisible(visible);
    }

    private void licenseInputVisible(boolean visible) {
        lblCDMedicalLicense.setVisible(visible);
        jtfCDMedicalLicense.setVisible(visible);
        lblUDMedicalLicense.setVisible(visible);
        jtfUDMedicalLicense.setVisible(visible);
    }

    private void btnUserCDefaultAction() {
        createRole.setVisible(false);
        createEmployee.pack();
        createEmployee.setLocationRelativeTo(null);
        createEmployee.setVisible(true);
    }

    private void btnUserUDefaultAction() {
        updateRole.setVisible(false);
        updateEmployee.pack();
        updateEmployee.setLocationRelativeTo(null);
        updateEmployee.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        createEmployee = new javax.swing.JDialog();
        main2 = new javax.swing.JPanel();
        title2 = new javax.swing.JPanel();
        lblCreateDoctor = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form2 = new javax.swing.JPanel();
        right2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createUserInfo2 = new javax.swing.JPanel();
        lblCDInstruction = new javax.swing.JLabel();
        lblCDName = new javax.swing.JLabel();
        ftfCDName = new javax.swing.JFormattedTextField();
        lblCDBirthDate = new javax.swing.JLabel();
        dfCDBirthDate = new amc.view.comp.AmcDateField();
        lblCDGender = new javax.swing.JLabel();
        cbxCDGender = new javax.swing.JComboBox<>();
        lblCDEmail = new javax.swing.JLabel();
        jtfCDEmail = new javax.swing.JTextField();
        lblCDContact = new javax.swing.JLabel();
        ftfCDContact = new javax.swing.JFormattedTextField();
        lblCDDepartment = new javax.swing.JLabel();
        cbxCDDepartment = new javax.swing.JComboBox<>();
        lblCDMedicalLicense = new javax.swing.JLabel();
        jtfCDMedicalLicense = new javax.swing.JTextField();
        button2 = new javax.swing.JPanel();
        btnCDAdd = new javax.swing.JButton();
        updateEmployee = new javax.swing.JDialog();
        main4 = new javax.swing.JPanel();
        title4 = new javax.swing.JPanel();
        lblUpdateDoctor = new javax.swing.JLabel();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form4 = new javax.swing.JPanel();
        right4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createUserInfo4 = new javax.swing.JPanel();
        lblUDInstruction = new javax.swing.JLabel();
        lblUDName = new javax.swing.JLabel();
        ftfUDName = new javax.swing.JFormattedTextField();
        lblUDBirthDate = new javax.swing.JLabel();
        dfUDBirthDate = new amc.view.comp.AmcDateField();
        lblUDGender = new javax.swing.JLabel();
        cbxUDGender = new javax.swing.JComboBox<>();
        lblUDEmail = new javax.swing.JLabel();
        jtfUDEmail = new javax.swing.JTextField();
        lblUDContact = new javax.swing.JLabel();
        ftfUDContact = new javax.swing.JFormattedTextField();
        lblUDDepartment = new javax.swing.JLabel();
        cbxUDDepartment = new javax.swing.JComboBox<>();
        lblUDMedicalLicense = new javax.swing.JLabel();
        jtfUDMedicalLicense = new javax.swing.JTextField();
        button4 = new javax.swing.JPanel();
        btnUDSave = new javax.swing.JButton();
        createRole = new javax.swing.JDialog();
        main6 = new javax.swing.JPanel();
        title6 = new javax.swing.JPanel();
        lblCreateRole = new javax.swing.JLabel();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form6 = new javax.swing.JPanel();
        right6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createUserInfo6 = new javax.swing.JPanel();
        lblCRInstruction = new javax.swing.JLabel();
        btnManager = new amc.view.comp.MenuButton();
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        btnDoctor = new amc.view.comp.MenuButton();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        btnStaff = new amc.view.comp.MenuButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 75), new java.awt.Dimension(32767, 32767));
        updateRole = new javax.swing.JDialog();
        main7 = new javax.swing.JPanel();
        title7 = new javax.swing.JPanel();
        lblUpdateRole = new javax.swing.JLabel();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form7 = new javax.swing.JPanel();
        right7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createUserInfo7 = new javax.swing.JPanel();
        lblURInstruction = new javax.swing.JLabel();
        btnManager1 = new amc.view.comp.MenuButton();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        btnDoctor1 = new amc.view.comp.MenuButton();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        btnStaff1 = new amc.view.comp.MenuButton();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 75), new java.awt.Dimension(32767, 32767));
        filter = new javax.swing.JPanel();
        jcbUsers = new javax.swing.JComboBox<>();
        jtfSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        crudButtons = new javax.swing.JPanel();
        btnCreate = new amc.view.comp.AmcButton();
        btnUpdate = new amc.view.comp.AmcButton();
        btnDelete = new amc.view.comp.AmcButton();
        table = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(32767, 0));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(32767, 0));
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 32767));

        main2.setMaximumSize(new java.awt.Dimension(500, 700));
        main2.setPreferredSize(new java.awt.Dimension(500, 700));
        main2.setLayout(new java.awt.BorderLayout());

        title2.setMaximumSize(new java.awt.Dimension(500, 50));
        title2.setPreferredSize(new java.awt.Dimension(500, 50));
        title2.setLayout(new java.awt.BorderLayout());

        lblCreateDoctor.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblCreateDoctor.setText("Create Doctor");
        title2.add(lblCreateDoctor, java.awt.BorderLayout.CENTER);
        title2.add(filler8, java.awt.BorderLayout.LINE_START);

        main2.add(title2, java.awt.BorderLayout.PAGE_START);

        form2.setLayout(new java.awt.BorderLayout());
        form2.add(right2, java.awt.BorderLayout.LINE_END);
        form2.add(left2, java.awt.BorderLayout.LINE_START);

        java.awt.GridBagLayout createUserInfo2Layout = new java.awt.GridBagLayout();
        createUserInfo2Layout.columnWeights = new double[] {1.0};
        createUserInfo2.setLayout(createUserInfo2Layout);

        lblCDInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDInstruction.setText("Please fill out this form");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(lblCDInstruction, gridBagConstraints);

        lblCDName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDName.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(lblCDName, gridBagConstraints);

        ftfCDName.setForeground(new java.awt.Color(153, 153, 153));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(ftfCDName, gridBagConstraints);

        lblCDBirthDate.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDBirthDate.setText("Birth Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(lblCDBirthDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(dfCDBirthDate, gridBagConstraints);

        lblCDGender.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDGender.setText("Gender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(lblCDGender, gridBagConstraints);

        cbxCDGender.setForeground(new java.awt.Color(153, 153, 153));
        cbxCDGender.setModel(new DefaultComboBoxModel<User.Gender>(User.Gender.values()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(cbxCDGender, gridBagConstraints);

        lblCDEmail.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDEmail.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(lblCDEmail, gridBagConstraints);

        jtfCDEmail.setForeground(new java.awt.Color(153, 153, 153));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(jtfCDEmail, gridBagConstraints);

        lblCDContact.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDContact.setText("Contact");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(lblCDContact, gridBagConstraints);

        ftfCDContact.setForeground(new java.awt.Color(153, 153, 153));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(ftfCDContact, gridBagConstraints);

        lblCDDepartment.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDDepartment.setText("Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(lblCDDepartment, gridBagConstraints);

        cbxCDDepartment.setModel(new DefaultComboBoxModel<Department>());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(cbxCDDepartment, gridBagConstraints);

        lblCDMedicalLicense.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDMedicalLicense.setText("Medical License");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(lblCDMedicalLicense, gridBagConstraints);

        jtfCDMedicalLicense.setForeground(new java.awt.Color(153, 153, 153));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo2.add(jtfCDMedicalLicense, gridBagConstraints);

        form2.add(createUserInfo2, java.awt.BorderLayout.CENTER);

        button2.setPreferredSize(new java.awt.Dimension(418, 75));
        button2.setLayout(new java.awt.GridBagLayout());

        btnCDAdd.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        btnCDAdd.setText("Add");
        btnCDAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCDAddActionPerformed(evt);
            }
        });
        button2.add(btnCDAdd, new java.awt.GridBagConstraints());

        form2.add(button2, java.awt.BorderLayout.PAGE_END);

        main2.add(form2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout createEmployeeLayout = new javax.swing.GroupLayout(createEmployee.getContentPane());
        createEmployee.getContentPane().setLayout(createEmployeeLayout);
        createEmployeeLayout.setHorizontalGroup(
            createEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        createEmployeeLayout.setVerticalGroup(
            createEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main2, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        main4.setMaximumSize(new java.awt.Dimension(500, 700));
        main4.setPreferredSize(new java.awt.Dimension(500, 700));
        main4.setLayout(new java.awt.BorderLayout());

        title4.setMaximumSize(new java.awt.Dimension(500, 50));
        title4.setPreferredSize(new java.awt.Dimension(500, 50));
        title4.setLayout(new java.awt.BorderLayout());

        lblUpdateDoctor.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblUpdateDoctor.setText("Update Doctor");
        title4.add(lblUpdateDoctor, java.awt.BorderLayout.CENTER);
        title4.add(filler10, java.awt.BorderLayout.LINE_START);

        main4.add(title4, java.awt.BorderLayout.PAGE_START);

        form4.setLayout(new java.awt.BorderLayout());
        form4.add(right4, java.awt.BorderLayout.LINE_END);
        form4.add(left4, java.awt.BorderLayout.LINE_START);

        java.awt.GridBagLayout createUserInfo4Layout = new java.awt.GridBagLayout();
        createUserInfo4Layout.columnWeights = new double[] {1.0};
        createUserInfo4.setLayout(createUserInfo4Layout);

        lblUDInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDInstruction.setText("Please fill out this form");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(lblUDInstruction, gridBagConstraints);

        lblUDName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDName.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(lblUDName, gridBagConstraints);

        ftfUDName.setForeground(new java.awt.Color(153, 153, 153));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(ftfUDName, gridBagConstraints);

        lblUDBirthDate.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDBirthDate.setText("Birth Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(lblUDBirthDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(dfUDBirthDate, gridBagConstraints);

        lblUDGender.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDGender.setText("Gender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(lblUDGender, gridBagConstraints);

        cbxUDGender.setForeground(new java.awt.Color(153, 153, 153));
        cbxUDGender.setModel(new DefaultComboBoxModel<User.Gender>(User.Gender.values())
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(cbxUDGender, gridBagConstraints);

        lblUDEmail.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDEmail.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(lblUDEmail, gridBagConstraints);

        jtfUDEmail.setForeground(new java.awt.Color(153, 153, 153));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(jtfUDEmail, gridBagConstraints);

        lblUDContact.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDContact.setText("Contact");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(lblUDContact, gridBagConstraints);

        ftfUDContact.setForeground(new java.awt.Color(153, 153, 153));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(ftfUDContact, gridBagConstraints);

        lblUDDepartment.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDDepartment.setText("Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(lblUDDepartment, gridBagConstraints);

        cbxUDDepartment.setModel(new DefaultComboBoxModel<Department>());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(cbxUDDepartment, gridBagConstraints);

        lblUDMedicalLicense.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDMedicalLicense.setText("Medical License");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(lblUDMedicalLicense, gridBagConstraints);

        jtfUDMedicalLicense.setForeground(new java.awt.Color(153, 153, 153));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        createUserInfo4.add(jtfUDMedicalLicense, gridBagConstraints);

        form4.add(createUserInfo4, java.awt.BorderLayout.CENTER);

        button4.setPreferredSize(new java.awt.Dimension(418, 75));
        button4.setLayout(new java.awt.GridBagLayout());

        btnUDSave.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        btnUDSave.setText("Save");
        btnUDSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUDSaveActionPerformed(evt);
            }
        });
        button4.add(btnUDSave, new java.awt.GridBagConstraints());

        form4.add(button4, java.awt.BorderLayout.PAGE_END);

        main4.add(form4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout updateEmployeeLayout = new javax.swing.GroupLayout(updateEmployee.getContentPane());
        updateEmployee.getContentPane().setLayout(updateEmployeeLayout);
        updateEmployeeLayout.setHorizontalGroup(
            updateEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        updateEmployeeLayout.setVerticalGroup(
            updateEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main4, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        main6.setMaximumSize(new java.awt.Dimension(500, 700));
        main6.setPreferredSize(new java.awt.Dimension(500, 700));
        main6.setLayout(new java.awt.BorderLayout());

        title6.setMaximumSize(new java.awt.Dimension(500, 50));
        title6.setPreferredSize(new java.awt.Dimension(500, 50));
        title6.setLayout(new java.awt.BorderLayout());

        lblCreateRole.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblCreateRole.setText("Create Role");
        title6.add(lblCreateRole, java.awt.BorderLayout.CENTER);
        title6.add(filler12, java.awt.BorderLayout.LINE_START);

        main6.add(title6, java.awt.BorderLayout.PAGE_START);

        form6.setLayout(new java.awt.BorderLayout());
        form6.add(right6, java.awt.BorderLayout.LINE_END);
        form6.add(left6, java.awt.BorderLayout.LINE_START);

        createUserInfo6.setLayout(new java.awt.GridLayout(6, 2, 5, 0));

        lblCRInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCRInstruction.setText("Please select a role to create");
        createUserInfo6.add(lblCRInstruction);

        btnManager.setText("Manager");
        btnManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagerActionPerformed(evt);
            }
        });
        createUserInfo6.add(btnManager);
        createUserInfo6.add(filler14);

        btnDoctor.setText("Doctor");
        btnDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoctorActionPerformed(evt);
            }
        });
        createUserInfo6.add(btnDoctor);
        createUserInfo6.add(filler15);

        btnStaff.setText("Staff");
        btnStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffActionPerformed(evt);
            }
        });
        createUserInfo6.add(btnStaff);

        form6.add(createUserInfo6, java.awt.BorderLayout.CENTER);
        form6.add(filler5, java.awt.BorderLayout.PAGE_END);

        main6.add(form6, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout createRoleLayout = new javax.swing.GroupLayout(createRole.getContentPane());
        createRole.getContentPane().setLayout(createRoleLayout);
        createRoleLayout.setHorizontalGroup(
            createRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main6, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        createRoleLayout.setVerticalGroup(
            createRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main6, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        main7.setMaximumSize(new java.awt.Dimension(500, 700));
        main7.setPreferredSize(new java.awt.Dimension(500, 700));
        main7.setLayout(new java.awt.BorderLayout());

        title7.setMaximumSize(new java.awt.Dimension(500, 50));
        title7.setPreferredSize(new java.awt.Dimension(500, 50));
        title7.setLayout(new java.awt.BorderLayout());

        lblUpdateRole.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblUpdateRole.setText("Update Role");
        title7.add(lblUpdateRole, java.awt.BorderLayout.CENTER);
        title7.add(filler13, java.awt.BorderLayout.LINE_START);

        main7.add(title7, java.awt.BorderLayout.PAGE_START);

        form7.setLayout(new java.awt.BorderLayout());
        form7.add(right7, java.awt.BorderLayout.LINE_END);
        form7.add(left7, java.awt.BorderLayout.LINE_START);

        createUserInfo7.setLayout(new java.awt.GridLayout(6, 2, 5, 0));

        lblURInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblURInstruction.setText("Please select a role to update");
        createUserInfo7.add(lblURInstruction);

        btnManager1.setText("Manager");
        btnManager1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManager1ActionPerformed(evt);
            }
        });
        createUserInfo7.add(btnManager1);
        createUserInfo7.add(filler16);

        btnDoctor1.setText("Doctor");
        btnDoctor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoctor1ActionPerformed(evt);
            }
        });
        createUserInfo7.add(btnDoctor1);
        createUserInfo7.add(filler17);

        btnStaff1.setText("Staff");
        btnStaff1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaff1ActionPerformed(evt);
            }
        });
        createUserInfo7.add(btnStaff1);

        form7.add(createUserInfo7, java.awt.BorderLayout.CENTER);
        form7.add(filler18, java.awt.BorderLayout.PAGE_END);

        main7.add(form7, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout updateRoleLayout = new javax.swing.GroupLayout(updateRole.getContentPane());
        updateRole.getContentPane().setLayout(updateRoleLayout);
        updateRoleLayout.setHorizontalGroup(
            updateRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main7, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        updateRoleLayout.setVerticalGroup(
            updateRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main7, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.BorderLayout());

        filter.setBackground(new java.awt.Color(224, 247, 247));
        filter.setForeground(new java.awt.Color(224, 247, 247));
        filter.setPreferredSize(new java.awt.Dimension(864, 70));

        jcbUsers.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jcbUsers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Manager", "Doctor", "Staff" }));
        jcbUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jcbUsers.setPreferredSize(new java.awt.Dimension(150, 22));
        jcbUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbUsersActionPerformed(evt);
            }
        });

        jtfSearch.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jtfSearch.setText("Search users");
        jtfSearch.setPreferredSize(new java.awt.Dimension(85, 25));
        jtfSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfSearchFocusLost(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 139, 139));
        btnSearch.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.setBorder(null);
        btnSearch.setPreferredSize(new java.awt.Dimension(72, 25));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout filterLayout = new javax.swing.GroupLayout(filter);
        filter.setLayout(filterLayout);
        filterLayout.setHorizontalGroup(
            filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(500, Short.MAX_VALUE))
        );
        filterLayout.setVerticalGroup(
            filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        add(filter, java.awt.BorderLayout.PAGE_START);

        crudButtons.setBackground(new java.awt.Color(224, 247, 247));
        crudButtons.setLayout(new java.awt.GridBagLayout());

        btnCreate.setBackground(new java.awt.Color(0, 139, 139));
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Create");
        btnCreate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        crudButtons.add(btnCreate, gridBagConstraints);

        btnUpdate.setBackground(new java.awt.Color(0, 139, 139));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        crudButtons.add(btnUpdate, gridBagConstraints);

        btnDelete.setBackground(new java.awt.Color(0, 139, 139));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        crudButtons.add(btnDelete, gridBagConstraints);

        add(crudButtons, java.awt.BorderLayout.PAGE_END);

        table.setBackground(new java.awt.Color(224, 247, 247));
        table.setLayout(new java.awt.BorderLayout());
        table.add(filler1, java.awt.BorderLayout.LINE_END);
        table.add(filler2, java.awt.BorderLayout.LINE_START);

        jScrollPane1.setViewportView(jTable1);

        table.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        table.add(filler3, java.awt.BorderLayout.PAGE_START);
        table.add(filler4, java.awt.BorderLayout.PAGE_END);

        add(table, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSearchFocusGained
        // TODO add your handling code here:
        if(jtfSearch.getText().equals("Search users")){
            jtfSearch.setText("");
            jtfSearch.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jtfSearchFocusGained

    private void jtfSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSearchFocusLost
        // TODO add your handling code here:
        if(jtfSearch.getText().equals("")){
            jtfSearch.setText("Search users");
            jtfSearch.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jtfSearchFocusLost

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        createRole.pack();
        createRole.setLocationRelativeTo(null);
        createRole.setVisible(true);
        this.firePropertyChange("openCreateUser", false, true);
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if(selectedEmployee == null){
            JOptionPane.showMessageDialog(
                this, "Please select an employee to update",
                "No selection", JOptionPane.WARNING_MESSAGE
                );
        }else{
            updateRole.pack();
            updateRole.setLocationRelativeTo(null);
            updateRole.setVisible(true);
            this.firePropertyChange("openUpdateUser", false, true);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        firePropertyChange("deleteEmployee", false, true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagerActionPerformed
        // TODO add your handling code here:
        currentCreateRole = Role.Manager;
        deptInputVisible(false);
        licenseInputVisible(false);
        btnUserCDefaultAction();
    }//GEN-LAST:event_btnManagerActionPerformed

    private void btnDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoctorActionPerformed
        // TODO add your handling code here:
        currentCreateRole = Role.Doctor;
        deptInputVisible(true);
        licenseInputVisible(true);
        btnUserCDefaultAction();
    }//GEN-LAST:event_btnDoctorActionPerformed

    private void btnStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffActionPerformed
        // TODO add your handling code here:
        currentCreateRole = Role.Staff;
        deptInputVisible(true);
        licenseInputVisible(false);
        btnUserCDefaultAction();
    }//GEN-LAST:event_btnStaffActionPerformed

    private void btnManager1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManager1ActionPerformed
        // TODO add your handling code here:
        if(selectedEmployee.getRole() != Role.Manager){
            JOptionPane.showMessageDialog(
                this, "Selected employee is not manager. Please select manager to update",
                "Role Mismatch", JOptionPane.WARNING_MESSAGE
                );
        }else {
            btnUserUDefaultAction();
            populateUpdateForm();
        }
    }//GEN-LAST:event_btnManager1ActionPerformed

    private void btnDoctor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoctor1ActionPerformed
        // TODO add your handling code here:
        if(selectedEmployee.getRole() != Role.Doctor){
            JOptionPane.showMessageDialog(
                this, "Selected employee is not doctor. Please select doctor to update",
                "Role Mismatch", JOptionPane.WARNING_MESSAGE
                );
        }else{
            btnUserUDefaultAction();
            populateUpdateForm();
        }
    }//GEN-LAST:event_btnDoctor1ActionPerformed

    private void btnStaff1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaff1ActionPerformed
        // TODO add your handling code here:
        if(selectedEmployee.getRole() != Role.Staff){
            JOptionPane.showMessageDialog(
                this, "Selected employee is not staff. Please select staff to update",
                "Role Mismatch", JOptionPane.WARNING_MESSAGE
                );
        }else{
            btnUserUDefaultAction();
            populateUpdateForm();
        }

    }//GEN-LAST:event_btnStaff1ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        firePropertyChange("searchByText", false, true);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCDAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCDAddActionPerformed
        // TODO add your handling code here:
        firePropertyChange("createEmployee", false, true);
    }//GEN-LAST:event_btnCDAddActionPerformed

    private void btnUDSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUDSaveActionPerformed
        // TODO add your handling code here:
        firePropertyChange("updateEmployee", false, true);
    }//GEN-LAST:event_btnUDSaveActionPerformed

    private void jcbUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbUsersActionPerformed
        // TODO add your handling code here:
        firePropertyChange("searchByRole", null, jcbUsers.getSelectedItem());
    }//GEN-LAST:event_jcbUsersActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCDAdd;
    private amc.view.comp.AmcButton btnCreate;
    private amc.view.comp.AmcButton btnDelete;
    private amc.view.comp.MenuButton btnDoctor;
    private amc.view.comp.MenuButton btnDoctor1;
    private amc.view.comp.MenuButton btnManager;
    private amc.view.comp.MenuButton btnManager1;
    private javax.swing.JButton btnSearch;
    private amc.view.comp.MenuButton btnStaff;
    private amc.view.comp.MenuButton btnStaff1;
    private javax.swing.JButton btnUDSave;
    private amc.view.comp.AmcButton btnUpdate;
    private javax.swing.JPanel button2;
    private javax.swing.JPanel button4;
    private javax.swing.JComboBox<Department> cbxCDDepartment;
    private javax.swing.JComboBox<User.Gender> cbxCDGender;
    private javax.swing.JComboBox<Department> cbxUDDepartment;
    private javax.swing.JComboBox<User.Gender> cbxUDGender;
    private javax.swing.JDialog createEmployee;
    private javax.swing.JDialog createRole;
    private javax.swing.JPanel createUserInfo2;
    private javax.swing.JPanel createUserInfo4;
    private javax.swing.JPanel createUserInfo6;
    private javax.swing.JPanel createUserInfo7;
    private javax.swing.JPanel crudButtons;
    private amc.view.comp.AmcDateField dfCDBirthDate;
    private amc.view.comp.AmcDateField dfUDBirthDate;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JPanel filter;
    private javax.swing.JPanel form2;
    private javax.swing.JPanel form4;
    private javax.swing.JPanel form6;
    private javax.swing.JPanel form7;
    private javax.swing.JFormattedTextField ftfCDContact;
    private javax.swing.JFormattedTextField ftfCDName;
    private javax.swing.JFormattedTextField ftfUDContact;
    private javax.swing.JFormattedTextField ftfUDName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcbUsers;
    private javax.swing.JTextField jtfCDEmail;
    private javax.swing.JTextField jtfCDMedicalLicense;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JTextField jtfUDEmail;
    private javax.swing.JTextField jtfUDMedicalLicense;
    private javax.swing.JLabel lblCDBirthDate;
    private javax.swing.JLabel lblCDContact;
    private javax.swing.JLabel lblCDDepartment;
    private javax.swing.JLabel lblCDEmail;
    private javax.swing.JLabel lblCDGender;
    private javax.swing.JLabel lblCDInstruction;
    private javax.swing.JLabel lblCDMedicalLicense;
    private javax.swing.JLabel lblCDName;
    private javax.swing.JLabel lblCRInstruction;
    private javax.swing.JLabel lblCreateDoctor;
    private javax.swing.JLabel lblCreateRole;
    private javax.swing.JLabel lblUDBirthDate;
    private javax.swing.JLabel lblUDContact;
    private javax.swing.JLabel lblUDDepartment;
    private javax.swing.JLabel lblUDEmail;
    private javax.swing.JLabel lblUDGender;
    private javax.swing.JLabel lblUDInstruction;
    private javax.swing.JLabel lblUDMedicalLicense;
    private javax.swing.JLabel lblUDName;
    private javax.swing.JLabel lblURInstruction;
    private javax.swing.JLabel lblUpdateDoctor;
    private javax.swing.JLabel lblUpdateRole;
    private javax.swing.Box.Filler left2;
    private javax.swing.Box.Filler left4;
    private javax.swing.Box.Filler left6;
    private javax.swing.Box.Filler left7;
    private javax.swing.JPanel main2;
    private javax.swing.JPanel main4;
    private javax.swing.JPanel main6;
    private javax.swing.JPanel main7;
    private javax.swing.Box.Filler right2;
    private javax.swing.Box.Filler right4;
    private javax.swing.Box.Filler right6;
    private javax.swing.Box.Filler right7;
    private javax.swing.JPanel table;
    private javax.swing.JPanel title2;
    private javax.swing.JPanel title4;
    private javax.swing.JPanel title6;
    private javax.swing.JPanel title7;
    private javax.swing.JDialog updateEmployee;
    private javax.swing.JDialog updateRole;
    // End of variables declaration//GEN-END:variables
}

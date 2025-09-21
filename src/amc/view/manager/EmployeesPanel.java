package amc.view.manager;

import amc.model.entity.EmployeeDTO;
import amc.model.entity.Role;
import amc.view.comp.AmcComboBox;
import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmployeesPanel extends javax.swing.JPanel {

    private String selectedUserId = "";
    private Role selectedUserRole;
    private Role currentCreateRole;
    private List<EmployeeDTO> currentEmployees = new ArrayList<>();
    
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
                    EmployeeDTO selectedEmployee = currentEmployees.get(selectedRow);
                    selectedUserId = selectedEmployee.getUserId();
                    selectedUserRole = selectedEmployee.getRole();
                } else {
                    selectedUserId = "";
                    selectedUserRole = null;
                }
            }
        });
    }
    
    //Show employee in table
    public void showEmployees(List<EmployeeDTO> employees){
        this.currentEmployees = employees;
        String[] columns = {"Name", "Date of Birth", "Gender", "Email", "Contact", "Department", "Role", "License"};
        DefaultTableModel model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int r, int c) {return false;}
        };
        
        for(EmployeeDTO employee : employees){
            model.addRow(new Object[]{
                employee.getUserName(),
                employee.getDateOfBirth(),
                employee.getGender(),
                employee.getEmail(),
                employee.getContact(),
                employee.getDepartmentName(),
                employee.getRole(),
                employee.getLicense()
            });
            
        jTable1.setModel(model);
        }
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
            EmployeeDTO employee = currentEmployees.get(selectedRow);
            
            //Populate based on role
            switch(employee.getRole()){
                case (Role.Manager) -> {
                    ftfUMName.setText(employee.getUserName());
                    dfUMBirthDate.set$date(employee.getDateOfBirth());
                    cbxUMGender.setSelectedItem(employee.getGender());
                    jtfUMEmail.setText(employee.getEmail());
                    ftfUMContact.setText(employee.getContact());
                }
                case (Role.Doctor) -> {
                    ftfUDName.setText(employee.getUserName());
                    dfUDBirthDate.set$date(employee.getDateOfBirth());
                    cbxUDGender.setSelectedItem(employee.getGender());
                    jtfUDEmail.setText(employee.getEmail());
                    ftfUDContact.setText(employee.getContact());
                    cbxUDDepartment.setSelectedItem(employee.getDepartmentName());
                    
                    String license = employee.getLicense();
                    if(license != null && license.startsWith("MMC")){
                        license = license.substring(3); // Remove "MMC" prefix
                    }
                    jtfUDMedicalLicense.setText(license);
                }
                case (Role.Staff) -> {
                    ftfUSName.setText(employee.getUserName());
                    dfUSBirthDate.set$date(employee.getDateOfBirth());
                    cbxUSGender.setSelectedItem(employee.getGender());
                    jtfUSEmail.setText(employee.getEmail());
                    ftfUSContact.setText(employee.getContact());
                    cbxUSDepartment.setSelectedItem(employee.getDepartmentName());
                }
            }
        }
    }
    
    // Get selected userId
    public String getSelectedUserId(){ return selectedUserId;}
    
    // Get selected role
    public Role getSelectedUserRole(){return selectedUserRole; }
    
    // Get search input
    public String getSearchInput(){return jtfSearch.getText();}
    
    // Get create role
    public Role getCreateRole(){ return currentCreateRole; }
    
    
    
    
    
    // Get create name
    public String getCreateName(){
        switch(currentCreateRole){
            case(Role.Manager) -> {return ftfCMName.getText();}
            case(Role.Doctor) -> {return ftfCDName.getText();}
            case(Role.Staff) -> {return ftfCSName.getText();}
            default -> {return "";}
        }
    }
    
    // Get create birth date
    public LocalDate getCreateBirthDate(){
        switch(currentCreateRole){
            case(Role.Manager) -> {return dfCMBirthDate.get$date();}
            case(Role.Doctor) -> {return dfCDBirthDate.get$date();}
            case(Role.Staff) -> {return dfCSBirthDate.get$date();}
            default -> {return null;}
        }
    }
    
    // Get create gender
    public String getCreateGender(){
        switch(currentCreateRole){
            case(Role.Manager) -> {return (String)cbxCMGender.getSelectedItem();}
            case(Role.Doctor) -> {return (String)cbxCDGender.getSelectedItem();}
            case(Role.Staff) -> {return (String)cbxCSGender.getSelectedItem();}
            default -> {return "";}
        }
    }
    
    // Get create email
    public String getCreateEmail(){
        switch(currentCreateRole){
            case(Role.Manager) -> {return jtfCMEmail.getText();}
            case(Role.Doctor) -> {return jtfCDEmail.getText();}
            case(Role.Staff) -> {return jtfCSEmail.getText();}
            default -> {return "";}
        }
    }
    
    // Get create contact
    public String getCreateContact(){
        switch(currentCreateRole){
            case(Role.Manager) -> {return ftfCMContact.getText();}
            case(Role.Doctor) -> {return ftfCDContact.getText();}
            case(Role.Staff) -> {return ftfCSContact.getText();}
            default -> {return "";}
        }
    }
    
    // Get create department (doctor/ Staff)
    public String getCreateDepartment(){
            switch(currentCreateRole){
            case(Role.Doctor) -> {return (String)cbxCDDepartment.getSelectedItem();}
            case(Role.Staff) -> {return (String)cbxCSDepartment.getSelectedItem();}
            default -> {return "";}
        }
    }
    
    public String getCreateLicense(){
        if(Role.Doctor.equals(currentCreateRole)){
            return jtfCDMedicalLicense.getText();
        }
        return "";
    }
    
    
   
    
    
    // Get create name
    public String getUpdateName(){
        switch(selectedUserRole){
            case(Role.Manager) -> {return ftfUMName.getText();}
            case(Role.Doctor) -> {return ftfUDName.getText();}
            case(Role.Staff) -> {return ftfUSName.getText();}
            default -> {return "";}
        }
    }
    
    // Get create birth date
    public LocalDate getUpdateBirthDate(){
        switch(selectedUserRole){
            case(Role.Manager) -> {return dfUMBirthDate.get$date();}
            case(Role.Doctor) -> {return dfUDBirthDate.get$date();}
            case(Role.Staff) -> {return dfUSBirthDate.get$date();}
            default -> {return null;}
        }
    }
    
    // Get create gender
    public String getUpdateGender(){
        switch(selectedUserRole){
            case(Role.Manager) -> {return (String)cbxUMGender.getSelectedItem();}
            case(Role.Doctor) -> {return (String)cbxUDGender.getSelectedItem();}
            case(Role.Staff) -> {return (String)cbxUSGender.getSelectedItem();}
            default -> {return "";}
        }
    }
    
    // Get create email
    public String getUpdateEmail(){
        switch(selectedUserRole){
            case(Role.Manager) -> {return jtfUMEmail.getText();}
            case(Role.Doctor) -> {return jtfUDEmail.getText();}
            case(Role.Staff) -> {return jtfUSEmail.getText();}
            default -> {return "";}
        }
    }
    
    // Get create contact
    public String getUpdateContact(){
        switch(selectedUserRole){
            case(Role.Manager) -> {return ftfUMContact.getText();}
            case(Role.Doctor) -> {return ftfUDContact.getText();}
            case(Role.Staff) -> {return ftfUSContact.getText();}
            default -> {return "";}
        }
    }
    
    // Get create department (doctor/ Staff)
    public String getUpdateDepartment(){
            switch(selectedUserRole){
            case(Role.Doctor) -> {return (String)cbxUDDepartment.getSelectedItem();}
            case(Role.Staff) -> {return (String)cbxUSDepartment.getSelectedItem();}
            default -> {return "";}
        }
    }
    
    public String getUpdateLicense(){
        if(Role.Doctor.equals(selectedUserRole)){
            return jtfUDMedicalLicense.getText();
        }
        return "";
    }
    
    
    
    
    
    // Close create dialogs
    public void closeCreateDialog(){
        createManager.setVisible(false);
        createDoctor.setVisible(false);
        createStaff.setVisible(false);
    }
    
    // Close update dialogs
    public void closeUpdateDialog(){
        updateManager.setVisible(false);
        updateDoctor.setVisible(false);
        updateStaff.setVisible(false);
    }
    
    // get Selected Role in combobox
    public String getSelectedRole(){
        return (String) jcbUsers.getSelectedItem();
    }
    
    // Reset search field
    public void resetSearchField() {
        jtfSearch.setText("Search users");
        jtfSearch.setForeground(new Color(153, 153, 153));
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        createManager = new javax.swing.JDialog();
        main = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        lblCreateManager = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form = new javax.swing.JPanel();
        right = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createUserInfo = new javax.swing.JPanel();
        lblCMInstruction = new javax.swing.JLabel();
        lblCMName = new javax.swing.JLabel();
        ftfCMName = new javax.swing.JFormattedTextField();
        lblCMBirthDate = new javax.swing.JLabel();
        dfCMBirthDate = new amc.view.comp.AmcDateField();
        lblCMGender = new javax.swing.JLabel();
        cbxCMGender = new javax.swing.JComboBox<>();
        lblCMEmail = new javax.swing.JLabel();
        jtfCMEmail = new javax.swing.JTextField();
        lblCMContact = new javax.swing.JLabel();
        ftfCMContact = new javax.swing.JFormattedTextField();
        button = new javax.swing.JPanel();
        btnCMAdd = new javax.swing.JButton();
        updateManager = new javax.swing.JDialog();
        main1 = new javax.swing.JPanel();
        title1 = new javax.swing.JPanel();
        lblUpdateManger = new javax.swing.JLabel();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form1 = new javax.swing.JPanel();
        right1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createUserInfo1 = new javax.swing.JPanel();
        lblUMInstruction = new javax.swing.JLabel();
        lblUMName = new javax.swing.JLabel();
        ftfUMName = new javax.swing.JFormattedTextField();
        lblUMBirthDate = new javax.swing.JLabel();
        dfUMBirthDate = new amc.view.comp.AmcDateField();
        lblUMGender = new javax.swing.JLabel();
        cbxUMGender = new javax.swing.JComboBox<>();
        lblUMEmail = new javax.swing.JLabel();
        jtfUMEmail = new javax.swing.JTextField();
        lblUMContact = new javax.swing.JLabel();
        ftfUMContact = new javax.swing.JFormattedTextField();
        button1 = new javax.swing.JPanel();
        btnUMSave = new javax.swing.JButton();
        createDoctor = new javax.swing.JDialog();
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
        updateDoctor = new javax.swing.JDialog();
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
        createStaff = new javax.swing.JDialog();
        main3 = new javax.swing.JPanel();
        title3 = new javax.swing.JPanel();
        lblCreateStaff = new javax.swing.JLabel();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form3 = new javax.swing.JPanel();
        right3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createUserInfo3 = new javax.swing.JPanel();
        lblCSInstruction = new javax.swing.JLabel();
        lblCSName = new javax.swing.JLabel();
        ftfCSName = new javax.swing.JFormattedTextField();
        lblCSBirthDate = new javax.swing.JLabel();
        dfCSBirthDate = new amc.view.comp.AmcDateField();
        lblCSGender = new javax.swing.JLabel();
        cbxCSGender = new javax.swing.JComboBox<>();
        lblCSEmail = new javax.swing.JLabel();
        jtfCSEmail = new javax.swing.JTextField();
        lblCSContact = new javax.swing.JLabel();
        ftfCSContact = new javax.swing.JFormattedTextField();
        lblCSDepartment = new javax.swing.JLabel();
        cbxCSDepartment = new javax.swing.JComboBox<>();
        button3 = new javax.swing.JPanel();
        btnCSAdd = new javax.swing.JButton();
        updateStaff = new javax.swing.JDialog();
        main5 = new javax.swing.JPanel();
        title5 = new javax.swing.JPanel();
        lblUpdateStaff = new javax.swing.JLabel();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form5 = new javax.swing.JPanel();
        right5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createUserInfo5 = new javax.swing.JPanel();
        lblUSInstruction = new javax.swing.JLabel();
        lblUSName = new javax.swing.JLabel();
        ftfUSName = new javax.swing.JFormattedTextField();
        lblUSBirthDate = new javax.swing.JLabel();
        dfUSBirthDate = new amc.view.comp.AmcDateField();
        lblUSGender = new javax.swing.JLabel();
        cbxUSGender = new javax.swing.JComboBox<>();
        lblUSEmail = new javax.swing.JLabel();
        jtfUSEmail = new javax.swing.JTextField();
        lblUSContact = new javax.swing.JLabel();
        ftfUSContact = new javax.swing.JFormattedTextField();
        lblUSDepartment = new javax.swing.JLabel();
        cbxUSDepartment = new javax.swing.JComboBox<>();
        button5 = new javax.swing.JPanel();
        btnUSSave = new javax.swing.JButton();
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

        main.setMaximumSize(new java.awt.Dimension(500, 700));
        main.setPreferredSize(new java.awt.Dimension(500, 700));
        main.setLayout(new java.awt.BorderLayout());

        title.setMaximumSize(new java.awt.Dimension(500, 50));
        title.setPreferredSize(new java.awt.Dimension(500, 50));
        title.setLayout(new java.awt.BorderLayout());

        lblCreateManager.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblCreateManager.setText("Create Manager");
        title.add(lblCreateManager, java.awt.BorderLayout.CENTER);
        title.add(filler6, java.awt.BorderLayout.LINE_START);

        main.add(title, java.awt.BorderLayout.PAGE_START);

        form.setLayout(new java.awt.BorderLayout());
        form.add(right, java.awt.BorderLayout.LINE_END);
        form.add(left, java.awt.BorderLayout.LINE_START);

        createUserInfo.setLayout(new java.awt.GridLayout(11, 2, 5, 0));

        lblCMInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCMInstruction.setText("Please fill out this form");
        createUserInfo.add(lblCMInstruction);

        lblCMName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCMName.setText("Name");
        createUserInfo.add(lblCMName);

        ftfCMName.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo.add(ftfCMName);

        lblCMBirthDate.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCMBirthDate.setText("Birth Date");
        createUserInfo.add(lblCMBirthDate);
        createUserInfo.add(dfCMBirthDate);

        lblCMGender.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCMGender.setText("Gender");
        createUserInfo.add(lblCMGender);

        cbxCMGender.setForeground(new java.awt.Color(153, 153, 153));
        cbxCMGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male ", "Female" }));
        createUserInfo.add(cbxCMGender);

        lblCMEmail.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCMEmail.setText("Email");
        createUserInfo.add(lblCMEmail);

        jtfCMEmail.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo.add(jtfCMEmail);

        lblCMContact.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCMContact.setText("Contact");
        createUserInfo.add(lblCMContact);

        ftfCMContact.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo.add(ftfCMContact);

        form.add(createUserInfo, java.awt.BorderLayout.CENTER);

        button.setPreferredSize(new java.awt.Dimension(418, 75));
        button.setLayout(new java.awt.GridBagLayout());

        btnCMAdd.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        btnCMAdd.setText("Add");
        btnCMAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCMAddActionPerformed(evt);
            }
        });
        button.add(btnCMAdd, new java.awt.GridBagConstraints());

        form.add(button, java.awt.BorderLayout.PAGE_END);

        main.add(form, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout createManagerLayout = new javax.swing.GroupLayout(createManager.getContentPane());
        createManager.getContentPane().setLayout(createManagerLayout);
        createManagerLayout.setHorizontalGroup(
            createManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        createManagerLayout.setVerticalGroup(
            createManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        main1.setMaximumSize(new java.awt.Dimension(500, 700));
        main1.setPreferredSize(new java.awt.Dimension(500, 700));
        main1.setLayout(new java.awt.BorderLayout());

        title1.setMaximumSize(new java.awt.Dimension(500, 50));
        title1.setPreferredSize(new java.awt.Dimension(500, 50));
        title1.setLayout(new java.awt.BorderLayout());

        lblUpdateManger.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblUpdateManger.setText("Update Manager");
        title1.add(lblUpdateManger, java.awt.BorderLayout.CENTER);
        title1.add(filler7, java.awt.BorderLayout.LINE_START);

        main1.add(title1, java.awt.BorderLayout.PAGE_START);

        form1.setLayout(new java.awt.BorderLayout());
        form1.add(right1, java.awt.BorderLayout.LINE_END);
        form1.add(left1, java.awt.BorderLayout.LINE_START);

        createUserInfo1.setLayout(new java.awt.GridLayout(11, 2, 5, 0));

        lblUMInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUMInstruction.setText("Please fill out this form");
        createUserInfo1.add(lblUMInstruction);

        lblUMName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUMName.setText("Name");
        createUserInfo1.add(lblUMName);

        ftfUMName.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo1.add(ftfUMName);

        lblUMBirthDate.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUMBirthDate.setText("Birth Date");
        createUserInfo1.add(lblUMBirthDate);
        createUserInfo1.add(dfUMBirthDate);

        lblUMGender.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUMGender.setText("Gender");
        createUserInfo1.add(lblUMGender);

        cbxUMGender.setForeground(new java.awt.Color(153, 153, 153));
        cbxUMGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male ", "Female" }));
        createUserInfo1.add(cbxUMGender);

        lblUMEmail.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUMEmail.setText("Email");
        createUserInfo1.add(lblUMEmail);

        jtfUMEmail.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo1.add(jtfUMEmail);

        lblUMContact.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUMContact.setText("Contact");
        createUserInfo1.add(lblUMContact);

        ftfUMContact.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo1.add(ftfUMContact);

        form1.add(createUserInfo1, java.awt.BorderLayout.CENTER);

        button1.setPreferredSize(new java.awt.Dimension(418, 75));
        button1.setLayout(new java.awt.GridBagLayout());

        btnUMSave.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        btnUMSave.setText("Save");
        btnUMSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUMSaveActionPerformed(evt);
            }
        });
        button1.add(btnUMSave, new java.awt.GridBagConstraints());

        form1.add(button1, java.awt.BorderLayout.PAGE_END);

        main1.add(form1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout updateManagerLayout = new javax.swing.GroupLayout(updateManager.getContentPane());
        updateManager.getContentPane().setLayout(updateManagerLayout);
        updateManagerLayout.setHorizontalGroup(
            updateManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        updateManagerLayout.setVerticalGroup(
            updateManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

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

        createUserInfo2.setLayout(new java.awt.GridLayout(15, 2, 5, 0));

        lblCDInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDInstruction.setText("Please fill out this form");
        createUserInfo2.add(lblCDInstruction);

        lblCDName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDName.setText("Name");
        createUserInfo2.add(lblCDName);

        ftfCDName.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo2.add(ftfCDName);

        lblCDBirthDate.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDBirthDate.setText("Birth Date");
        createUserInfo2.add(lblCDBirthDate);
        createUserInfo2.add(dfCDBirthDate);

        lblCDGender.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDGender.setText("Gender");
        createUserInfo2.add(lblCDGender);

        cbxCDGender.setForeground(new java.awt.Color(153, 153, 153));
        cbxCDGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male ", "Female" }));
        createUserInfo2.add(cbxCDGender);

        lblCDEmail.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDEmail.setText("Email");
        createUserInfo2.add(lblCDEmail);

        jtfCDEmail.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo2.add(jtfCDEmail);

        lblCDContact.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDContact.setText("Contact");
        createUserInfo2.add(lblCDContact);

        ftfCDContact.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo2.add(ftfCDContact);

        lblCDDepartment.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDDepartment.setText("Department");
        createUserInfo2.add(lblCDDepartment);

        cbxCDDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internal Medicine", "Surgery", "Pediatrics", "Dermatology", "Ophthalmology", "ENT (Ear, Nose, Throat)", "Dentistry", "Gynecology", "General Chinese Medicine", "General Pratice" }));
        createUserInfo2.add(cbxCDDepartment);

        lblCDMedicalLicense.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCDMedicalLicense.setText("Medical License");
        createUserInfo2.add(lblCDMedicalLicense);

        jtfCDMedicalLicense.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo2.add(jtfCDMedicalLicense);

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

        javax.swing.GroupLayout createDoctorLayout = new javax.swing.GroupLayout(createDoctor.getContentPane());
        createDoctor.getContentPane().setLayout(createDoctorLayout);
        createDoctorLayout.setHorizontalGroup(
            createDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        createDoctorLayout.setVerticalGroup(
            createDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        createUserInfo4.setLayout(new java.awt.GridLayout(15, 2, 5, 0));

        lblUDInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDInstruction.setText("Please fill out this form");
        createUserInfo4.add(lblUDInstruction);

        lblUDName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDName.setText("Name");
        createUserInfo4.add(lblUDName);

        ftfUDName.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo4.add(ftfUDName);

        lblUDBirthDate.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDBirthDate.setText("Birth Date");
        createUserInfo4.add(lblUDBirthDate);
        createUserInfo4.add(dfUDBirthDate);

        lblUDGender.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDGender.setText("Gender");
        createUserInfo4.add(lblUDGender);

        cbxUDGender.setForeground(new java.awt.Color(153, 153, 153));
        cbxUDGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male ", "Female" }));
        createUserInfo4.add(cbxUDGender);

        lblUDEmail.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDEmail.setText("Email");
        createUserInfo4.add(lblUDEmail);

        jtfUDEmail.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo4.add(jtfUDEmail);

        lblUDContact.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDContact.setText("Contact");
        createUserInfo4.add(lblUDContact);

        ftfUDContact.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo4.add(ftfUDContact);

        lblUDDepartment.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDDepartment.setText("Department");
        createUserInfo4.add(lblUDDepartment);

        cbxUDDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internal Medicine", "Surgery", "Pediatrics", "Dermatology", "Ophthalmology", "ENT (Ear, Nose, Throat)", "Dentistry", "Gynecology", "General Chinese Medicine", "General Pratice" }));
        createUserInfo4.add(cbxUDDepartment);

        lblUDMedicalLicense.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUDMedicalLicense.setText("Medical License");
        createUserInfo4.add(lblUDMedicalLicense);

        jtfUDMedicalLicense.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo4.add(jtfUDMedicalLicense);

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

        javax.swing.GroupLayout updateDoctorLayout = new javax.swing.GroupLayout(updateDoctor.getContentPane());
        updateDoctor.getContentPane().setLayout(updateDoctorLayout);
        updateDoctorLayout.setHorizontalGroup(
            updateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main4, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        updateDoctorLayout.setVerticalGroup(
            updateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main4, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        main3.setMaximumSize(new java.awt.Dimension(500, 700));
        main3.setPreferredSize(new java.awt.Dimension(500, 700));
        main3.setLayout(new java.awt.BorderLayout());

        title3.setMaximumSize(new java.awt.Dimension(500, 50));
        title3.setPreferredSize(new java.awt.Dimension(500, 50));
        title3.setLayout(new java.awt.BorderLayout());

        lblCreateStaff.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblCreateStaff.setText("Create Staff");
        title3.add(lblCreateStaff, java.awt.BorderLayout.CENTER);
        title3.add(filler9, java.awt.BorderLayout.LINE_START);

        main3.add(title3, java.awt.BorderLayout.PAGE_START);

        form3.setLayout(new java.awt.BorderLayout());
        form3.add(right3, java.awt.BorderLayout.LINE_END);
        form3.add(left3, java.awt.BorderLayout.LINE_START);

        createUserInfo3.setLayout(new java.awt.GridLayout(13, 2, 5, 0));

        lblCSInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCSInstruction.setText("Please fill out this form");
        createUserInfo3.add(lblCSInstruction);

        lblCSName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCSName.setText("Name");
        createUserInfo3.add(lblCSName);

        ftfCSName.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo3.add(ftfCSName);

        lblCSBirthDate.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCSBirthDate.setText("Birth Date");
        createUserInfo3.add(lblCSBirthDate);
        createUserInfo3.add(dfCSBirthDate);

        lblCSGender.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCSGender.setText("Gender");
        createUserInfo3.add(lblCSGender);

        cbxCSGender.setForeground(new java.awt.Color(153, 153, 153));
        cbxCSGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male ", "Female" }));
        createUserInfo3.add(cbxCSGender);

        lblCSEmail.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCSEmail.setText("Email");
        createUserInfo3.add(lblCSEmail);

        jtfCSEmail.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo3.add(jtfCSEmail);

        lblCSContact.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCSContact.setText("Contact");
        createUserInfo3.add(lblCSContact);

        ftfCSContact.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo3.add(ftfCSContact);

        lblCSDepartment.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblCSDepartment.setText("Department");
        createUserInfo3.add(lblCSDepartment);

        cbxCSDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internal Medicine", "Surgery", "Pediatrics", "Dermatology", "Ophthalmology", "ENT (Ear, Nose, Throat)", "Dentistry", "Gynecology", "General Chinese Medicine", "General Pratice" }));
        createUserInfo3.add(cbxCSDepartment);

        form3.add(createUserInfo3, java.awt.BorderLayout.CENTER);

        button3.setPreferredSize(new java.awt.Dimension(418, 75));
        button3.setLayout(new java.awt.GridBagLayout());

        btnCSAdd.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        btnCSAdd.setText("Add");
        btnCSAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCSAddActionPerformed(evt);
            }
        });
        button3.add(btnCSAdd, new java.awt.GridBagConstraints());

        form3.add(button3, java.awt.BorderLayout.PAGE_END);

        main3.add(form3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout createStaffLayout = new javax.swing.GroupLayout(createStaff.getContentPane());
        createStaff.getContentPane().setLayout(createStaffLayout);
        createStaffLayout.setHorizontalGroup(
            createStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main3, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        createStaffLayout.setVerticalGroup(
            createStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main3, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        main5.setMaximumSize(new java.awt.Dimension(500, 700));
        main5.setPreferredSize(new java.awt.Dimension(500, 700));
        main5.setLayout(new java.awt.BorderLayout());

        title5.setMaximumSize(new java.awt.Dimension(500, 50));
        title5.setPreferredSize(new java.awt.Dimension(500, 50));
        title5.setLayout(new java.awt.BorderLayout());

        lblUpdateStaff.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblUpdateStaff.setText("Update Staff");
        title5.add(lblUpdateStaff, java.awt.BorderLayout.CENTER);
        title5.add(filler11, java.awt.BorderLayout.LINE_START);

        main5.add(title5, java.awt.BorderLayout.PAGE_START);

        form5.setLayout(new java.awt.BorderLayout());
        form5.add(right5, java.awt.BorderLayout.LINE_END);
        form5.add(left5, java.awt.BorderLayout.LINE_START);

        createUserInfo5.setLayout(new java.awt.GridLayout(13, 2, 5, 0));

        lblUSInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUSInstruction.setText("Please fill out this form");
        createUserInfo5.add(lblUSInstruction);

        lblUSName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUSName.setText("Name");
        createUserInfo5.add(lblUSName);

        ftfUSName.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo5.add(ftfUSName);

        lblUSBirthDate.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUSBirthDate.setText("Birth Date");
        createUserInfo5.add(lblUSBirthDate);
        createUserInfo5.add(dfUSBirthDate);

        lblUSGender.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUSGender.setText("Gender");
        createUserInfo5.add(lblUSGender);

        cbxUSGender.setForeground(new java.awt.Color(153, 153, 153));
        cbxUSGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male ", "Female" }));
        createUserInfo5.add(cbxUSGender);

        lblUSEmail.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUSEmail.setText("Email");
        createUserInfo5.add(lblUSEmail);

        jtfUSEmail.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo5.add(jtfUSEmail);

        lblUSContact.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUSContact.setText("Contact");
        createUserInfo5.add(lblUSContact);

        ftfUSContact.setForeground(new java.awt.Color(153, 153, 153));
        createUserInfo5.add(ftfUSContact);

        lblUSDepartment.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUSDepartment.setText("Department");
        createUserInfo5.add(lblUSDepartment);

        cbxUSDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internal Medicine", "Surgery", "Pediatrics", "Dermatology", "Ophthalmology", "ENT (Ear, Nose, Throat)", "Dentistry", "Gynecology", "General Chinese Medicine", "General Pratice" }));
        createUserInfo5.add(cbxUSDepartment);

        form5.add(createUserInfo5, java.awt.BorderLayout.CENTER);

        button5.setPreferredSize(new java.awt.Dimension(418, 75));
        button5.setLayout(new java.awt.GridBagLayout());

        btnUSSave.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        btnUSSave.setText("Save");
        btnUSSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUSSaveActionPerformed(evt);
            }
        });
        button5.add(btnUSSave, new java.awt.GridBagConstraints());

        form5.add(button5, java.awt.BorderLayout.PAGE_END);

        main5.add(form5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout updateStaffLayout = new javax.swing.GroupLayout(updateStaff.getContentPane());
        updateStaff.getContentPane().setLayout(updateStaffLayout);
        updateStaffLayout.setHorizontalGroup(
            updateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main5, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        updateStaffLayout.setVerticalGroup(
            updateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main5, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
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
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if(selectedUserId == null || selectedUserId.isEmpty()){
            JOptionPane.showMessageDialog(
                    this, "Please select an employee to update",
                    "No selection", JOptionPane.WARNING_MESSAGE
                    );
        }else{
            updateRole.pack();
            updateRole.setLocationRelativeTo(null);
            updateRole.setVisible(true);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        firePropertyChange("deleteEmployee", false, true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagerActionPerformed
        // TODO add your handling code here:
        currentCreateRole = Role.Manager;
        createRole.setVisible(false);
        createManager.pack();
        createManager.setLocationRelativeTo(null);
        createManager.setVisible(true);
    }//GEN-LAST:event_btnManagerActionPerformed

    private void btnDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoctorActionPerformed
        // TODO add your handling code here:
        currentCreateRole = Role.Doctor;
        createRole.setVisible(false);
        createDoctor.pack();
        createDoctor.setLocationRelativeTo(null);
        createDoctor.setVisible(true);
    }//GEN-LAST:event_btnDoctorActionPerformed

    private void btnStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffActionPerformed
        // TODO add your handling code here:
        currentCreateRole = Role.Staff;
        createRole.setVisible(false);
        createStaff.pack();
        createStaff.setLocationRelativeTo(null);
        createStaff.setVisible(true);
    }//GEN-LAST:event_btnStaffActionPerformed

    private void btnManager1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManager1ActionPerformed
        // TODO add your handling code here:
        if(selectedUserRole != Role.Manager){
            JOptionPane.showMessageDialog(
                    this, "Selected employee is not manager. Please select manager to update",
                    "Role Mismatch", JOptionPane.WARNING_MESSAGE
                    );
        }else {
            updateRole.setVisible(false);
            updateManager.pack();
            updateManager.setLocationRelativeTo(null);
            updateManager.setVisible(true);
            populateUpdateForm();
        }
    }//GEN-LAST:event_btnManager1ActionPerformed

    private void btnDoctor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoctor1ActionPerformed
        // TODO add your handling code here:
        if(selectedUserRole != Role.Doctor){
            JOptionPane.showMessageDialog(
                    this, "Selected employee is not doctor. Please select doctor to update",
                    "Role Mismatch", JOptionPane.WARNING_MESSAGE
                    );
        }else{
            updateRole.setVisible(false);
            updateDoctor.pack();
            updateDoctor.setLocationRelativeTo(null);
            updateDoctor.setVisible(true);
            populateUpdateForm();
        }
    }//GEN-LAST:event_btnDoctor1ActionPerformed

    private void btnStaff1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaff1ActionPerformed
        // TODO add your handling code here:
        if(selectedUserRole != Role.Staff){
            JOptionPane.showMessageDialog(
                    this, "Selected employee is not staff. Please select staff to update",
                    "Role Mismatch", JOptionPane.WARNING_MESSAGE
                    );
        }else{
            updateRole.setVisible(false);
            updateStaff.pack();
            updateStaff.setLocationRelativeTo(null);
            updateStaff.setVisible(true);
            populateUpdateForm();
        }
        
        
    }//GEN-LAST:event_btnStaff1ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        firePropertyChange("searchByText", false, true);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCMAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCMAddActionPerformed
        // TODO add your handling code here:
        firePropertyChange("createEmployee", false, true);
    }//GEN-LAST:event_btnCMAddActionPerformed

    private void btnCDAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCDAddActionPerformed
        // TODO add your handling code here:
        firePropertyChange("createEmployee", false, true);
    }//GEN-LAST:event_btnCDAddActionPerformed

    private void btnCSAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCSAddActionPerformed
        // TODO add your handling code here:
        firePropertyChange("createEmployee", false, true);
    }//GEN-LAST:event_btnCSAddActionPerformed

    private void btnUMSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUMSaveActionPerformed
        // TODO add your handling code here:
        firePropertyChange("updateEmployee", false, true);
    }//GEN-LAST:event_btnUMSaveActionPerformed

    private void btnUDSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUDSaveActionPerformed
        // TODO add your handling code here:
        firePropertyChange("updateEmployee", false, true);
    }//GEN-LAST:event_btnUDSaveActionPerformed

    private void jcbUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbUsersActionPerformed
        // TODO add your handling code here:
        firePropertyChange("searchByRole", null, jcbUsers.getSelectedItem());
    }//GEN-LAST:event_jcbUsersActionPerformed

    private void btnUSSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUSSaveActionPerformed
        // TODO add your handling code here:
        firePropertyChange("updateEmployee", false, true);
    }//GEN-LAST:event_btnUSSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCDAdd;
    private javax.swing.JButton btnCMAdd;
    private javax.swing.JButton btnCSAdd;
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
    private javax.swing.JButton btnUMSave;
    private javax.swing.JButton btnUSSave;
    private amc.view.comp.AmcButton btnUpdate;
    private javax.swing.JPanel button;
    private javax.swing.JPanel button1;
    private javax.swing.JPanel button2;
    private javax.swing.JPanel button3;
    private javax.swing.JPanel button4;
    private javax.swing.JPanel button5;
    private javax.swing.JComboBox<String> cbxCDDepartment;
    private javax.swing.JComboBox<String> cbxCDGender;
    private javax.swing.JComboBox<String> cbxCMGender;
    private javax.swing.JComboBox<String> cbxCSDepartment;
    private javax.swing.JComboBox<String> cbxCSGender;
    private javax.swing.JComboBox<String> cbxUDDepartment;
    private javax.swing.JComboBox<String> cbxUDGender;
    private javax.swing.JComboBox<String> cbxUMGender;
    private javax.swing.JComboBox<String> cbxUSDepartment;
    private javax.swing.JComboBox<String> cbxUSGender;
    private javax.swing.JDialog createDoctor;
    private javax.swing.JDialog createManager;
    private javax.swing.JDialog createRole;
    private javax.swing.JDialog createStaff;
    private javax.swing.JPanel createUserInfo;
    private javax.swing.JPanel createUserInfo1;
    private javax.swing.JPanel createUserInfo2;
    private javax.swing.JPanel createUserInfo3;
    private javax.swing.JPanel createUserInfo4;
    private javax.swing.JPanel createUserInfo5;
    private javax.swing.JPanel createUserInfo6;
    private javax.swing.JPanel createUserInfo7;
    private javax.swing.JPanel crudButtons;
    private amc.view.comp.AmcDateField dfCDBirthDate;
    private amc.view.comp.AmcDateField dfCMBirthDate;
    private amc.view.comp.AmcDateField dfCSBirthDate;
    private amc.view.comp.AmcDateField dfUDBirthDate;
    private amc.view.comp.AmcDateField dfUMBirthDate;
    private amc.view.comp.AmcDateField dfUSBirthDate;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
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
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JPanel filter;
    private javax.swing.JPanel form;
    private javax.swing.JPanel form1;
    private javax.swing.JPanel form2;
    private javax.swing.JPanel form3;
    private javax.swing.JPanel form4;
    private javax.swing.JPanel form5;
    private javax.swing.JPanel form6;
    private javax.swing.JPanel form7;
    private javax.swing.JFormattedTextField ftfCDContact;
    private javax.swing.JFormattedTextField ftfCDName;
    private javax.swing.JFormattedTextField ftfCMContact;
    private javax.swing.JFormattedTextField ftfCMName;
    private javax.swing.JFormattedTextField ftfCSContact;
    private javax.swing.JFormattedTextField ftfCSName;
    private javax.swing.JFormattedTextField ftfUDContact;
    private javax.swing.JFormattedTextField ftfUDName;
    private javax.swing.JFormattedTextField ftfUMContact;
    private javax.swing.JFormattedTextField ftfUMName;
    private javax.swing.JFormattedTextField ftfUSContact;
    private javax.swing.JFormattedTextField ftfUSName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcbUsers;
    private javax.swing.JTextField jtfCDEmail;
    private javax.swing.JTextField jtfCDMedicalLicense;
    private javax.swing.JTextField jtfCMEmail;
    private javax.swing.JTextField jtfCSEmail;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JTextField jtfUDEmail;
    private javax.swing.JTextField jtfUDMedicalLicense;
    private javax.swing.JTextField jtfUMEmail;
    private javax.swing.JTextField jtfUSEmail;
    private javax.swing.JLabel lblCDBirthDate;
    private javax.swing.JLabel lblCDContact;
    private javax.swing.JLabel lblCDDepartment;
    private javax.swing.JLabel lblCDEmail;
    private javax.swing.JLabel lblCDGender;
    private javax.swing.JLabel lblCDInstruction;
    private javax.swing.JLabel lblCDMedicalLicense;
    private javax.swing.JLabel lblCDName;
    private javax.swing.JLabel lblCMBirthDate;
    private javax.swing.JLabel lblCMContact;
    private javax.swing.JLabel lblCMEmail;
    private javax.swing.JLabel lblCMGender;
    private javax.swing.JLabel lblCMInstruction;
    private javax.swing.JLabel lblCMName;
    private javax.swing.JLabel lblCRInstruction;
    private javax.swing.JLabel lblCSBirthDate;
    private javax.swing.JLabel lblCSContact;
    private javax.swing.JLabel lblCSDepartment;
    private javax.swing.JLabel lblCSEmail;
    private javax.swing.JLabel lblCSGender;
    private javax.swing.JLabel lblCSInstruction;
    private javax.swing.JLabel lblCSName;
    private javax.swing.JLabel lblCreateDoctor;
    private javax.swing.JLabel lblCreateManager;
    private javax.swing.JLabel lblCreateRole;
    private javax.swing.JLabel lblCreateStaff;
    private javax.swing.JLabel lblUDBirthDate;
    private javax.swing.JLabel lblUDContact;
    private javax.swing.JLabel lblUDDepartment;
    private javax.swing.JLabel lblUDEmail;
    private javax.swing.JLabel lblUDGender;
    private javax.swing.JLabel lblUDInstruction;
    private javax.swing.JLabel lblUDMedicalLicense;
    private javax.swing.JLabel lblUDName;
    private javax.swing.JLabel lblUMBirthDate;
    private javax.swing.JLabel lblUMContact;
    private javax.swing.JLabel lblUMEmail;
    private javax.swing.JLabel lblUMGender;
    private javax.swing.JLabel lblUMInstruction;
    private javax.swing.JLabel lblUMName;
    private javax.swing.JLabel lblURInstruction;
    private javax.swing.JLabel lblUSBirthDate;
    private javax.swing.JLabel lblUSContact;
    private javax.swing.JLabel lblUSDepartment;
    private javax.swing.JLabel lblUSEmail;
    private javax.swing.JLabel lblUSGender;
    private javax.swing.JLabel lblUSInstruction;
    private javax.swing.JLabel lblUSName;
    private javax.swing.JLabel lblUpdateDoctor;
    private javax.swing.JLabel lblUpdateManger;
    private javax.swing.JLabel lblUpdateRole;
    private javax.swing.JLabel lblUpdateStaff;
    private javax.swing.Box.Filler left;
    private javax.swing.Box.Filler left1;
    private javax.swing.Box.Filler left2;
    private javax.swing.Box.Filler left3;
    private javax.swing.Box.Filler left4;
    private javax.swing.Box.Filler left5;
    private javax.swing.Box.Filler left6;
    private javax.swing.Box.Filler left7;
    private javax.swing.JPanel main;
    private javax.swing.JPanel main1;
    private javax.swing.JPanel main2;
    private javax.swing.JPanel main3;
    private javax.swing.JPanel main4;
    private javax.swing.JPanel main5;
    private javax.swing.JPanel main6;
    private javax.swing.JPanel main7;
    private javax.swing.Box.Filler right;
    private javax.swing.Box.Filler right1;
    private javax.swing.Box.Filler right2;
    private javax.swing.Box.Filler right3;
    private javax.swing.Box.Filler right4;
    private javax.swing.Box.Filler right5;
    private javax.swing.Box.Filler right6;
    private javax.swing.Box.Filler right7;
    private javax.swing.JPanel table;
    private javax.swing.JPanel title;
    private javax.swing.JPanel title1;
    private javax.swing.JPanel title2;
    private javax.swing.JPanel title3;
    private javax.swing.JPanel title4;
    private javax.swing.JPanel title5;
    private javax.swing.JPanel title6;
    private javax.swing.JPanel title7;
    private javax.swing.JDialog updateDoctor;
    private javax.swing.JDialog updateManager;
    private javax.swing.JDialog updateRole;
    private javax.swing.JDialog updateStaff;
    // End of variables declaration//GEN-END:variables
}

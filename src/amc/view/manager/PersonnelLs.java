package amc.view.manager;
import java.awt.Color;

public class PersonnelLs extends javax.swing.JPanel {

    public PersonnelLs() {
        initComponents();
        UiUtils.styleComboBox(jcbUsers);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        createUser = new javax.swing.JDialog();
        main = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        lblAddUser = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form = new javax.swing.JPanel();
        right = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createUserInfo = new javax.swing.JPanel();
        lblInstruction = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        cbxRole = new javax.swing.JComboBox<>();
        lblName = new javax.swing.JLabel();
        ftfName = new javax.swing.JFormattedTextField();
        lblAge = new javax.swing.JLabel();
        spnAge = new javax.swing.JSpinner();
        lblGender = new javax.swing.JLabel();
        cbxGender = new javax.swing.JComboBox<>();
        lblEmail = new javax.swing.JLabel();
        jtfEmail = new javax.swing.JTextField();
        lblContact = new javax.swing.JLabel();
        ftfContact = new javax.swing.JFormattedTextField();
        button = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        updateUser = new javax.swing.JDialog();
        main1 = new javax.swing.JPanel();
        title1 = new javax.swing.JPanel();
        lblAddUser1 = new javax.swing.JLabel();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form1 = new javax.swing.JPanel();
        right1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createUserInfo1 = new javax.swing.JPanel();
        lblInstruction1 = new javax.swing.JLabel();
        lblRole1 = new javax.swing.JLabel();
        cbxRole1 = new javax.swing.JComboBox<>();
        lblName1 = new javax.swing.JLabel();
        ftfName1 = new javax.swing.JFormattedTextField();
        lblAge1 = new javax.swing.JLabel();
        spnAge1 = new javax.swing.JSpinner();
        lblGender1 = new javax.swing.JLabel();
        cbxGender1 = new javax.swing.JComboBox<>();
        lblEmail1 = new javax.swing.JLabel();
        jtfEmail1 = new javax.swing.JTextField();
        lblContact1 = new javax.swing.JLabel();
        ftfContact1 = new javax.swing.JFormattedTextField();
        button1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
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

        lblAddUser.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblAddUser.setText("Add New User");
        title.add(lblAddUser, java.awt.BorderLayout.CENTER);
        title.add(filler6, java.awt.BorderLayout.LINE_START);

        main.add(title, java.awt.BorderLayout.PAGE_START);

        form.setLayout(new java.awt.BorderLayout());
        form.add(right, java.awt.BorderLayout.LINE_END);
        form.add(left, java.awt.BorderLayout.LINE_START);

        createUserInfo.setLayout(new java.awt.GridLayout(13, 2, 5, 0));

        lblInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblInstruction.setText("Please fill out this form");
        createUserInfo.add(lblInstruction);

        lblRole.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblRole.setText("Role");
        createUserInfo.add(lblRole);

        cbxRole.setForeground(new java.awt.Color(153, 153, 153));
        cbxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Doctor", "Staff" }));
        createUserInfo.add(cbxRole);

        lblName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblName.setText("Name");
        createUserInfo.add(lblName);

        ftfName.setForeground(new java.awt.Color(153, 153, 153));
        ftfName.setText("Enter name");
        ftfName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftfNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftfNameFocusLost(evt);
            }
        });
        createUserInfo.add(ftfName);

        lblAge.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblAge.setText("Age");
        createUserInfo.add(lblAge);
        createUserInfo.add(spnAge);

        lblGender.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblGender.setText("Gender");
        createUserInfo.add(lblGender);

        cbxGender.setForeground(new java.awt.Color(153, 153, 153));
        cbxGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male ", "Female" }));
        createUserInfo.add(cbxGender);

        lblEmail.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblEmail.setText("Email");
        createUserInfo.add(lblEmail);

        jtfEmail.setForeground(new java.awt.Color(153, 153, 153));
        jtfEmail.setText("Enter email");
        jtfEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfEmailFocusLost(evt);
            }
        });
        createUserInfo.add(jtfEmail);

        lblContact.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblContact.setText("Contact");
        createUserInfo.add(lblContact);

        ftfContact.setForeground(new java.awt.Color(153, 153, 153));
        ftfContact.setText("Enter contact");
        ftfContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftfContactFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftfContactFocusLost(evt);
            }
        });
        createUserInfo.add(ftfContact);

        form.add(createUserInfo, java.awt.BorderLayout.CENTER);

        button.setPreferredSize(new java.awt.Dimension(418, 75));
        button.setLayout(new java.awt.GridBagLayout());

        btnAdd.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        btnAdd.setText("Add");
        button.add(btnAdd, new java.awt.GridBagConstraints());

        form.add(button, java.awt.BorderLayout.PAGE_END);

        main.add(form, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout createUserLayout = new javax.swing.GroupLayout(createUser.getContentPane());
        createUser.getContentPane().setLayout(createUserLayout);
        createUserLayout.setHorizontalGroup(
            createUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        createUserLayout.setVerticalGroup(
            createUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        main1.setMaximumSize(new java.awt.Dimension(500, 700));
        main1.setPreferredSize(new java.awt.Dimension(500, 700));
        main1.setLayout(new java.awt.BorderLayout());

        title1.setMaximumSize(new java.awt.Dimension(500, 50));
        title1.setPreferredSize(new java.awt.Dimension(500, 50));
        title1.setLayout(new java.awt.BorderLayout());

        lblAddUser1.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblAddUser1.setText("Update User Details");
        title1.add(lblAddUser1, java.awt.BorderLayout.CENTER);
        title1.add(filler7, java.awt.BorderLayout.LINE_START);

        main1.add(title1, java.awt.BorderLayout.PAGE_START);

        form1.setLayout(new java.awt.BorderLayout());
        form1.add(right1, java.awt.BorderLayout.LINE_END);
        form1.add(left1, java.awt.BorderLayout.LINE_START);

        createUserInfo1.setLayout(new java.awt.GridLayout(13, 2, 5, 0));

        lblInstruction1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblInstruction1.setText("Please fill out this form");
        createUserInfo1.add(lblInstruction1);

        lblRole1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblRole1.setText("Role");
        createUserInfo1.add(lblRole1);

        cbxRole1.setForeground(new java.awt.Color(153, 153, 153));
        cbxRole1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Doctor", "Staff" }));
        createUserInfo1.add(cbxRole1);

        lblName1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblName1.setText("Name");
        createUserInfo1.add(lblName1);

        ftfName1.setForeground(new java.awt.Color(153, 153, 153));
        ftfName1.setText("Enter new name");
        ftfName1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftfName1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftfName1FocusLost(evt);
            }
        });
        createUserInfo1.add(ftfName1);

        lblAge1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblAge1.setText("Age");
        createUserInfo1.add(lblAge1);
        createUserInfo1.add(spnAge1);

        lblGender1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblGender1.setText("Gender");
        createUserInfo1.add(lblGender1);

        cbxGender1.setForeground(new java.awt.Color(153, 153, 153));
        cbxGender1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male ", "Female" }));
        createUserInfo1.add(cbxGender1);

        lblEmail1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblEmail1.setText("Email");
        createUserInfo1.add(lblEmail1);

        jtfEmail1.setForeground(new java.awt.Color(153, 153, 153));
        jtfEmail1.setText("Enter new email");
        jtfEmail1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEmail1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfEmail1FocusLost(evt);
            }
        });
        createUserInfo1.add(jtfEmail1);

        lblContact1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblContact1.setText("Contact");
        createUserInfo1.add(lblContact1);

        ftfContact1.setForeground(new java.awt.Color(153, 153, 153));
        ftfContact1.setText("Enter new contact");
        ftfContact1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftfContact1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftfContact1FocusLost(evt);
            }
        });
        createUserInfo1.add(ftfContact1);

        form1.add(createUserInfo1, java.awt.BorderLayout.CENTER);

        button1.setPreferredSize(new java.awt.Dimension(418, 75));
        button1.setLayout(new java.awt.GridBagLayout());

        btnSave.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        btnSave.setText("Save");
        button1.add(btnSave, new java.awt.GridBagConstraints());

        form1.add(button1, java.awt.BorderLayout.PAGE_END);

        main1.add(form1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout updateUserLayout = new javax.swing.GroupLayout(updateUser.getContentPane());
        updateUser.getContentPane().setLayout(updateUserLayout);
        updateUserLayout.setHorizontalGroup(
            updateUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        updateUserLayout.setVerticalGroup(
            updateUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.BorderLayout());

        filter.setBackground(new java.awt.Color(224, 247, 247));
        filter.setForeground(new java.awt.Color(224, 247, 247));
        filter.setPreferredSize(new java.awt.Dimension(864, 70));

        jcbUsers.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jcbUsers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Doctor", "Staff" }));
        jcbUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jcbUsers.setPreferredSize(new java.awt.Dimension(150, 22));

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

        javax.swing.GroupLayout filterLayout = new javax.swing.GroupLayout(filter);
        filter.setLayout(filterLayout);
        filterLayout.setHorizontalGroup(
            filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(filterLayout.createSequentialGroup()
                        .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void ftfNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfNameFocusGained
        // TODO add your handling code here:
        if(ftfName.getText().equals("Enter name")){
            ftfName.setText("");
            ftfName.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfNameFocusGained

    private void ftfNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfNameFocusLost
        // TODO add your handling code here:
        if(ftfName.getText().equals("")){
            ftfName.setText("Enter name");
            ftfName.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfNameFocusLost

    private void jtfEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEmailFocusGained
        // TODO add your handling code here:
        if(jtfEmail.getText().equals("Enter email")){
            jtfEmail.setText("");
            jtfEmail.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jtfEmailFocusGained

    private void jtfEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEmailFocusLost
        // TODO add your handling code here:
        if(jtfEmail.getText().equals("")){
            jtfEmail.setText("Enter email");
            jtfEmail.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jtfEmailFocusLost

    private void ftfContactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfContactFocusGained
        // TODO add your handling code here:
        if(ftfContact.getText().equals("Enter contact")){
           ftfContact.setText("");
           ftfContact.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfContactFocusGained

    private void ftfContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfContactFocusLost
        // TODO add your handling code here:
        if(ftfContact.getText().equals("")){
           ftfContact.setText("Enter contact");
           ftfContact.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfContactFocusLost

    private void ftfName1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfName1FocusGained
        // TODO add your handling code here:
        if(ftfName1.getText().equals("Enter new name")){
            ftfName1.setText("");
            ftfName1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfName1FocusGained

    private void ftfName1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfName1FocusLost
        // TODO add your handling code here:
        if(ftfName1.getText().equals("")){
            ftfName1.setText("Enter new name");
            ftfName1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfName1FocusLost

    private void jtfEmail1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEmail1FocusGained
        // TODO add your handling code here:
        if(jtfEmail1.getText().equals("Enter new email")){
            jtfEmail1.setText("");
            jtfEmail1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jtfEmail1FocusGained

    private void jtfEmail1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEmail1FocusLost
        // TODO add your handling code here:
        if(jtfEmail1.getText().equals("")){
            jtfEmail1.setText("Enter new email");
            jtfEmail1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jtfEmail1FocusLost

    private void ftfContact1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfContact1FocusGained
        // TODO add your handling code here:
        if(ftfContact1.getText().equals("Enter new contact")){
           ftfContact1.setText("");
           ftfContact1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfContact1FocusGained

    private void ftfContact1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfContact1FocusLost
        // TODO add your handling code here:
        if(ftfContact1.getText().equals("")){
           ftfContact1.setText("Enter new contact");
           ftfContact1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfContact1FocusLost

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        createUser.pack();
        createUser.setLocationRelativeTo(null);
        createUser.setVisible(true);
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        updateUser.pack();
        updateUser.setLocationRelativeTo(null);
        updateUser.setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private amc.view.comp.AmcButton btnCreate;
    private amc.view.comp.AmcButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private amc.view.comp.AmcButton btnUpdate;
    private javax.swing.JPanel button;
    private javax.swing.JPanel button1;
    private javax.swing.JComboBox<String> cbxGender;
    private javax.swing.JComboBox<String> cbxGender1;
    private javax.swing.JComboBox<String> cbxRole;
    private javax.swing.JComboBox<String> cbxRole1;
    private javax.swing.JDialog createUser;
    private javax.swing.JPanel createUserInfo;
    private javax.swing.JPanel createUserInfo1;
    private javax.swing.JPanel crudButtons;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JPanel filter;
    private javax.swing.JPanel form;
    private javax.swing.JPanel form1;
    private javax.swing.JFormattedTextField ftfContact;
    private javax.swing.JFormattedTextField ftfContact1;
    private javax.swing.JFormattedTextField ftfName;
    private javax.swing.JFormattedTextField ftfName1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcbUsers;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JTextField jtfEmail1;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JLabel lblAddUser;
    private javax.swing.JLabel lblAddUser1;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblAge1;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblContact1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblGender1;
    private javax.swing.JLabel lblInstruction;
    private javax.swing.JLabel lblInstruction1;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblRole1;
    private javax.swing.Box.Filler left;
    private javax.swing.Box.Filler left1;
    private javax.swing.JPanel main;
    private javax.swing.JPanel main1;
    private javax.swing.Box.Filler right;
    private javax.swing.Box.Filler right1;
    private javax.swing.JSpinner spnAge;
    private javax.swing.JSpinner spnAge1;
    private javax.swing.JPanel table;
    private javax.swing.JPanel title;
    private javax.swing.JPanel title1;
    private javax.swing.JDialog updateUser;
    // End of variables declaration//GEN-END:variables
}

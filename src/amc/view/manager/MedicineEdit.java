package amc.view.manager;
import java.awt.Color;

public class MedicineEdit extends javax.swing.JPanel {

    public MedicineEdit() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        createMedicine = new javax.swing.JDialog();
        main = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        lblAddMedicine = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form = new javax.swing.JPanel();
        right = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        createMedicineInfo = new javax.swing.JPanel();
        lblInstruction = new javax.swing.JLabel();
        lblMedicineName = new javax.swing.JLabel();
        ftfMedicineName = new javax.swing.JFormattedTextField();
        lblMedicineFee = new javax.swing.JLabel();
        ftfMedicineFee = new javax.swing.JFormattedTextField();
        button = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        updateService = new javax.swing.JDialog();
        main1 = new javax.swing.JPanel();
        title1 = new javax.swing.JPanel();
        lblUpdateMedicine = new javax.swing.JLabel();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(48, 0), new java.awt.Dimension(32767, 0));
        form1 = new javax.swing.JPanel();
        right1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        left1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        updateServiceInfo = new javax.swing.JPanel();
        lblInstruction1 = new javax.swing.JLabel();
        lblMedicineName1 = new javax.swing.JLabel();
        ftfMedicineName1 = new javax.swing.JFormattedTextField();
        lblMedicineFee1 = new javax.swing.JLabel();
        ftfMedicineFee1 = new javax.swing.JFormattedTextField();
        button1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        medicineButtons = new javax.swing.JPanel();
        btnCreate = new amc.view.comp.AmcButton();
        btnUpdate = new amc.view.comp.AmcButton();
        btnDelete = new amc.view.comp.AmcButton();
        medicineDetails = new javax.swing.JPanel();
        medicineLabel = new javax.swing.JPanel();
        medicineFilter = new javax.swing.JPanel();
        jtfSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(32767, 0));
        medicineTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(32767, 0));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(32767, 0));

        main.setMaximumSize(new java.awt.Dimension(500, 700));
        main.setPreferredSize(new java.awt.Dimension(500, 700));
        main.setLayout(new java.awt.BorderLayout());

        title.setMaximumSize(new java.awt.Dimension(500, 50));
        title.setPreferredSize(new java.awt.Dimension(500, 50));
        title.setLayout(new java.awt.BorderLayout());

        lblAddMedicine.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblAddMedicine.setText("Add New Medicine");
        title.add(lblAddMedicine, java.awt.BorderLayout.CENTER);
        title.add(filler6, java.awt.BorderLayout.LINE_START);

        main.add(title, java.awt.BorderLayout.PAGE_START);

        form.setLayout(new java.awt.BorderLayout());
        form.add(right, java.awt.BorderLayout.LINE_END);
        form.add(left, java.awt.BorderLayout.LINE_START);

        createMedicineInfo.setLayout(new java.awt.GridLayout(13, 2, 5, 0));

        lblInstruction.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblInstruction.setText("Please fill out this form");
        createMedicineInfo.add(lblInstruction);

        lblMedicineName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblMedicineName.setText("Medicine Name");
        createMedicineInfo.add(lblMedicineName);

        ftfMedicineName.setForeground(new java.awt.Color(153, 153, 153));
        ftfMedicineName.setText("Enter medicine name");
        ftfMedicineName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftfMedicineNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftfMedicineNameFocusLost(evt);
            }
        });
        createMedicineInfo.add(ftfMedicineName);

        lblMedicineFee.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblMedicineFee.setText("Medicine Fee");
        createMedicineInfo.add(lblMedicineFee);

        ftfMedicineFee.setForeground(new java.awt.Color(153, 153, 153));
        ftfMedicineFee.setText("Enter medicine fee");
        ftfMedicineFee.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftfMedicineFeeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftfMedicineFeeFocusLost(evt);
            }
        });
        createMedicineInfo.add(ftfMedicineFee);

        form.add(createMedicineInfo, java.awt.BorderLayout.CENTER);

        button.setPreferredSize(new java.awt.Dimension(418, 75));
        button.setLayout(new java.awt.GridBagLayout());

        btnAdd.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        btnAdd.setText("Add");
        button.add(btnAdd, new java.awt.GridBagConstraints());

        form.add(button, java.awt.BorderLayout.PAGE_END);

        main.add(form, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout createMedicineLayout = new javax.swing.GroupLayout(createMedicine.getContentPane());
        createMedicine.getContentPane().setLayout(createMedicineLayout);
        createMedicineLayout.setHorizontalGroup(
            createMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        createMedicineLayout.setVerticalGroup(
            createMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        main1.setMaximumSize(new java.awt.Dimension(500, 700));
        main1.setPreferredSize(new java.awt.Dimension(500, 700));
        main1.setLayout(new java.awt.BorderLayout());

        title1.setMaximumSize(new java.awt.Dimension(500, 50));
        title1.setPreferredSize(new java.awt.Dimension(500, 50));
        title1.setLayout(new java.awt.BorderLayout());

        lblUpdateMedicine.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        lblUpdateMedicine.setText("Update Medicine Details");
        title1.add(lblUpdateMedicine, java.awt.BorderLayout.CENTER);
        title1.add(filler7, java.awt.BorderLayout.LINE_START);

        main1.add(title1, java.awt.BorderLayout.PAGE_START);

        form1.setLayout(new java.awt.BorderLayout());
        form1.add(right1, java.awt.BorderLayout.LINE_END);
        form1.add(left1, java.awt.BorderLayout.LINE_START);

        updateServiceInfo.setLayout(new java.awt.GridLayout(13, 2, 5, 0));

        lblInstruction1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblInstruction1.setText("Please fill out this form");
        updateServiceInfo.add(lblInstruction1);

        lblMedicineName1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblMedicineName1.setText("Medicine Name");
        updateServiceInfo.add(lblMedicineName1);

        ftfMedicineName1.setForeground(new java.awt.Color(153, 153, 153));
        ftfMedicineName1.setText("Enter new medicine name");
        ftfMedicineName1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftfMedicineName1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftfMedicineName1FocusLost(evt);
            }
        });
        updateServiceInfo.add(ftfMedicineName1);

        lblMedicineFee1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblMedicineFee1.setText("Medicine Fee");
        updateServiceInfo.add(lblMedicineFee1);

        ftfMedicineFee1.setForeground(new java.awt.Color(153, 153, 153));
        ftfMedicineFee1.setText("Enter new medicine fee");
        ftfMedicineFee1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftfMedicineFee1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftfMedicineFee1FocusLost(evt);
            }
        });
        ftfMedicineFee1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftfMedicineFee1ActionPerformed(evt);
            }
        });
        updateServiceInfo.add(ftfMedicineFee1);

        form1.add(updateServiceInfo, java.awt.BorderLayout.CENTER);

        button1.setPreferredSize(new java.awt.Dimension(418, 75));
        button1.setLayout(new java.awt.GridBagLayout());

        btnSave.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        btnSave.setText("Save");
        button1.add(btnSave, new java.awt.GridBagConstraints());

        form1.add(button1, java.awt.BorderLayout.PAGE_END);

        main1.add(form1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout updateServiceLayout = new javax.swing.GroupLayout(updateService.getContentPane());
        updateService.getContentPane().setLayout(updateServiceLayout);
        updateServiceLayout.setHorizontalGroup(
            updateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );
        updateServiceLayout.setVerticalGroup(
            updateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.BorderLayout());

        medicineButtons.setBackground(new java.awt.Color(245, 253, 253));
        medicineButtons.setLayout(new java.awt.GridBagLayout());

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
        medicineButtons.add(btnCreate, gridBagConstraints);

        btnUpdate.setBackground(new java.awt.Color(0, 139, 139));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Edit");
        btnUpdate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        medicineButtons.add(btnUpdate, gridBagConstraints);

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
        medicineButtons.add(btnDelete, gridBagConstraints);

        add(medicineButtons, java.awt.BorderLayout.PAGE_END);

        medicineDetails.setBackground(new java.awt.Color(255, 255, 255));
        medicineDetails.setLayout(new java.awt.GridLayout(1, 2));

        medicineLabel.setBackground(new java.awt.Color(69, 185, 174));
        medicineLabel.setLayout(new java.awt.BorderLayout());

        medicineFilter.setBackground(new java.awt.Color(245, 253, 253));
        medicineFilter.setForeground(new java.awt.Color(224, 247, 247));
        medicineFilter.setPreferredSize(new java.awt.Dimension(864, 50));
        medicineFilter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfSearch.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jtfSearch.setForeground(new java.awt.Color(153, 153, 153));
        jtfSearch.setText("Search medicine");
        jtfSearch.setPreferredSize(new java.awt.Dimension(85, 25));
        jtfSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfSearchFocusLost(evt);
            }
        });
        medicineFilter.add(jtfSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, -1));

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
        medicineFilter.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        medicineLabel.add(medicineFilter, java.awt.BorderLayout.PAGE_START);

        jLabel1.setBackground(new java.awt.Color(69, 185, 174));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/amc/image/bg_medicine_room.jpg"))); // NOI18N
        medicineLabel.add(jLabel1, java.awt.BorderLayout.CENTER);

        filler4.setBackground(new java.awt.Color(245, 253, 253));
        filler4.setOpaque(true);
        medicineLabel.add(filler4, java.awt.BorderLayout.LINE_START);

        medicineDetails.add(medicineLabel);

        medicineTable.setBackground(new java.awt.Color(255, 255, 255));
        medicineTable.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Medicine ID", "Medicine Name", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        medicineTable.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        filler1.setBackground(new java.awt.Color(245, 253, 253));
        filler1.setOpaque(true);
        medicineTable.add(filler1, java.awt.BorderLayout.PAGE_START);

        filler2.setBackground(new java.awt.Color(245, 253, 253));
        filler2.setOpaque(true);
        medicineTable.add(filler2, java.awt.BorderLayout.LINE_END);

        filler3.setBackground(new java.awt.Color(245, 253, 253));
        filler3.setOpaque(true);
        medicineTable.add(filler3, java.awt.BorderLayout.LINE_START);

        medicineDetails.add(medicineTable);

        add(medicineDetails, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void ftfMedicineNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfMedicineNameFocusGained
        // TODO add your handling code here:
        if(ftfMedicineName.getText().equals("Enter medicine name")){
            ftfMedicineName.setText("");
            ftfMedicineName.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfMedicineNameFocusGained

    private void ftfMedicineNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfMedicineNameFocusLost
        // TODO add your handling code here:
        if(ftfMedicineName.getText().equals("")){
            ftfMedicineName.setText("Enter medicine name");
            ftfMedicineName.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfMedicineNameFocusLost

    private void ftfMedicineName1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfMedicineName1FocusGained
        // TODO add your handling code here:
        if(ftfMedicineName1.getText().equals("Enter new medicine name")){
            ftfMedicineName1.setText("");
            ftfMedicineName1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfMedicineName1FocusGained

    private void ftfMedicineName1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfMedicineName1FocusLost
        // TODO add your handling code here:
        if(ftfMedicineName1.getText().equals("")){
            ftfMedicineName1.setText("Enter new medicine name");
            ftfMedicineName1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfMedicineName1FocusLost

    private void ftfMedicineFee1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftfMedicineFee1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftfMedicineFee1ActionPerformed

    private void jtfSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSearchFocusGained
        // TODO add your handling code here:
        if(jtfSearch.getText().equals("Search medicine")){
            jtfSearch.setText("");
            jtfSearch.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jtfSearchFocusGained

    private void jtfSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSearchFocusLost
        // TODO add your handling code here:
        if(jtfSearch.getText().equals("")){
            jtfSearch.setText("Search medicine");
            jtfSearch.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jtfSearchFocusLost

    private void ftfMedicineFeeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfMedicineFeeFocusGained
        // TODO add your handling code here:
        if(ftfMedicineFee.getText().equals("Enter medicine fee")){
            ftfMedicineFee.setText("");
            ftfMedicineFee.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfMedicineFeeFocusGained

    private void ftfMedicineFeeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfMedicineFeeFocusLost
        // TODO add your handling code here:
        if(ftfMedicineFee.getText().equals("")){
            ftfMedicineFee.setText("Enter medicine fee");
            ftfMedicineFee.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfMedicineFeeFocusLost

    private void ftfMedicineFee1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfMedicineFee1FocusGained
        // TODO add your handling code here:
        if(ftfMedicineFee1.getText().equals("Enter new medicine fee")){
            ftfMedicineFee1.setText("");
            ftfMedicineFee1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfMedicineFee1FocusGained

    private void ftfMedicineFee1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftfMedicineFee1FocusLost
        // TODO add your handling code here:
        if(ftfMedicineFee1.getText().equals("")){
            ftfMedicineFee1.setText("Enter new medicine fee");
            ftfMedicineFee1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ftfMedicineFee1FocusLost

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        createMedicine.pack();
        createMedicine.setLocationRelativeTo(null);
        createMedicine.setVisible(true);
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        updateService.pack();
        updateService.setLocationRelativeTo(null);
        updateService.setVisible(true);
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
    private javax.swing.JDialog createMedicine;
    private javax.swing.JPanel createMedicineInfo;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JPanel form;
    private javax.swing.JPanel form1;
    private javax.swing.JFormattedTextField ftfMedicineFee;
    private javax.swing.JFormattedTextField ftfMedicineFee1;
    private javax.swing.JFormattedTextField ftfMedicineName;
    private javax.swing.JFormattedTextField ftfMedicineName1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JLabel lblAddMedicine;
    private javax.swing.JLabel lblInstruction;
    private javax.swing.JLabel lblInstruction1;
    private javax.swing.JLabel lblMedicineFee;
    private javax.swing.JLabel lblMedicineFee1;
    private javax.swing.JLabel lblMedicineName;
    private javax.swing.JLabel lblMedicineName1;
    private javax.swing.JLabel lblUpdateMedicine;
    private javax.swing.Box.Filler left;
    private javax.swing.Box.Filler left1;
    private javax.swing.JPanel main;
    private javax.swing.JPanel main1;
    private javax.swing.JPanel medicineButtons;
    private javax.swing.JPanel medicineDetails;
    private javax.swing.JPanel medicineFilter;
    private javax.swing.JPanel medicineLabel;
    private javax.swing.JPanel medicineTable;
    private javax.swing.Box.Filler right;
    private javax.swing.Box.Filler right1;
    private javax.swing.JPanel title;
    private javax.swing.JPanel title1;
    private javax.swing.JDialog updateService;
    private javax.swing.JPanel updateServiceInfo;
    // End of variables declaration//GEN-END:variables
}

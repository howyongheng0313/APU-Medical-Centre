package amc.view.manager;

public class ManagerProfile extends javax.swing.JPanel {

    public ManagerProfile() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        main = new javax.swing.JPanel();
        Content = new javax.swing.JPanel();
        btnEditProfile = new javax.swing.JButton();
        ManagerDetails = new javax.swing.JPanel();
        lblManagerID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblContact = new javax.swing.JLabel();
        lblDateOfBirth = new javax.swing.JLabel();
        ManagerID = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        Gender = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        Contact = new javax.swing.JLabel();
        DateOfBirth = new javax.swing.JLabel();
        Empty = new javax.swing.JPanel();
        amcPicture2 = new amc.view.comp.AmcPicture();

        setPreferredSize(new java.awt.Dimension(1000, 500));
        setLayout(new java.awt.BorderLayout());

        main.setBackground(new java.awt.Color(245, 253, 253));
        main.setPreferredSize(new java.awt.Dimension(1000, 500));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {150, 0};
        jPanel1Layout.columnWeights = new double[] {0.5, 0.5};
        main.setLayout(jPanel1Layout);

        Content.setOpaque(false);
        Content.setLayout(new java.awt.GridBagLayout());

        btnEditProfile.setBackground(new java.awt.Color(0, 204, 204));
        btnEditProfile.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnEditProfile.setForeground(new java.awt.Color(255, 255, 255));
        btnEditProfile.setText("Edit Profile");
        btnEditProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnEditProfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        Content.add(btnEditProfile, gridBagConstraints);

        ManagerDetails.setBackground(new java.awt.Color(0, 204, 204));
        ManagerDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        ManagerDetails.setLayout(new java.awt.GridBagLayout());

        lblManagerID.setBackground(new java.awt.Color(255, 255, 255));
        lblManagerID.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblManagerID.setForeground(new java.awt.Color(255, 255, 255));
        lblManagerID.setText("Manager ID:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        ManagerDetails.add(lblManagerID, gridBagConstraints);

        lblName.setBackground(new java.awt.Color(255, 255, 255));
        lblName.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        ManagerDetails.add(lblName, gridBagConstraints);

        lblGender.setBackground(new java.awt.Color(255, 255, 255));
        lblGender.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblGender.setForeground(new java.awt.Color(255, 255, 255));
        lblGender.setText("Gender:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        ManagerDetails.add(lblGender, gridBagConstraints);

        lblEmail.setBackground(new java.awt.Color(255, 255, 255));
        lblEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        ManagerDetails.add(lblEmail, gridBagConstraints);

        lblContact.setBackground(new java.awt.Color(255, 255, 255));
        lblContact.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblContact.setForeground(new java.awt.Color(255, 255, 255));
        lblContact.setText("Contact:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        ManagerDetails.add(lblContact, gridBagConstraints);

        lblDateOfBirth.setBackground(new java.awt.Color(255, 255, 255));
        lblDateOfBirth.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDateOfBirth.setForeground(new java.awt.Color(255, 255, 255));
        lblDateOfBirth.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        ManagerDetails.add(lblDateOfBirth, gridBagConstraints);

        ManagerID.setBackground(new java.awt.Color(255, 255, 255));
        ManagerID.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ManagerID.setForeground(new java.awt.Color(255, 255, 255));
        ManagerID.setText("jLabel9");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        ManagerDetails.add(ManagerID, gridBagConstraints);

        Name.setBackground(new java.awt.Color(255, 255, 255));
        Name.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        Name.setForeground(new java.awt.Color(255, 255, 255));
        Name.setText("jLabel10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        ManagerDetails.add(Name, gridBagConstraints);

        Gender.setBackground(new java.awt.Color(255, 255, 255));
        Gender.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        Gender.setForeground(new java.awt.Color(255, 255, 255));
        Gender.setText("jLabel11");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        ManagerDetails.add(Gender, gridBagConstraints);

        Email.setBackground(new java.awt.Color(255, 255, 255));
        Email.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        Email.setForeground(new java.awt.Color(255, 255, 255));
        Email.setText("jLabel12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        ManagerDetails.add(Email, gridBagConstraints);

        Contact.setBackground(new java.awt.Color(255, 255, 255));
        Contact.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        Contact.setForeground(new java.awt.Color(255, 255, 255));
        Contact.setText("jLabel13");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        ManagerDetails.add(Contact, gridBagConstraints);

        DateOfBirth.setBackground(new java.awt.Color(255, 255, 255));
        DateOfBirth.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        DateOfBirth.setForeground(new java.awt.Color(255, 255, 255));
        DateOfBirth.setText("jLabel14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        ManagerDetails.add(DateOfBirth, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        Content.add(ManagerDetails, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        main.add(Content, gridBagConstraints);

        Empty.setOpaque(false);
        Empty.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        main.add(Empty, gridBagConstraints);

        amcPicture2.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/avatar_doctor.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        main.add(amcPicture2, gridBagConstraints);

        add(main, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Contact;
    private javax.swing.JPanel Content;
    private javax.swing.JLabel DateOfBirth;
    private javax.swing.JLabel Email;
    private javax.swing.JPanel Empty;
    private javax.swing.JLabel Gender;
    private javax.swing.JPanel ManagerDetails;
    private javax.swing.JLabel ManagerID;
    private javax.swing.JLabel Name;
    private amc.view.comp.AmcPicture amcPicture2;
    private javax.swing.JButton btnEditProfile;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblDateOfBirth;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblManagerID;
    private javax.swing.JLabel lblName;
    private javax.swing.JPanel main;
    // End of variables declaration//GEN-END:variables
}

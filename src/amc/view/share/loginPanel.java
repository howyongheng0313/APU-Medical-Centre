package amc.view.share;

import amc.view.Theme;
import java.awt.*;
import amc.model.MyModel;
import javax.swing.JOptionPane;
import amc.view.manager.Dashboard;

public class loginPanel extends javax.swing.JPanel {

    public loginPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sign_in = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblNoAccount = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        jtfLoginEmail = new java.awt.TextField();
        btnForgetPassword = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jpfPassword = new javax.swing.JPasswordField();
        lblLogo = new amc.view.comp.AmcPicture();
        sign_up = new javax.swing.JPanel();
        signup_content = new javax.swing.JPanel();
        logo = new javax.swing.JPanel();
        amcPicture1 = new amc.view.comp.AmcPicture();
        signup_detail = new javax.swing.JPanel();
        signup_details = new javax.swing.JPanel();
        jtfICnumber = new javax.swing.JTextField();
        jtfCustomerName = new javax.swing.JTextField();
        jtfEmail = new javax.swing.JTextField();
        jtfContact = new javax.swing.JTextField();
        jcbGender = new javax.swing.JComboBox<>();
        btnSignup = new javax.swing.JButton();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 0));

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.CardLayout());

        sign_in.setBackground(new java.awt.Color(245, 253, 253));
        sign_in.setPreferredSize(new java.awt.Dimension(800, 500));
        java.awt.GridBagLayout sign_inLayout = new java.awt.GridBagLayout();
        sign_inLayout.columnWidths = new int[] {200, 200};
        sign_inLayout.columnWeights = new double[] {0.5, 0.5};
        sign_inLayout.rowWeights = new double[] {1.0};
        sign_in.setLayout(sign_inLayout);

        jPanel2.setBackground(Theme.C2_BG);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        lblLogin.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lblLogin.setForeground(Theme.C2_FG);
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("Login");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(lblLogin, gridBagConstraints);

        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblEmail.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(lblEmail, gridBagConstraints);

        lblPassword.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblPassword.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(lblPassword, gridBagConstraints);

        lblNoAccount.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblNoAccount.setText("Don't have an account?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel2.add(lblNoAccount, gridBagConstraints);

        btnRegister.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("Register");
        btnRegister.setBorder(null);
        btnRegister.setBorderPainted(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        jPanel2.add(btnRegister, gridBagConstraints);

        jtfLoginEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jtfLoginEmail.setPreferredSize(new java.awt.Dimension(8, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jtfLoginEmail, gridBagConstraints);

        btnForgetPassword.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnForgetPassword.setForeground(new java.awt.Color(255, 102, 102));
        btnForgetPassword.setText("Forgot Password?");
        btnForgetPassword.setBorder(null);
        btnForgetPassword.setBorderPainted(false);
        btnForgetPassword.setContentAreaFilled(false);
        btnForgetPassword.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel2.add(btnForgetPassword, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setText("Sign In");
        jButton1.setBorder(null);
        jButton1.setPreferredSize(new java.awt.Dimension(38, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jButton1, gridBagConstraints);

        jpfPassword.setPreferredSize(new java.awt.Dimension(8, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jpfPassword, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        sign_in.add(jPanel2, gridBagConstraints);

        lblLogo.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/logo_clover.png"))); // NOI18N
        lblLogo.set$maxSize(new java.awt.Dimension(400, 400));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(17, 17, 17, 17);
        sign_in.add(lblLogo, gridBagConstraints);

        add(sign_in, "card3");

        sign_up.setPreferredSize(new java.awt.Dimension(800, 500));
        sign_up.setLayout(new java.awt.BorderLayout());

        signup_content.setBackground(new java.awt.Color(255, 255, 255));
        signup_content.setLayout(new java.awt.BorderLayout());

        logo.setBackground(new java.awt.Color(245, 253, 253));
        logo.setForeground(new java.awt.Color(255, 255, 255));
        logo.setPreferredSize(new java.awt.Dimension(829, 250));
        logo.setLayout(new java.awt.BorderLayout());

        amcPicture1.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/logo_clover.png"))); // NOI18N
        logo.add(amcPicture1, java.awt.BorderLayout.CENTER);

        signup_content.add(logo, java.awt.BorderLayout.PAGE_START);

        signup_detail.setBackground(new java.awt.Color(245, 253, 253));
        signup_detail.setForeground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout signup_detailLayout = new java.awt.GridBagLayout();
        signup_detailLayout.columnWidths = new int[] {1, 1, 1};
        signup_detailLayout.columnWeights = new double[] {1.0, 1.0, 1.0};
        signup_detail.setLayout(signup_detailLayout);

        signup_details.setBackground(new java.awt.Color(245, 253, 253));
        signup_details.setForeground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout signup_detailsLayout = new java.awt.GridBagLayout();
        signup_detailsLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
        signup_detailsLayout.columnWeights = new double[] {1.0};
        signup_detailsLayout.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        signup_details.setLayout(signup_detailsLayout);

        jtfICnumber.setText("Enter your identification number");
        jtfICnumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jtfICnumber.setPreferredSize(new java.awt.Dimension(74, 35));
        jtfICnumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfICnumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfICnumberFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        signup_details.add(jtfICnumber, gridBagConstraints);

        jtfCustomerName.setText("Enter your full name");
        jtfCustomerName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jtfCustomerName.setPreferredSize(new java.awt.Dimension(74, 35));
        jtfCustomerName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCustomerNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCustomerNameFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        signup_details.add(jtfCustomerName, gridBagConstraints);

        jtfEmail.setText("Enter your email");
        jtfEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jtfEmail.setPreferredSize(new java.awt.Dimension(74, 35));
        jtfEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfEmailFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        signup_details.add(jtfEmail, gridBagConstraints);

        jtfContact.setText("Enter your contact number");
        jtfContact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jtfContact.setPreferredSize(new java.awt.Dimension(74, 35));
        jtfContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfContactFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfContactFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        signup_details.add(jtfContact, gridBagConstraints);

        jcbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jcbGender.setToolTipText("");
        jcbGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jcbGender.setPreferredSize(new java.awt.Dimension(74, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        signup_details.add(jcbGender, gridBagConstraints);

        btnSignup.setBackground(new java.awt.Color(0, 153, 153));
        btnSignup.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSignup.setText("Sign Up");
        btnSignup.setBorder(null);
        btnSignup.setPreferredSize(new java.awt.Dimension(72, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        signup_details.add(btnSignup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weighty = 1.0;
        signup_detail.add(signup_details, gridBagConstraints);

        signup_content.add(signup_detail, java.awt.BorderLayout.CENTER);

        filler10.setBackground(new java.awt.Color(245, 253, 253));
        signup_content.add(filler10, java.awt.BorderLayout.PAGE_END);

        sign_up.add(signup_content, java.awt.BorderLayout.CENTER);

        add(sign_up, "sign_up_page");
    }// </editor-fold>//GEN-END:initComponents

    private void jtfICnumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfICnumberFocusGained
        // TODO add your handling code here:
        if(jtfICnumber.getText().equals("Enter your identification number")){
            jtfICnumber.setText("");
            jtfICnumber.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtfICnumberFocusGained

    private void jtfICnumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfICnumberFocusLost
        // TODO add your handling code here:
        if(jtfICnumber.getText().equals("")){
            jtfICnumber.setText("Enter your identification number");
            jtfICnumber.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtfICnumberFocusLost

    private void jtfCustomerNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCustomerNameFocusGained
        // TODO add your handling code here:
        if(jtfCustomerName.getText().equals("Enter your full name")){
            jtfCustomerName.setText("");
            jtfCustomerName.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtfCustomerNameFocusGained

    private void jtfCustomerNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCustomerNameFocusLost
        // TODO add your handling code here:
        if(jtfCustomerName.getText().equals("")){
            jtfCustomerName.setText("Enter your full name");
            jtfCustomerName.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtfCustomerNameFocusLost

    private void jtfEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEmailFocusGained
        // TODO add your handling code here:
        if(jtfEmail.getText().equals("Enter your email")){
            jtfEmail.setText("");
            jtfEmail.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtfEmailFocusGained

    private void jtfEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEmailFocusLost
        // TODO add your handling code here:
        if(jtfEmail.getText().equals("")){
            jtfEmail.setText("Enter your email");
            jtfEmail.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtfEmailFocusLost

    private void jtfContactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfContactFocusGained
        // TODO add your handling code here:
        if(jtfContact.getText().equals("Enter your contact number")){
            jtfContact.setText("");
            jtfContact.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtfContactFocusGained

    private void jtfContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfContactFocusLost
        // TODO add your handling code here:
        if(jtfContact.getText().equals("")){
            jtfContact.setText("Enter your contact number");
            jtfContact.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtfContactFocusLost

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) this.getLayout();
        layout.show (this, "sign_up_page");
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String login_email = jtfLoginEmail.getText().trim();
        String login_password = new String(jpfPassword.getPassword());
        
        MyModel user = MyModel.login(login_email, login_password);
        if(user != null){
            JOptionPane.showMessageDialog(this, "Login successfull! Role" + user.getRole());
            
            switch (user.getRole()){
                case "Manager" -> {
                    
                }
                case "Doctor" -> {
                    
                }
                case "Staff" -> {
                    
                }
                default -> {
                    
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private amc.view.comp.AmcPicture amcPicture1;
    private javax.swing.JButton btnForgetPassword;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnSignup;
    private javax.swing.Box.Filler filler10;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> jcbGender;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JTextField jtfContact;
    private javax.swing.JTextField jtfCustomerName;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JTextField jtfICnumber;
    private java.awt.TextField jtfLoginEmail;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblLogin;
    private amc.view.comp.AmcPicture lblLogo;
    private javax.swing.JLabel lblNoAccount;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JPanel logo;
    private javax.swing.JPanel sign_in;
    private javax.swing.JPanel sign_up;
    private javax.swing.JPanel signup_content;
    private javax.swing.JPanel signup_detail;
    private javax.swing.JPanel signup_details;
    // End of variables declaration//GEN-END:variables
}

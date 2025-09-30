package amc.view.share;

import amc.model.DataUtil;
import amc.model.entity.User;
import amc.view.Theme;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.time.LocalDate;

public class LoginPanel extends javax.swing.JPanel {

    public LoginPanel() {
        initComponents();
        this.switch2Login(true);
        this.picLoginUndo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.picSignupUndo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void clearPassword() {
        pwdPassword.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        loginPage = new javax.swing.JPanel();
        picLoginUndo = new amc.view.comp.AmcPicture();
        picLogoinLogo = new amc.view.comp.AmcPicture();
        loginForm = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtLoginEmail = new java.awt.TextField();
        lblPassword = new javax.swing.JLabel();
        pwdPassword = new javax.swing.JPasswordField();
        btnLogin = new amc.view.comp.AmcButton();
        lblNoAccount = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        signupPage = new javax.swing.JPanel();
        picSignupUndo = new amc.view.comp.AmcPicture();
        picSignupLogo = new amc.view.comp.AmcPicture();
        signupForm = new javax.swing.JPanel();
        txtIcNumber = new amc.view.comp.AmcPlaceHolder();
        txtCustomerName = new amc.view.comp.AmcPlaceHolder();
        txtSignupEmail = new amc.view.comp.AmcPlaceHolder();
        txtContact = new amc.view.comp.AmcPlaceHolder();
        cmbGender = new javax.swing.JComboBox<>();
        btnSignup = new amc.view.comp.AmcButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.CardLayout());

        loginPage.setOpaque(false);
        loginPage.setPreferredSize(new java.awt.Dimension(800, 500));
        java.awt.GridBagLayout sign_inLayout = new java.awt.GridBagLayout();
        sign_inLayout.columnWidths = new int[] {200, 200};
        sign_inLayout.columnWeights = new double[] {0.5, 0.5};
        sign_inLayout.rowWeights = new double[] {1.0};
        loginPage.setLayout(sign_inLayout);

        picLoginUndo.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/icon_undo.png"))); // NOI18N
        picLoginUndo.setMaximumSize(new java.awt.Dimension(35, 35));
        picLoginUndo.setMinimumSize(new java.awt.Dimension(35, 35));
        picLoginUndo.setPreferredSize(new java.awt.Dimension(35, 36));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        loginPage.add(picLoginUndo, gridBagConstraints);

        picLogoinLogo.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/logo_clover.png"))); // NOI18N
        picLogoinLogo.set$maxSize(new java.awt.Dimension(350, 350));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        loginPage.add(picLogoinLogo, gridBagConstraints);

        loginForm.setBackground(Theme.C2_BG);
        loginForm.setLayout(new java.awt.GridBagLayout());

        lblLogin.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lblLogin.setForeground(Theme.C2_FG);
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("Login");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 25, 0);
        loginForm.add(lblLogin, gridBagConstraints);

        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblEmail.setForeground(Theme.C2_FG);
        lblEmail.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 25, 10, 10);
        loginForm.add(lblEmail, gridBagConstraints);

        txtLoginEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtLoginEmail.setMaximumSize(new java.awt.Dimension(8, 30));
        txtLoginEmail.setMinimumSize(new java.awt.Dimension(8, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 25);
        loginForm.add(txtLoginEmail, gridBagConstraints);

        lblPassword.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblPassword.setForeground(Theme.C2_FG);
        lblPassword.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 25, 10, 10);
        loginForm.add(lblPassword, gridBagConstraints);

        pwdPassword.setMinimumSize(new java.awt.Dimension(8, 30));
        pwdPassword.setPreferredSize(new java.awt.Dimension(8, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 25);
        loginForm.add(pwdPassword, gridBagConstraints);

        btnLogin.setBackground(Theme.C1_BG);
        btnLogin.setForeground(Theme.C1_FG);
        btnLogin.setText("Log in");
        btnLogin.set$hoverBackground(Theme.C1_BG_SELECT);
        btnLogin.set$hoverForeground(Theme.C1_FG_SELECT);
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLogin.setMaximumSize(new java.awt.Dimension(180, 35));
        btnLogin.setMinimumSize(new java.awt.Dimension(180, 35));
        btnLogin.setPreferredSize(new java.awt.Dimension(180, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        loginForm.add(btnLogin, gridBagConstraints);

        lblNoAccount.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblNoAccount.setForeground(Theme.C2_BG_SELECT);
        lblNoAccount.setText("Don't have an account?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        loginForm.add(lblNoAccount, gridBagConstraints);

        btnRegister.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRegister.setForeground(Theme.C2_FG);
        btnRegister.setText("Click Here Register");
        btnRegister.setBorder(null);
        btnRegister.setBorderPainted(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        loginForm.add(btnRegister, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        loginPage.add(loginForm, gridBagConstraints);

        add(loginPage, "Login");

        signupPage.setOpaque(false);
        signupPage.setPreferredSize(new java.awt.Dimension(800, 500));
        java.awt.GridBagLayout signupPageLayout = new java.awt.GridBagLayout();
        signupPageLayout.columnWeights = new double[] {1.0};
        signupPageLayout.rowWeights = new double[] {0.3, 0.7};
        signupPage.setLayout(signupPageLayout);

        picSignupUndo.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/icon_undo.png"))); // NOI18N
        picSignupUndo.setMaximumSize(new java.awt.Dimension(35, 35));
        picSignupUndo.setMinimumSize(new java.awt.Dimension(35, 35));
        picSignupUndo.setPreferredSize(new java.awt.Dimension(35, 36));
        picSignupUndo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                picSignupUndoMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        signupPage.add(picSignupUndo, gridBagConstraints);

        picSignupLogo.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/logo_clover.png"))); // NOI18N
        picSignupLogo.set$maxSize(new java.awt.Dimension(350, 350));
        picSignupLogo.setMaximumSize(new java.awt.Dimension(100, 200));
        picSignupLogo.setMinimumSize(new java.awt.Dimension(100, 200));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        signupPage.add(picSignupLogo, gridBagConstraints);

        signupForm.setBackground(new java.awt.Color(245, 253, 253));
        signupForm.setForeground(new java.awt.Color(255, 255, 255));
        signupForm.setOpaque(false);
        java.awt.GridBagLayout signup_detailLayout = new java.awt.GridBagLayout();
        signup_detailLayout.columnWidths = new int[] {100, 200, 100};
        signup_detailLayout.columnWeights = new double[] {0.2, 0.6, 0.2};
        signupForm.setLayout(signup_detailLayout);

        txtIcNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txtIcNumber.setForeground(Theme.C1_FG);
        txtIcNumber.set$hint("Enter your IC number");
        txtIcNumber.set$holderColor(Theme.C1_FG_DISABLE);
        txtIcNumber.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtIcNumber.setMaximumSize(new java.awt.Dimension(100, 40));
        txtIcNumber.setMinimumSize(new java.awt.Dimension(100, 40));
        txtIcNumber.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        signupForm.add(txtIcNumber, gridBagConstraints);

        txtCustomerName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txtCustomerName.setForeground(Theme.C1_FG);
        txtCustomerName.set$hint("Enter your full name");
        txtCustomerName.set$holderColor(Theme.C1_FG_DISABLE);
        txtCustomerName.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtCustomerName.setMaximumSize(new java.awt.Dimension(100, 40));
        txtCustomerName.setMinimumSize(new java.awt.Dimension(100, 40));
        txtCustomerName.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        signupForm.add(txtCustomerName, gridBagConstraints);

        txtSignupEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txtSignupEmail.setForeground(Theme.C1_FG);
        txtSignupEmail.set$hint("Enter your email");
        txtSignupEmail.set$holderColor(Theme.C1_FG_DISABLE);
        txtSignupEmail.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtSignupEmail.setMaximumSize(new java.awt.Dimension(100, 40));
        txtSignupEmail.setMinimumSize(new java.awt.Dimension(100, 40));
        txtSignupEmail.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        signupForm.add(txtSignupEmail, gridBagConstraints);

        txtContact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txtContact.setForeground(Theme.C1_FG);
        txtContact.set$hint("Enter your contact number");
        txtContact.set$holderColor(Theme.C1_FG_DISABLE);
        txtContact.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtContact.setMaximumSize(new java.awt.Dimension(100, 40));
        txtContact.setMinimumSize(new java.awt.Dimension(100, 40));
        txtContact.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        signupForm.add(txtContact, gridBagConstraints);

        cmbGender.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        cmbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        cmbGender.setToolTipText("");
        cmbGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        cmbGender.setMaximumSize(new java.awt.Dimension(100, 40));
        cmbGender.setMinimumSize(new java.awt.Dimension(100, 40));
        cmbGender.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        signupForm.add(cmbGender, gridBagConstraints);

        btnSignup.setBackground(Theme.C2_BG);
        btnSignup.setForeground(Theme.C1_FG);
        btnSignup.setText("Sign Up");
        btnSignup.set$hoverBackground(Theme.C2_BG_SELECT);
        btnSignup.set$hoverForeground(Theme.C2_FG_SELECT);
        btnSignup.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        btnSignup.setMaximumSize(new java.awt.Dimension(180, 40));
        btnSignup.setMinimumSize(new java.awt.Dimension(180, 40));
        btnSignup.setPreferredSize(new java.awt.Dimension(180, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        signupForm.add(btnSignup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        signupPage.add(signupForm, gridBagConstraints);

        add(signupPage, "Signup");
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        this.switch2Login(false);
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void picSignupUndoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_picSignupUndoMouseClicked
        // TODO add your handling code here:
        this.switch2Login(true);
    }//GEN-LAST:event_picSignupUndoMouseClicked

    private void resetForm() {
        txtLoginEmail.setText("");
        pwdPassword.setText("");
        txtIcNumber.setText("");
        txtCustomerName.setText("");
        txtSignupEmail.setText("");
        txtContact.setText("");
        cmbGender.setSelectedIndex(0);
    }

    public final void switch2Login(boolean toLogin) {
        this.resetForm();
        ((CardLayout) this.getLayout()).show(this, toLogin ? "Login" : "Signup");
    }

    public final User.LoginContext getLoginContext() {
        return new User.LoginContext(
            DataUtil.formatEmail(txtLoginEmail.getText()),
            new String(pwdPassword.getPassword())
        );
    }

    public final User.SignupContext getSignupContext() {
        return new User.SignupContext(
            txtIcNumber.getText(),
            txtCustomerName.getText(),
            LocalDate.EPOCH,
            DataUtil.formatEmail(txtLoginEmail.getText()),
            txtContact.getText(),
            User.Gender.valueOf(cmbGender.getSelectedItem().toString())
        );
    }

    public void clearEmailContact(boolean kEmail, boolean kContact) {
        if (kEmail) txtSignupEmail.setText("");
        if (kContact) txtContact.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public amc.view.comp.AmcButton btnLogin;
    private javax.swing.JButton btnRegister;
    public amc.view.comp.AmcButton btnSignup;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNoAccount;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JPanel loginForm;
    private javax.swing.JPanel loginPage;
    public amc.view.comp.AmcPicture picLoginUndo;
    private amc.view.comp.AmcPicture picLogoinLogo;
    private amc.view.comp.AmcPicture picSignupLogo;
    private amc.view.comp.AmcPicture picSignupUndo;
    private javax.swing.JPasswordField pwdPassword;
    private javax.swing.JPanel signupForm;
    private javax.swing.JPanel signupPage;
    private amc.view.comp.AmcPlaceHolder txtContact;
    private amc.view.comp.AmcPlaceHolder txtCustomerName;
    private amc.view.comp.AmcPlaceHolder txtIcNumber;
    private java.awt.TextField txtLoginEmail;
    private amc.view.comp.AmcPlaceHolder txtSignupEmail;
    // End of variables declaration//GEN-END:variables
}

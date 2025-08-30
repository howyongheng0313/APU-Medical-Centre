package amc.view.share;

import amc.model.DataUtil;
import amc.model.entity.User;
import amc.view.Theme;
import java.awt.CardLayout;
import java.util.Arrays;

public class LoginPanel extends javax.swing.JPanel {

    public LoginPanel() {
        initComponents();
        this.switch2Login(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        loginPage = new javax.swing.JPanel();
        picLogo1 = new amc.view.comp.AmcPicture();
        loginForm = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtLoginEmail = new java.awt.TextField();
        lblPassword = new javax.swing.JLabel();
        pwdPassword = new javax.swing.JPasswordField();
        btnLogin = new amc.view.comp.AmcButton();
        btnForgot = new javax.swing.JButton();
        lblNoAccount = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        signupPage = new javax.swing.JPanel();
        picLogo2 = new amc.view.comp.AmcPicture();
        signupForm = new javax.swing.JPanel();
        txtIcNumber = new amc.view.comp.AmcPlaceHolder();
        txtCustomerName = new amc.view.comp.AmcPlaceHolder();
        txtSignupEmail = new amc.view.comp.AmcPlaceHolder();
        txtContact = new amc.view.comp.AmcPlaceHolder();
        cmbGender = new javax.swing.JComboBox<>();
        btnSignup = new amc.view.comp.AmcButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.CardLayout());

        loginPage.setOpaque(false);
        loginPage.setPreferredSize(new java.awt.Dimension(800, 500));
        java.awt.GridBagLayout sign_inLayout = new java.awt.GridBagLayout();
        sign_inLayout.columnWidths = new int[] {200, 200};
        sign_inLayout.columnWeights = new double[] {0.5, 0.5};
        sign_inLayout.rowWeights = new double[] {1.0};
        loginPage.setLayout(sign_inLayout);

        picLogo1.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/logo_clover.png"))); // NOI18N
        picLogo1.set$maxSize(new java.awt.Dimension(350, 350));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        loginPage.add(picLogo1, gridBagConstraints);

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

        btnForgot.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnForgot.setForeground(new java.awt.Color(153, 0, 0));
        btnForgot.setText("Forgot Password?");
        btnForgot.setBorder(null);
        btnForgot.setBorderPainted(false);
        btnForgot.setContentAreaFilled(false);
        btnForgot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        loginForm.add(btnForgot, gridBagConstraints);

        lblNoAccount.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblNoAccount.setForeground(Theme.C2_FG_DISABLE);
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

        picLogo2.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/logo_clover.png"))); // NOI18N
        picLogo2.set$maxSize(new java.awt.Dimension(350, 350));
        picLogo2.setMaximumSize(new java.awt.Dimension(100, 200));
        picLogo2.setMinimumSize(new java.awt.Dimension(100, 200));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        signupPage.add(picLogo2, gridBagConstraints);

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
            Arrays.toString(pwdPassword.getPassword())
        );
    }

    public final User.SignupContext getSignupContext() {
        return new User.SignupContext(
            txtIcNumber.getText(),
            txtCustomerName.getText(),
            DataUtil.formatEmail(txtLoginEmail.getText()),
            txtContact.getText(),
            User.Gender.valueOf(cmbGender.getSelectedItem().toString())
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnForgot;
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
    private amc.view.comp.AmcPicture picLogo1;
    private amc.view.comp.AmcPicture picLogo2;
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

package amc.controller.share;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.DataUtil;
import java.awt.event.ActionEvent;

import amc.model.entity.User;
import amc.view.share.LoginPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginCtl extends AbstractSubCtl {
    private final LoginPanel viewLogin = new LoginPanel();

    public LoginCtl(AmcCtl ROOT) {
        super(ROOT);
        viewLogin.btnLogin.addActionListener((ActionEvent evt) -> {
            login();
        });
        viewLogin.btnSignup.addActionListener((ActionEvent evt) -> {
            singup();
        });

        viewLogin.picLoginUndo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                getROOT().popPage(viewLogin);
            }
        });
    }

    public void startView() {
        viewLogin.switch2Login(true);
        getROOT().pushPage(viewLogin);
    }

    private void login() {
        User.LoginContext loginCtx = viewLogin.getLoginContext();
        User logged = User.login(loginCtx);
        
        if (logged == null) {
            viewLogin.clearPassword();
            javax.swing.JOptionPane.showMessageDialog(
                viewLogin,
                "Invalid email or password. Please try again.",
                "Login Failed",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        getROOT().setCurrentUser(logged);
        getROOT().UserChange.fire();
        getROOT().popPage(viewLogin);
    }

    private void singup() {
        User.SignupContext signupCtx = viewLogin.getSignupContext();
        if (
            signupCtx.icNumber().isEmpty() ||
            signupCtx.userName().isEmpty() ||
            signupCtx.email().isEmpty() ||
            !DataUtil.validContact(signupCtx.contact())
        ) {
            viewLogin.clearEmailContact(
                signupCtx.email().isEmpty(),
                !DataUtil.validContact(signupCtx.contact())
            );
            javax.swing.JOptionPane.showMessageDialog(
                viewLogin,
                "Please fill IC number, full name, a valid email, and a valid contact number.",
                "Invalid Signup Details",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        User signed = User.signup(signupCtx);
        if (signed == null) {
            javax.swing.JOptionPane.showMessageDialog(
                viewLogin,
                "Signup failed. The IC or email may already exist.",
                "Signup Failed",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        viewLogin.switch2Login(true);
            }
}

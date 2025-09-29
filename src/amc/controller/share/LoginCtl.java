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
        getROOT().pushPage(viewLogin);
    }

    private void login() {
        User.LoginContext loginCtx = viewLogin.getLoginContext();
        User logged = User.login(loginCtx);
        getROOT().setCurrentUser(logged);
        getROOT().UserChange.fire();
        getROOT().popPage(viewLogin);
    }

    private void singup() {
        User.SignupContext signupCtx = viewLogin.getSignupContext();
        if (
            (signupCtx.icNumber().isEmpty()) ||
            (signupCtx.userName().isEmpty()) ||
            (signupCtx.email().isEmpty()) ||
            DataUtil.validContact(signupCtx.contact())
        ) {
            viewLogin.clearEmailContact(
                signupCtx.email().isEmpty(),
                DataUtil.validContact(signupCtx.contact())
            );
            return;
        }
        User signed = User.signup(signupCtx);
        if (signed == null) return;
        viewLogin.switch2Login(true);
    }
}

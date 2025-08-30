package amc.controller;

import java.awt.event.ActionEvent;

import amc.model.entity.User;
import amc.view.share.LoginPanel;

public class LoginCtl extends AbstractSubCtl {
    private final LoginPanel viewLogin = new LoginPanel();

    protected LoginCtl(AmcCtl ROOT) {
        super(ROOT);
        viewLogin.btnLogin.addActionListener((ActionEvent evt) -> {
            login();
        });
        viewLogin.btnSignup.addActionListener((ActionEvent evt) -> {
        });
        viewLogin.btnForgot.addActionListener((ActionEvent evt) -> {
        });
    }

    public void startView() {
        getROOT().pushPage(viewLogin);
    }

    public void login() {
        User.LoginContext loginCtx = viewLogin.getLoginContext();
        User LoggedUser = User.login(loginCtx);
        getROOT().setCurrentUser(LoggedUser);
        getROOT().UserChange.fire();
        getROOT().popPage(viewLogin);
    }
}

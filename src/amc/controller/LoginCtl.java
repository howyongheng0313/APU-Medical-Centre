package amc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import amc.model.entity.User;
import amc.view.share.LoginPanel;

public class LoginCtl extends AbstractSubCtl {
    private LoginPanel viewLogin = new LoginPanel();

    protected LoginCtl(AmcCtl ROOT) {
        super(ROOT);
        viewLogin.btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                login();
            }
        });
        viewLogin.btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        });
        viewLogin.btnForgot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }
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

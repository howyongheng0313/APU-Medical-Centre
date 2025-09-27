package amc.controller.share;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
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
        });
        viewLogin.btnForgot.addActionListener((ActionEvent evt) -> {
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

    public void login() {
        User.LoginContext loginCtx = viewLogin.getLoginContext();
        User loggedUser = User.login(loginCtx);
        getROOT().setCurrentUser(loggedUser);
        getROOT().UserChange.fire();
        getROOT().popPage(viewLogin);
    }
}

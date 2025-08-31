package amc.controller;

import java.awt.event.ActionEvent;
import amc.model.entity.User;
import amc.view.share.MenuPanel;

public class MenuCtl extends AbstractSubCtl {
    private final MenuPanel viewMenu = new MenuPanel();

    public MenuCtl(AmcCtl ROOT) {
        super(ROOT);
        viewMenu.btnLogin.addActionListener((ActionEvent evt) -> {
            gotoLogin();
        });
        getROOT().UserChange.register(() -> {
            loadUser();
        });
    }

    private void loadUser() {
        User user = getROOT().getCurrentUser();
        if (user == null) {
            viewMenu.switch2Home(true);
            viewMenu.setUserPage(null);
            return;
        }
        UserCtl userCtl = user.getRole().newCtl(getROOT());
        // TO BE CONTINUE
    }

    private void gotoLogin() {
        LoginCtl loginCtl = new LoginCtl(getROOT());
        loginCtl.startView();
    }

    public void startView() {
        getROOT().pushPage(viewMenu);
    }
}

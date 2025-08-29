package amc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import amc.model.entity.Customer;
import amc.model.entity.Manager;
import amc.model.entity.Doctor;
import amc.model.entity.Staff;
import amc.view.share.MenuPanel;

public class MenuCtl extends AbstractSubCtl {
    private final MenuPanel viewMenu = new MenuPanel();

    public MenuCtl(AmcCtl ROOT) {
        super(ROOT);
        viewMenu.btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                gotoLogin();
            }
        });
        getROOT().UserChange.register(()->{
            loadUser();
        });
    }

    private void loadUser() {
        if (getROOT().getCurrentUser() == null) {
            viewMenu.switch2Home(true);
            viewMenu.setUserPage(null);
            return;
        }
//        int a = switch (getROOT().getCurrentUser().getClass()) {
//            case Customer.class -> 0;
//            case Manager.class  -> 0;
//            case Docctor.class  -> 0;
//            case Staff.class    -> 0;
//            default -> 0;
//        };
    }

    private void gotoLogin() {
        LoginCtl loginCtl = new LoginCtl(getROOT());
        loginCtl.startView();
    }

    public void startView() {
        getROOT().pushPage(viewMenu);
    }
}

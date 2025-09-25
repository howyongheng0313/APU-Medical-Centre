package amc.controller.share;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.controller.UserCtl;
import java.awt.event.ActionEvent;
import amc.model.entity.User;
import amc.view.share.BarComp;
import amc.view.share.MenuPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class MenuCtl extends AbstractSubCtl {
    private final MenuPanel viewMenu = new MenuPanel();

    public MenuCtl(AmcCtl ROOT) {
        super(ROOT);
        viewMenu.btnLogin.addActionListener((ActionEvent evt) -> {
            gotoLogin();
        });

        viewMenu.picAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                User logged = getROOT().getCurrentUser();
                if (logged == null) return;
                ProfileCtl profileCtl = new ProfileCtl(
                    getROOT(), logged, logged.getRole().getProfileNode()
                );
                profileCtl.startView();
            }
        });

        getROOT().UserChange.register(() -> {
            loadUser();
        });
    }

    private void loadUser() {
        User user = getROOT().getCurrentUser();
        if (user == null) {
            viewMenu.switch2Home(true);
            viewMenu.setUserPage(null, null);
            return;
        }
        UserCtl  userCtl  = user.getRole().newCtl(getROOT());
        JPanel   viewBody = userCtl.getViewBody();
        BarComp  viewBar  = userCtl.getViewBar();
        viewMenu.setUserPage(viewBody, viewBar);
    }

    private void gotoLogin() {
        LoginCtl loginCtl = new LoginCtl(getROOT());
        loginCtl.startView();
    }

    public void startView() {
        getROOT().pushPage(viewMenu);
    }
}

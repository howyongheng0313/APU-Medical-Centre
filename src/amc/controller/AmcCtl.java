package amc.controller;

import amc.model.EventTrigger;
import amc.model.entity.User;
import amc.view.share.AmcFrame;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class AmcCtl {
    private final AmcFrame viewAmc = new AmcFrame();
    private final List<JPanel> pageLs = new ArrayList<>();
    private final List<String> nameLs = new ArrayList<>();
    private int counter = 0;
    private User currentUser;
    public EventTrigger UserChange = new EventTrigger();

    public AmcCtl() {
        MenuCtl menuCtl = new MenuCtl(this);
        menuCtl.startView();
    }

    public void startView() { viewAmc.setVisible(true); }

    public void pushPage(JPanel page) {
        String name = "pg" + counter++;
        if (!pageLs.isEmpty()) pageLs.getLast().setEnabled(false);

        pageLs.addLast(page);
        nameLs.addLast(name);

        Container viewPane = viewAmc.getContentPane();
        viewPane.add(page, name);
        ((CardLayout) viewPane.getLayout()).show(viewPane, name);
    }

    public void popPage(JPanel page) {
        if (!pageLs.getLast().equals(page)) return;

        Container viewPane = viewAmc.getContentPane();
        viewPane.remove(page);
        pageLs.removeLast();
        nameLs.removeLast();

        if (!pageLs.isEmpty()) {
            pageLs.getLast().setEnabled(true);
            ((CardLayout) viewPane.getLayout()).show(viewPane, nameLs.getLast());
        }
    }

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User currentUser) { this.currentUser = currentUser; }
}

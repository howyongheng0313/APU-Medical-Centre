package amc.controller;

import amc.controller.share.MenuCtl;
import amc.model.EventTrigger;
import amc.model.entity.User;
import amc.view.AmcFrame;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.JPanel;

public class AmcCtl {
    private final AmcFrame viewAmc = new AmcFrame();
    private final LinkedHashMap<JPanel, String> pageMap = new LinkedHashMap<>();
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

        // disable last page if exists
        if (!pageMap.isEmpty()) {
            pageMap.lastEntry().getKey().setEnabled(false);
        }

        pageMap.put(page, name);

        Container viewPane = viewAmc.getContentPane();
        viewPane.add(page, name);
        ((CardLayout) viewPane.getLayout()).show(viewPane, name);
    }

    public void popPage(JPanel page) {
        if (!pageMap.containsKey(page)) return;

        Container viewPane = viewAmc.getContentPane();
        viewPane.remove(page);

        boolean isLast = (page == pageMap.lastEntry().getKey());
        pageMap.remove(page);

        if (isLast && !pageMap.isEmpty()) {
            Entry<JPanel, String> lastEntry = pageMap.lastEntry();
            lastEntry.getKey().setEnabled(true);
            ((CardLayout) viewPane.getLayout()).show(viewPane, lastEntry.getValue());
        }
    }

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User currentUser) { this.currentUser = currentUser; }
}

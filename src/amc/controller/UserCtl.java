package amc.controller;

import amc.view.share.BarPanel;
import javax.swing.JPanel;

public abstract class UserCtl extends AbstractSubCtl {
    public UserCtl(AmcCtl ROOT) {
        super(ROOT);
    }

    public abstract JPanel getViewBody();
    public abstract BarPanel getViewBar();
}

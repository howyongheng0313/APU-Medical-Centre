package amc.controller.staff;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.view.staff.PayingsPanel;
import javax.swing.JPanel;

public class PayingsCtl extends AbstractSubCtl {
    private final PayingsPanel viewPayings = new PayingsPanel();

    public PayingsCtl(AmcCtl ROOT) {
        super(ROOT);
    }

    public JPanel getView() { return viewPayings; }
}

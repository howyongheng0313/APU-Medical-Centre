package amc.controller.staff;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.view.staff.CustomersPanel;
import javax.swing.JPanel;

public class CustomersCtl extends AbstractSubCtl {
    private final CustomersPanel viewCustomers = new CustomersPanel();

    public CustomersCtl(AmcCtl ROOT) {
        super(ROOT);
    }

    public JPanel getView() { return viewCustomers; }
}

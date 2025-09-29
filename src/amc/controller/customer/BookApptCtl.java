package amc.controller.customer;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.view.customer.BookApptPanel;
import javax.swing.JPanel;

public class BookApptCtl extends AbstractSubCtl {
    private final BookApptPanel viewBookAppt = new BookApptPanel();
    public BookApptCtl(AmcCtl ROOT) {
        super(ROOT);
    }

    public JPanel getView() { return viewBookAppt; }
}

package amc.controller.staff;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.view.staff.BookingsPanel;
import javax.swing.JPanel;

public class BookingsCtl extends AbstractSubCtl {
    private final BookingsPanel viewBookings = new BookingsPanel();

    public BookingsCtl(AmcCtl ROOT) {
        super(ROOT);
    }

    public JPanel getView() { return viewBookings; }
}

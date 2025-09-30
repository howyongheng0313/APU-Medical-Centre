package amc.controller.customer;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.view.customer.AppointmentsPanel;
import javax.swing.JPanel;

public class AppointmentsCtl extends AbstractSubCtl {
    private final AppointmentsPanel viewAppointments = new AppointmentsPanel();
    public AppointmentsCtl(AmcCtl ROOT) {
        super(ROOT);
    }

    public JPanel getView() { return viewAppointments; }
}

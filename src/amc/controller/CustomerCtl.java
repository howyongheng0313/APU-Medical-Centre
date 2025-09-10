package amc.controller;

import amc.view.customer.CustomerPanel;
import amc.view.share.BarButton;
import amc.view.share.BarComp;
import javax.swing.JPanel;

public class CustomerCtl extends UserCtl {
    private final BarButton appointmentsBarBtn = new BarButton("Appointments", "Appointments");
    private final BarButton bookApptBarBtn     = new BarButton("Book Appt"   , "BookAppt");
    private final CustomerPanel viewBody = new CustomerPanel(); 
    private final BarComp       viewBar  = new BarComp(viewBody, 
        appointmentsBarBtn,
        bookApptBarBtn
    );

    public CustomerCtl(AmcCtl ROOT) {
        super(ROOT);
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

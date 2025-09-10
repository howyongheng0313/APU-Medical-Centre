package amc.controller;

import amc.view.share.BarButton;
import amc.view.share.BarComp;
import amc.view.staff.StaffPanel;
import javax.swing.JPanel;

public class StaffCtl extends UserCtl {
    private final BarButton bookingsBarBtn  = new BarButton("Bookings" , "Booking");
    private final BarButton customersBarBtn = new BarButton("Customers", "Customers");
    private final BarButton payingsBarBtn   = new BarButton("Payings"  , "Payings");
    private final StaffPanel viewBody = new StaffPanel();
    private final BarComp    viewBar  = new BarComp(viewBody,
        bookingsBarBtn,
        customersBarBtn,
        payingsBarBtn
    );

    public StaffCtl(AmcCtl ROOT) {
        super(ROOT);
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

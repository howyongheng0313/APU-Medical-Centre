package amc.controller;

import amc.controller.staff.*;
import amc.view.share.BarButton;
import amc.view.share.BarComp;
import amc.view.share.UserPanel;
import javax.swing.JPanel;

public class StaffCtl extends UserCtl {
    private final BarButton bookingsBarBtn  = new BarButton("Bookings" , "Bookings");
    private final BarButton customersBarBtn = new BarButton("Customers", "Customers");
    private final BarButton payingsBarBtn   = new BarButton("Payings"  , "Payings");
    private final UserPanel viewBody = new UserPanel();
    private final BarComp   viewBar  = new BarComp(viewBody,
        bookingsBarBtn,
        customersBarBtn,
        payingsBarBtn
    );

    public StaffCtl(AmcCtl ROOT) {
        super(ROOT);
        BookingsCtl  bookingsCtl  = new BookingsCtl(getROOT());
        CustomersCtl customersCtl = new CustomersCtl(getROOT());
        PayingsCtl   payingsCtl   = new PayingsCtl(getROOT());
        viewBody.add(bookingsCtl.getView() , "Bookings");
        viewBody.add(customersCtl.getView(), "Customers");
        viewBody.add(payingsCtl.getView()  , "Payings");
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

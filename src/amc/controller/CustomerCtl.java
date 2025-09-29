package amc.controller;

import amc.controller.customer.*;
import amc.view.share.BarButton;
import amc.view.share.BarComp;
import amc.view.share.UserPanel;
import javax.swing.JPanel;

public class CustomerCtl extends UserCtl {
    private final BarButton appointmentsBarBtn = new BarButton("Appointments", "Appointments");
    private final BarButton bookApptBarBtn     = new BarButton("Book Appt"   , "BookAppt");
    private final UserPanel viewBody = new UserPanel(); 
    private final BarComp   viewBar  = new BarComp(viewBody, 
        appointmentsBarBtn,
        bookApptBarBtn
    );

    public CustomerCtl(AmcCtl ROOT) {
        super(ROOT);
        AppointmentsCtl appointmentsCtl = new AppointmentsCtl(getROOT());
        BookApptCtl     bookApptCtl     = new BookApptCtl(getROOT());
        viewBody.add(appointmentsCtl.getView(), "Appointments");
        viewBody.add(bookApptCtl.getView()    , "BookAppt");
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

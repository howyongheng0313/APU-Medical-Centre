package amc.controller;

import amc.controller.customer.AppointmentCtl;
import amc.controller.customer.BookAppointmentCtl;
import amc.view.share.BarButton;
import amc.view.share.BarComp;
import amc.view.share.UserPanel;
import javax.swing.JPanel;

public class CustomerCtl extends UserCtl {
    private final BarButton appointmentsBarBtn = new BarButton("Appointments", "Appointments");
    private final BarButton bookApptBarBtn = new BarButton("Book Appt", "BookAppt");
    private final UserPanel viewBody = new UserPanel(); 
    private final BarComp viewBar = new BarComp(viewBody, 
        appointmentsBarBtn,
        bookApptBarBtn
    );

    public CustomerCtl(AmcCtl ROOT) {
        super(ROOT);

        AppointmentCtl appointmentCtl = new AppointmentCtl(getROOT());
        viewBody.add(appointmentCtl.getView(), "Appointments");

        BookAppointmentCtl bookingCtl = new BookAppointmentCtl(getROOT());
        viewBody.add(bookingCtl.getView(), "BookAppt");
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

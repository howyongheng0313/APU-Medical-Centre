package amc.controller;

import amc.view.share.BarButton;
import amc.view.share.BarComp;
import amc.view.share.UserPanel;
import javax.swing.JPanel;

public class DoctorCtl extends UserCtl {
    private final BarButton appointmentsBarBtn = new BarButton("Appointments", "Appointments");
    private final UserPanel viewBody = new UserPanel();
    private final BarComp   viewBar  = new BarComp(viewBody,
        appointmentsBarBtn
    );

    public DoctorCtl(AmcCtl ROOT) {
        super(ROOT);
        AppointmentCtl appointmentCtl = new AppointmentCtl(getROOT());  
        
        viewBody.add(appointmentCtl.getView(), "Appointments");
    }
    
    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

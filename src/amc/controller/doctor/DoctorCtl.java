package amc.controller.doctor;

import amc.controller.AmcCtl;
import amc.controller.UserCtl;
import amc.controller.doctor.AppointmentDoctorCtl;
import amc.model.entity.Doctor;
import amc.model.entity.User;
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
        User currentUser = (User) ROOT.getCurrentUser();
        Doctor currentDoctor = (Doctor) ROOT.getCurrentUser();
        AppointmentDoctorCtl appointmentCtl = new AppointmentDoctorCtl(ROOT, currentUser, currentDoctor);  
        viewBody.add(appointmentCtl.getView(), "Appointments");
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

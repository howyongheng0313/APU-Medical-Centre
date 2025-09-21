package amc.controller;
import amc.view.doctor.Appointment;
import amc.model.entity.User;
import amc.view.doctor.MainPageDoctor;
import javax.swing.JPanel;

public class AppointmentCtl extends AbstractSubCtl {
    private final  MainPageDoctor viewAppointment;
    
    public AppointmentCtl(AmcCtl ROOT) {
        
        super(ROOT);
        User currentUser = getROOT().getCurrentUser();
        this.viewAppointment = new MainPageDoctor(ROOT,currentUser);
        
    }
    
    public JPanel getView() {
        return viewAppointment;
    }
}

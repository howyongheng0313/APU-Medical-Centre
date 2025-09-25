/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amc.controller.Doctor;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.entity.Doctor;
import amc.model.entity.User;
import amc.view.doctor.MainPageDoctor;

/**
 *
 * @author Administrator
 */
public class AppointmentDoctorCtl extends AbstractSubCtl{
    private final MainPageDoctor viewAppointments;
    
    public AppointmentDoctorCtl(AmcCtl ROOT, User currentUser, Doctor currentDoctor) {
        super(ROOT);
        this.viewAppointments = new MainPageDoctor(ROOT, currentUser, currentDoctor);
    }

    public MainPageDoctor getView() {
        return viewAppointments;
    }
}

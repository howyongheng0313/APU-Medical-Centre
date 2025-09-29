/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amc.view.doctor;

/**
 *
 * @author Administrator
 */
public class FileLocation {
    private final String ServiceFile = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Assignment\\APU-Medical-Centre\\database\\Service.txt";
    private final String AppointmentFile = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Assignment\\APU-Medical-Centre\\database\\Appointment.txt";
    private final String MedicineFile = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Assignment\\APU-Medical-Centre\\database\\Medicine.txt";
    private final String AppointmentMedicineFile = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Assignment\\APU-Medical-Centre\\database\\ApptMedicine.txt";
    private final String AppointmentServiceFile = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Assignment\\APU-Medical-Centre\\database\\ApptService.txt";

    public String getAppointmentServiceFile() {
        return AppointmentServiceFile;
    }
    public String getAppointmentMedicineFile() {
        return AppointmentMedicineFile;
    }
    
    public String getServiceFile() {
        return ServiceFile;
    }

    public String getAppointmentFile() {
        return AppointmentFile;
    }

    public String getMedicineFile() {
        return MedicineFile;
    }
}

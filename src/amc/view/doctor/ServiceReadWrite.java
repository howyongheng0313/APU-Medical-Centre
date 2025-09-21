/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amc.view.doctor;

import amc.model.entity.Service;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;

/**
 *
 * @author Administrator
 */
public class ServiceReadWrite {
    FileLocation fl = new FileLocation();
    FileReaderWriter frw = new FileReaderWriter();
    
    public void writeApptService (JList<?> listName, String appointmentId){
        List<?> selectedRow = listName.getSelectedValuesList();
        List<String> columnToWrite = new ArrayList<>();
        for (Object row : selectedRow){
            if (row instanceof Service service){
                String serviceId = service.getServiceId();
                double servicePrice = service.getFee();
                columnToWrite.add(appointmentId + "|" + serviceId + "|" + "MYR" +servicePrice);
            }
        }
        frw.writeFile(fl.getAppointmentServiceFile(), columnToWrite);
    }
}

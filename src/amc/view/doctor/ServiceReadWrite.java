/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amc.view.doctor;

import amc.model.entity.Service;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import amc.model.entity.Doctor;
import javax.swing.DefaultListModel;
/**
 *
 * @author Administrator
 */
public class ServiceReadWrite {
    FileLocation fl = new FileLocation();
    FileReaderWriter frw = new FileReaderWriter();
    private final Doctor currentDoctor;
    DefaultListModel<String> listModel = new DefaultListModel<>();
    
    public ServiceReadWrite(Doctor currentDoctor){
        this.currentDoctor = currentDoctor;
    }
    
    public void listAllService(JList<String> listName) {
        List<String[]> readRows = frw.readFile(fl.getServiceFile());
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (String[] row : readRows) {
            String departmentId = row[3];  
            if (departmentId.equals(currentDoctor.getDepartmentId())) {
                String rowString = String.join(" | ", row);
                listModel.addElement(rowString);
            }
        }
        listName.setModel(listModel);
    }
    
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

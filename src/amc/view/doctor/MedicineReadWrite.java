/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amc.view.doctor;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class MedicineReadWrite {
    FileLocation fl = new FileLocation();
    FileReaderWriter frw = new FileReaderWriter();
    
    DefaultListModel<String> listModel = new DefaultListModel<>();
    
    public void listAllMedicine(JList<String> listName){
        List<String[]> readRows = frw.readFile(fl.getMedicineFile());
        for(String[] row : readRows){
            String rowString = String.join(" | ", row);
            listModel.addElement(rowString);
            listName.setModel(listModel);
        }
    }
    
    public void writeApptMedicine (JList<String> listName, JTextField txtFieldName, String appointmentId){
        List<String> selectedRow = listName.getSelectedValuesList();
        List<String> columnToWrite = new ArrayList<>();
        int amount = Integer.parseInt(txtFieldName.getText());
        for (String row : selectedRow){
            String[] columns = row.split("\\|");
            String medicineId = columns[0].trim();
            String medicinePrice = columns[2].trim();
            columnToWrite.add(appointmentId + "|" + medicineId + "|" + medicinePrice + "|" + amount);
        }
        frw.writeFile(fl.getAppointmentMedicineFile(), columnToWrite);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amc.view.doctor;
import java.awt.TextArea;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class FeedbackReadWrite {
    FileLocation fl = new FileLocation();
    FileReaderWriter frw = new FileReaderWriter();
    DefaultListModel<String> listModel = new DefaultListModel<>();
    
    
    public void listFeedBack(JList listName){
        List<String[]> readRows = frw.readFile(fl.getAppointmentFile());
        for(String[] row : readRows){
            String rowString = String.join(" | ", row);
            listModel.addElement(rowString);
        }
        listName.setModel(listModel);       
    }
    
    public void writeApptFeedback(TextArea txtAreaName, String appointmentId) {
        List<String[]> readRows = frw.readFile(fl.getAppointmentFile());
        List<String> updatedRows = new ArrayList<>();
        String feedback = txtAreaName.getText().trim();
        updatedRows.add("SEQ");
        if (feedback.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter feedback.");
            return;
        }

        for (String[] row : readRows) {
            if (row[0].trim().equals(appointmentId) && row.length <= 9) {
                if (row.length == 9) {
                row[8] = feedback;
                updatedRows.add(String.join("|", row));
                }
                else{
                    String updatedRow = String.join("|", row) + "|" + feedback;
                    updatedRows.add(updatedRow);
                }
                
            }
            else {
                updatedRows.add(String.join("|", row));
            }
        }
        frw.overWriteFile(fl.getAppointmentFile(), updatedRows);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amc.view.doctor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class Appointment {
    private String Appointment_ID = null;
    private String Customer_ID = null;
    private String Customer_Name = null;
    private String Doctor_ID = null;
    private String doctorName = null;
    private String departmentID = null;
    private String departmentName = null;
    private String staffID = null;
    private String staffName = null;
    private LocalDate appointmentDate = null;
    private String appointmentTime = null;
    private String services = null;
    private String appointmentReason = null;
    private String appointmentFeedback = null;
    private String prescription = null;
    private String appointmentStatus = null;
    private String filepath = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Assignment\\APU-Medical-Centre\\src\\amc\\view\\doctor\\AptTest";
   
    public void make_appointment(String customer_ID, String customer_Name, LocalDate appointment_Date, String appointment_Time, String appointment_Reason){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))){
                this.Appointment_ID = "APT001";
                this.Customer_ID = customer_ID;
                this.Customer_Name = customer_Name;
                this.appointmentDate = appointment_Date;
                    this.appointmentTime = appointment_Time;
                    this.appointmentReason = appointment_Reason;
                    String aptdetail = Appointment_ID + "," +
                    Customer_ID + "," +
                    Customer_Name + "," +
                    Doctor_ID + "," +
                    doctorName + "," +
                    departmentID + "," +
                    departmentName + "," +
                    staffID + "," +
                    staffName + "," +
                    appointmentDate + "," +
                    appointmentTime + "," +
                    services + "," +
                    appointmentReason + "," +
                    appointmentFeedback + "," +
                    prescription + "," +
                    appointmentStatus;
                    writer.append(aptdetail);
                    writer.newLine();
                    System.out.println(aptdetail);
                }
            
                catch(IOException e){
                    System.out.println("Error writing");
                }
            }
    
    public void view_appointment(JTable tableName){
        DefaultTableModel model = new DefaultTableModel();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = reader.readLine()) != null){
                String[] rows = line.split(",");
                model.addRow(rows);
            }
        }
        catch(IOException e){
            System.out.println("Error reading file");
        }
        tableName.setModel(model);
    }
}

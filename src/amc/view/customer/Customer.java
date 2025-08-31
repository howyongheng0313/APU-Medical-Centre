/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amc.view.customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class Customer {
    private String Customer_Name;
    private String Customer_ID;
    private int Contact;
    private String Email;
    private String Password;
    private String Status;

    public void update_profile(String Customer_Name, String Customer_ID, int Conatct, String Email, String Password) {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Contact = Contact;
        this.Email = Email;
        this.Password = Password;
        
        ////////////////
    }

    public void view_complete_appointment(JTable jTable1){
        String filepath = "filepath";
        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[] {
            "Customer_ID", "Date", "Time", "Status"
        });
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
                    String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length >= 8 && row[7].trim().equalsIgnoreCase("Completed")) {
                    table.addRow(row);
                }
            }
        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());

        }
        //jTable1.setModel(table);
    }
        
    public void view_incomplete_appointment(JTable jTable1){
        String filepath = "filepath";
        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[] {
            "Customer_ID", "Date", "Time", "Status"
        });
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
                    String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length >= 8 && row[7].trim().equalsIgnoreCase("Incomplete")) {
                    table.addRow(row);
                }
            }
        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());

        }
        //jTable1.setModel(table);
    }

    public void book_appointment(JTable table, JTextField customerID_txt){
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();

                customerID_txt.setText(table.getValueAt(row, 0).toString());
                //txtDoctorName.setText(table.getValueAt(row, 1).toString());
                //txtStaffName.setText(table.getValueAt(row, 2).toString());
                //txtService.setText(table.getValueAt(row, 3).toString());
                //txtPrescription.setText(table.getValueAt(row, 4).toString());
                //txtFee.setText(table.getValueAt(row, 5).toString());
            }
        });
    }
}

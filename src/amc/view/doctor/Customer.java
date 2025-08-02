
package amc.view.doctor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Customer {
    private String Customer_ID;
    private String Customer_Name;
    private String Email;
    private String Password;
    private int Customer_Contact;
    String filepath = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\Customer.txt";
    Appointment apt = new Appointment();
    public void filereader(){
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        catch(IOException e){
            System.out.println("Error.");
        }
    }
    
    public void delete_customer(String customer_id){
        List<String> updatedLines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if(!parts[0].trim().equals(customer_id)){
                    updatedLines.add(line);
                }
            }
            System.out.println(updatedLines);
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
                for(String lines : updatedLines){
                    System.out.println(lines);
                    writer.append(lines + "\n");
                }
            }
            catch(IOException e){
                System.out.println("Error deleting customer.");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        catch(IOException e){
            System.out.println("Error.");
        }
    }


    public void login(String Email, String Password){

        boolean userFound = false;
        try(BufferedReader reader = new BufferedReader (new FileReader(filepath))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts[2].trim().equals(Email) && parts[3].trim().equals(Password)){
                    System.out.println("Login successful.");
                    this.Customer_ID = parts[0].trim();
                    this.Customer_Name = parts[1].trim();
                    this.Customer_Contact = Integer.parseInt(parts[4].trim());
                    this.Email = Email;
                    this.Password = Password;
                    System.out.println(Customer_ID);
                    userFound = true;
                    break;
                }
            }
            if(!userFound){
                System.out.println("Invalid Email or password.");
            }
        }
        catch(IOException e){
            System.out.println("Error.");
        }
        }
    
    
    public void update_customer(String customer_id, String customer_name, String email, String password, int Contact){
        List<String> updatedLines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if(!parts[0].trim().equals(Customer_ID)){
                    updatedLines.add(line);
                }
                else{
                    this.Customer_ID = customer_id;
                    this.Customer_Name = customer_name;
                    this.Email = email;
                    this.Password = password;
                    this.Customer_Contact = Contact;
                    String new_details = (Customer_ID+","+Customer_Name+","+","+Email+","+Password+","+Customer_Contact);
                    updatedLines.add(new_details);
                }
            }
            System.out.println(updatedLines);
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
                for(String lines : updatedLines){
                    System.out.println(lines);
                    writer.append(lines + "\n");
                }
            }
            catch(IOException e){
                System.out.println("Error updating customer details.");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        catch(IOException e){
            System.out.println("Error.");
        }
    }
    
    public void test(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Update profile");
        System.out.println("2. Delete user");
        System.out.println("3. Make Appointment");
        System.out.println("Enter a function:");
        int select = sc.nextByte();
        if(select ==2){
            System.out.println("Enter id:");
            String ID = sc.next();
            delete_customer(ID);
        }
        else if(select ==1){
            System.out.println("Enter new id:");
            String ID = sc.next();
            System.out.println("Enter new name:");
            String name = sc.next();
            System.out.println("Enter new email:");
            String mail = sc.next();
            System.out.println("Enter new password:");
            String pass = sc.next();
            System.out.println("Enter new contact:");
            String contact = sc.next();
            update_customer(contact, name, mail, pass, select);
        }
        else if(select == 3){
            System.out.println("Enter Date:");
            String date = sc.next();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dates = LocalDate.parse(date,format);
            
            System.out.println("Enter Time:");
            String time = sc.next();
            System.out.println("Enter Reason:");
            String reason = sc.next();
            apt.make_appointment(Customer_ID, Customer_Name, dates, time, reason);
        
        }
    
        
        else{
            System.out.println("Error");
        }
    }
}


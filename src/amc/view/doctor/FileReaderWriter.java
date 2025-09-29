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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class FileReaderWriter {
    private String line;
    
    public List<String[]> readFile(String filepath) {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader (new FileReader(filepath))){
            line = br.readLine();
            while ((line = br.readLine()) != null){
                rows.add(line.split("\\|"));
            }
            return rows;
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error reading file.");
            return null;
        }
    }
    
    public void writeFile(String filepath, List<String> rows) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true))) {
            for (String row : rows) {
                bw.write(row);
                bw.newLine();
            }
            JOptionPane.showMessageDialog(null, "File Written.");
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error writing to file.");
        }
    }
    
    public void overWriteFile(String filepath, List<String> rows) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, false))) {
            for (String row : rows) {
                bw.write(row);
                bw.newLine();
            }
            JOptionPane.showMessageDialog(null, "File Written.");
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error writing to file.");
        }
    }
}


package amc.view.doctor;

import amc.model.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class AppointmentReadWrite {
    private final String doctorId;
    FileLocation fl = new FileLocation();
    FileReaderWriter frw = new FileReaderWriter();
        
    public AppointmentReadWrite(User currentUser) {
        this.doctorId = currentUser.getUserId();
    }

    public void view_appointment(JTable tableName, String status) {
        boolean view_current = status.equalsIgnoreCase("current");
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "Appointment_ID", "Customer_ID", "Doctor_ID", "Staff_ID",
            "Department_ID", "Date", "Time", "Status", "Doctor_Feedback"
        });

        List<String[]> readRows = frw.readFile(fl.getAppointmentFile());
        if (readRows == null) {
            return;
        }

        for (String[] row : readRows) {
            if (row.length >= 8) {
                String statusCode = row[7].trim();
                String rowDoctorId = row[2].trim();

                if (row.length == 8) {
                    row = new String[]{
                        row[0], row[1], row[2], row[3], row[4],
                        row[5], row[6], row[7], ""
                    };
                }
                if (rowDoctorId.equals(doctorId)) {
                    boolean match =
                        (view_current && statusCode.equals("1")) ||
                        (!view_current && !statusCode.equals("1"));

                    if (match) {
                        row[7] = mapStatus(statusCode);
                        model.addRow(row);
                    }
                }
            }
        }
        tableName.setModel(model);
    }

    public void view_appt_details(String AppointmentId, JTable tableName){
        List<String[]> readAptMedicine = frw.readFile(fl.getAppointmentMedicineFile());
        List<String[]> readAptService = frw.readFile(fl.getAppointmentServiceFile());
        List<String[]> readAptFeedback = frw.readFile(fl.getAppointmentFile());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "Appointment_ID", "Service", "Medicine", "Feedback"
        });
        
        List<String> medicines = new ArrayList<>();
        for (String[] m : readAptMedicine){
            if(m[0].equals(AppointmentId)){
                medicines.add(m[1]);
            }
        }
        
        List<String> services = new ArrayList<>();
        for (String[] s : readAptService) {
            if (s[0].equals(AppointmentId)) {
                services.add(s[1]);
            }
        }
        String feedback = "";
        for (String[] f : readAptFeedback) {
            if (f[0].equals(AppointmentId)) {
                feedback = f[8];
                break;
            }
        }
        model.addRow(new Object[]{
        AppointmentId,
        String.join(", ", services),
        String.join(", ", medicines),
        feedback
        });
        
        tableName.setModel(model);
    }
    
    private String mapStatus(String status) {
        switch (status) {
            case "-1": return "Cancelled";
            case "1":  return "Booked";
            case "2":  return "Completed";
            case "3":  return "Paid";
            default:   return "Unknown";
        }
    }
}

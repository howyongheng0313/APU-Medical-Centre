
package amc.view.doctor;

import amc.model.entity.User;
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
            return; // error already shown by FileReaderWriter
        }

        for (String[] row : readRows) {
            if (row.length >= 8) {
                String statusCode = row[7].trim();
                String rowDoctorId = row[2].trim();

                // Ensure 9 columns (feedback empty if missing)
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
                        // Map status code to readable text
                        row[7] = mapStatus(statusCode);
                        model.addRow(row);
                    }
                }
            }
        }
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

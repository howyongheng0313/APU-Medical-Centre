
package amc.view.doctor;

import amc.model.db_impl.Db;
import amc.model.entity.Appointment;
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
        
    public AppointmentReadWrite(User currentUser) {
        this.doctorId = currentUser.getId();
    }

    public void view_appointment(JTable table, boolean currentOnly) {
        DefaultTableModel tbModel = new DefaultTableModel();
        tbModel.setColumnIdentifiers(new String[]{
            "Appointment_ID", "Customer_ID", "Doctor_ID", "Staff_ID",
            "Department_ID", "Date", "Time", "Status", "Doctor_Feedback"
        });

        List<Appointment> apptLs = Db.Appointment.select(-1, model -> {
            return doctorId.equals(model.getDoctorId()) && (
                currentOnly && model.getStatus() == Appointment.Status.Booked ||
                !currentOnly && model.getStatus() != Appointment.Status.Booked
            );
        });

        for (Appointment appt: apptLs) {
            tbModel.addRow(new String[] {
                appt.getId(),
                appt.getCustomerId(),
                appt.getDoctorId(),
                appt.getStaffId(),
                appt.getDepartmentId(),
                appt.getDateTime().toLocalDate().toString(),
                appt.getDateTime().toLocalTime().toString(),
                appt.getStatus().toString(),
                appt.getFeedback()
            });
        }
        table.setModel(tbModel);
    }
}

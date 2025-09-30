package amc.controller.customer;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.db_impl.Db;
import amc.model.entity.Appointment;
import amc.model.entity.Customer;
import amc.model.entity.Department;
import amc.model.entity.User;
import amc.view.customer.BookingPanel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BookAppointmentCtl extends AbstractSubCtl {
    private final BookingPanel BookAppointment = new BookingPanel();

    public BookAppointmentCtl(AmcCtl ROOT) {
        super(ROOT);
        
        // Wire the button to call bookAppointment
        BookAppointment.addPropertyChangeListener("bookAppointment", evt -> {
            bookAppointment(
                BookAppointment.getPatientName(),
                BookAppointment.getSelectedDepartmentName(),
                BookAppointment.getSelectedDateStr(),
                BookAppointment.getSelectedTimeStr()
            );
        });
    }
   

    public void bookAppointment(String name, String departmentName, String dateStr, String timeStr) {
        // Must be logged in as Customer
        User u = getROOT().getCurrentUser();
        if (!(u instanceof Customer c)) {
            JOptionPane.showMessageDialog(BookAppointment, "Please login as a customer first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Basic validation
        if (name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(BookAppointment, "Name is required.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (departmentName == null || departmentName.isBlank()) {
            JOptionPane.showMessageDialog(BookAppointment, "Department is required.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (dateStr == null || dateStr.isBlank()) {
            JOptionPane.showMessageDialog(BookAppointment, "Date is required (yyyy-MM-dd).", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (timeStr == null || timeStr.isBlank()) {
            JOptionPane.showMessageDialog(BookAppointment, "Time is required (HH:mm:ss).", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Parse date/time (Appointment.txt uses yyyy-MM-dd and HH:mm:ss)
            LocalDate date = LocalDate.parse(dateStr);
            LocalTime time = LocalTime.parse(timeStr);

            // Resolve Department ID by name
            String departmentId = findDepartmentIdByName(departmentName);
            if (departmentId == null) {
                throw new IllegalArgumentException("Invalid department selected.");
            }

            // Generate new appointment ID
            String newApptId = generateNewAppointmentId();

            // Build and insert appointment; use current user's ID (customerId)
            Appointment appt = new Appointment(
                newApptId,
                c.getId(),
                departmentId,
                LocalDateTime.of(date, time),
                Appointment.Status.Booked
            );

            boolean ok = Db.Appointment.insert(List.of(appt));
            if (!ok) {
                throw new IllegalStateException("Failed to write appointment.");
            }

            JOptionPane.showMessageDialog(BookAppointment, "Booked successfully. ID: " + newApptId, "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(BookAppointment, "Invalid date/time format. Use yyyy-MM-dd and HH:mm:ss", "Invalid Format", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(BookAppointment, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (java.awt.HeadlessException ex) {
            JOptionPane.showMessageDialog(BookAppointment, "Booking failed: " + String.valueOf(ex.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String findDepartmentIdByName(String deptName) {
        List<Department> depts = Db.Department.select(1, d -> d.getDepartmentName().equals(deptName));
        return depts.isEmpty() ? null : depts.get(0).getId();
    }

    private String generateNewAppointmentId() {
        List<Appointment> all = Db.Appointment.select(-1, a -> true);
        int max = 0;
        for (Appointment a : all) {
            String id = a.getId();
            if (id != null && id.startsWith("APT-")) {
                try {
                    int n = Integer.parseInt(id.substring(4));
                    if (n > max) max = n;
                } catch (NumberFormatException ignore) {
                    // Skip invalid IDs
                }
            }
        }
        return String.format("APT-%03d", max + 1);
    }
    
    public JPanel getView() { return BookAppointment;}
}
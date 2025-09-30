package amc.controller;

import amc.model.entity.*;
import amc.model.db_impl.Db;
import amc.view.customer.ViewApptPanel;
import amc.view.customer.DetailApptPanel;
import java.util.*;
import java.time.format.DateTimeFormatter;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class AppointmentCtl extends AbstractSubCtl {
    
    private boolean isPopulatingTable = false;
    private boolean isViewingCompleted = false;
    private boolean allowRowClicking = false;
    private final ViewApptPanel viewAppointment = new ViewApptPanel();
    private final DetailApptPanel detailAppointment = new DetailApptPanel();
    private final DefaultTableModel tableModel = new DefaultTableModel(
        new Object[] { "ID", "Department", "Date", "Time", "Status", "Doctor", "Staff", "Feedback" }, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
   

    public AppointmentCtl(AmcCtl ROOT) { 
        super(ROOT);

        // Set up the table model
        viewAppointment.getJTable1().setModel(tableModel);
        viewAppointment.getIncompleteButton().addActionListener(e -> loadIncompleteAppointments());
        viewAppointment.getCompletedButton().addActionListener(e -> loadCompletedAppointments());
        viewAppointment.getCancelledButton().addActionListener(e -> loadCancelledAppointments());
        
        // Add row click listener for navigation to detail view
        viewAppointment.getJTable1().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        viewAppointment.getJTable1().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !isPopulatingTable && isViewingCompleted && allowRowClicking) { 
                // Only allow clicking when viewing completed appointments AND row clicking is allowed
                int selectedRow = viewAppointment.getJTable1().getSelectedRow();
                if (selectedRow >= 0) {
                    String appointmentId = (String) tableModel.getValueAt(selectedRow, 0);
                    showAppointmentDetails(appointmentId);
                }
            }
        });

        // Wire back button in detail panel
        detailAppointment.getBackButton().addActionListener(e -> showAppointmentsView());
        reload();
    }

    private void showAppointmentDetails(String appointmentId) {
        // Get appointment details
        List<Appointment> appointments = Db.Appointment.select(1, a -> a.getId().equals(appointmentId));
        if (appointments.isEmpty()) return;
        
        Appointment appointment = appointments.get(0);
        
        // Get related data
        String services = getServicesForAppointment(appointmentId);
        String prescription = getPrescriptionForAppointment(appointmentId);
        String totalFees = getTotalFeesForAppointment(appointmentId);
        String patientName = getPatientName(appointment.getCustomerId());
        
        // Populate detail panel
        detailAppointment.setAppointmentId(appointmentId);
        detailAppointment.setDate(appointment.getDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        detailAppointment.setPatientName(patientName);
        detailAppointment.setDoctorId(appointment.getDoctorId() != null ? appointment.getDoctorId() : "");
        detailAppointment.setStaffId(appointment.getStaffId() != null ? appointment.getStaffId() : "");
        detailAppointment.setServices(services);
        detailAppointment.setPrescription(prescription);
        detailAppointment.setTotalFees(totalFees);
        detailAppointment.setDepartment(appointment.getDepartmentId());
        
        // Switch to detail view
        switchToDetailView();
    }
    
    private void showAppointmentsView() {
        switchToAppointmentsView();
        // Clear table selection to allow clicking again
        viewAppointment.getJTable1().clearSelection();
        allowRowClicking = true; // Re-enable row clicking when returning
    }
    
    private void switchToDetailView() {
        getROOT().pushPage(detailAppointment);
    }
    
    private void switchToAppointmentsView() {
        getROOT().popPage(detailAppointment);
    }

    private String getServicesForAppointment(String appointmentId) {
        List<ApptService> services = Db.ApptService.select(-1, s -> s.getAppointmentId().equals(appointmentId));
        if (services.isEmpty()) return "No services";
        
        StringBuilder serviceList = new StringBuilder();
        for (ApptService service : services) {
            // Get service name from Service table
            List<Service> serviceDetails = Db.Service.select(1, s -> s.getId().equals(service.getServiceId()));
            if (!serviceDetails.isEmpty()) {
                if (serviceList.length() > 0) serviceList.append(", ");
                serviceList.append(serviceDetails.get(0).getServiceName());
            }
        }
        return serviceList.toString();
    }
    
    private String getPrescriptionForAppointment(String appointmentId) {
        List<ApptMedicine> medicines = Db.ApptMedicine.select(-1, m -> m.getAppointmentId().equals(appointmentId));
        if (medicines.isEmpty()) return "No prescription";
        
        StringBuilder prescription = new StringBuilder();
        for (ApptMedicine medicine : medicines) {
            // Get medicine name from Medicine table
            List<Medicine> medicineDetails = Db.Medicine.select(1, m -> m.getId().equals(medicine.getMedicineId()));
            if (!medicineDetails.isEmpty()) {
                if (prescription.length() > 0) prescription.append(", ");
                prescription.append(medicineDetails.get(0).getMedicineName());
            }
        }
        return prescription.toString();
    }
    
    private String getTotalFeesForAppointment(String appointmentId) {
        List<Payment> payments = Db.Payment.select(1, p -> p.getAppointmentId().equals(appointmentId));
        if (payments.isEmpty()) return "No payment record";

        // Since Payment doesn't have amount, just show payment method
        Payment payment = payments.get(0);
        return "Payment Method: " + payment.getPaymentMethod().name();
    }
    
    private String getPatientName(String customerId) {
        List<Customer> customers = Db.Customer.select(1, c -> c.getId().equals(customerId));
        if (customers.isEmpty()) return "Unknown";
        
        return customers.get(0).getUserName();
    }

    private void reload() {
        // Clear and load incomplete appointments by default
        tableModel.setRowCount(0);
        loadIncompleteAppointments();
    }

    private void loadIncompleteAppointments() {
        User user = getROOT().getCurrentUser();
        if (user == null) return;

        isViewingCompleted = false; // Set flag to false for incomplete view
        allowRowClicking = false; // Disable row clicking

        // Load appointments with status 0 (Pending) or 1 (Booked)
        List<Appointment> appts = Db.Appointment.select(-1, a -> 
            user.getId().equals(a.getCustomerId()) && 
            (a.getStatus() == Appointment.Status.Pending || a.getStatus() == Appointment.Status.Booked)
        );
        
        populateTable(appts);
        
        // Clear selection after populating
        viewAppointment.getJTable1().clearSelection();
    }

    private void loadCompletedAppointments() {
        User user = getROOT().getCurrentUser();
        if (user == null) return;

        isViewingCompleted = true; // Set flag to true for completed view
        allowRowClicking = false; // Initially disable row clicking

        // Load appointments with status 3 (Completed)
        List<Appointment> appts = Db.Appointment.select(-1, a -> 
            user.getId().equals(a.getCustomerId()) && 
            a.getStatus() == Appointment.Status.Completed
        );
        
        populateTable(appts);
        
        // Clear selection after populating
        viewAppointment.getJTable1().clearSelection();
        
        // Enable row clicking after a short delay
        javax.swing.Timer timer = new javax.swing.Timer(100, e -> {
            allowRowClicking = true;
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void loadCancelledAppointments() {
    User user = getROOT().getCurrentUser();
    if (user == null) return;

    isViewingCompleted = false; // Set flag to false for cancelled view
    allowRowClicking = false; // Disable row clicking

    // Load appointments with status -1 (Cancelled)
    List<Appointment> appts = Db.Appointment.select(-1, a -> 
        user.getId().equals(a.getCustomerId()) && 
        a.getStatus() == Appointment.Status.Cancelled
    );
    
    populateTable(appts);
    
    // Clear selection after populating
    viewAppointment.getJTable1().clearSelection();
    
    // Force table refresh
    viewAppointment.getJTable1().repaint();
    viewAppointment.getJTable1().revalidate();
    }

    private void populateTable(List<Appointment> appts) {
        isPopulatingTable = true; // Set flag before populating
        tableModel.setRowCount(0); // Clear existing rows
        
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        for (Appointment a : appts) {
            tableModel.addRow(new Object[] {
                a.getId(),
                a.getDepartmentId(),
                a.getDateTime().toLocalDate().format(df),
                a.getDateTime().toLocalTime().format(tf),
                a.getStatus().name(),
                a.getDoctorId() != null ? a.getDoctorId() : "",
                a.getStaffId() != null ? a.getStaffId() : "",
                a.getFeedback() != null ? a.getFeedback() : ""
            });
        }
        isPopulatingTable = false; // Reset flag after populating
    }
    
    public JPanel getView() { return viewAppointment;}
}
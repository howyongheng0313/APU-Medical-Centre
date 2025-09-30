package amc.controller.manager;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.db_impl.Db;
import amc.model.entity.*;
import amc.view.manager.AppointmentsPanel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class AppointmentsCtl extends AbstractSubCtl {
    
    private final AppointmentsPanel viewAppointments = new AppointmentsPanel();
    
    public AppointmentsCtl(AmcCtl ROOT) {
        super(ROOT);
        loadAllAppointments();
    }
    
    // Load all appointments
    public void loadAllAppointments() {
        try {
            List<Appointment> appointments = Db.Appointment.select(-1, appointment -> true);
            List<AppointmentDTO> appointmentDTOs = convertToAppointmentDTOs(appointments);
            
            viewAppointments.updateAppointments(appointmentDTOs);
            
        } catch (Exception e) {
            viewAppointments.updateAppointments(new ArrayList<>());
        }
    }
    
    private List<AppointmentDTO> convertToAppointmentDTOs(List<Appointment> appointments) {
        List<AppointmentDTO> dtos = new ArrayList<>();
        
        for (Appointment appointment : appointments) {
            try {
                // Get customer name
                String customerName = "Unknown Customer";
                try {
                    List<Customer> customers = Db.Customer.select(1, customer -> 
                        customer.getId().equals(appointment.getCustomerId()));
                    if (!customers.isEmpty()) {
                        customerName = customers.get(0).getUserName();
                    }
                } catch (Exception ex) {}
                
                // Get doctor name
                String doctorName = "No Doctor";
                if (appointment.getDoctorId() != null && !appointment.getDoctorId().isEmpty()) {
                    try {
                        List<Doctor> doctors = Db.Doctor.select(1, doctor -> 
                            doctor.getId().equals(appointment.getDoctorId()));
                        if (!doctors.isEmpty()) {
                            doctorName = "Dr " + doctors.get(0).getUserName();
                        }
                    } catch (Exception e) {}
                }
                
                // Get staff name
                String staffName = "No Staff";
                if (appointment.getStaffId() != null && !appointment.getStaffId().isEmpty()) {
                    try {
                        List<Staff> staffList = Db.Staff.select(1, staff -> 
                            staff.getId().equals(appointment.getStaffId()));
                        if (!staffList.isEmpty()) {
                            staffName = "Staff " + staffList.get(0).getUserName();
                        }
                    } catch (Exception e) {}
                }
                
                // Get department name
                String departmentName = "Unknown Department";
                try {
                    List<Department> departments = Db.Department.select(1, department -> 
                        department.getId().equals(appointment.getDepartmentId()));
                    if (!departments.isEmpty()) {
                        departmentName = departments.get(0).getDepartmentName();
                    }
                } catch (Exception e) {}
                
                // Create DTO
                AppointmentDTO dto = new AppointmentDTO(
                    appointment.getId(),
                    appointment.getCustomerId(),
                    customerName,
                    appointment.getDoctorId(),
                    doctorName,
                    appointment.getStaffId(),
                    staffName,
                    appointment.getDepartmentId(),
                    departmentName,
                    appointment.getDateTime(),
                    appointment.getStatus(),
                    appointment.getFeedback()
                );
                
                dtos.add(dto);
                
            } catch (Exception e) {}
        }
        
        return dtos;
    }
    
    public JPanel getView() { return viewAppointments; }
}
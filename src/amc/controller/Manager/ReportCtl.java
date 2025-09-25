/*package amc.controller;

import amc.model.entity.*;
import amc.model.db_impl.Db;
import amc.view.manager.ReportPanel;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReportCtl extends AbstractSubCtl{
    private final ReportPanel viewReport = new ReportPanel();

    public ReportCtl(AmcCtl ROOT){
        super(ROOT);
        setupReportGeneration();
    }

    // Setup report generation: listens to dashboard events and loads report data
    private void setupReportGeneration(){
        viewReport.btnGenerate.addActionListener(evt -> {
            String type = viewReport.getSelectedReportType();
            int year = viewReport.getSelectedYear();

            try {
                String card = switch(type){
                    case "Income Report" -> {
                        var rData = this.generateIncomeReport(year);
                        viewReport.incomeReportPanel1.displayReport(rData);
                        yield "incomeReport";
                    }
                    case "Patients Number Report" -> {
                        var rData = this.generatePatientNumberReport(year);
                        viewReport.patientNumberReportPanel1.displayReport(rData);
                        yield "patientNumberReport";
                    }
                    case "Doctor Performance Report" -> {
                        var rData = this.generateDoctorPerformanceReport(year);
                        viewReport.doctorPerformanceReportPanel1.displayReport(rData);
                        yield "doctorPerformanceReport";
                    }
                    default -> "";
                };
                if (card.isEmpty()) return;
                viewReport.switchReport(card);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    viewReport, "Error: " + ex.getMessage(), 
                    "Report Error:", JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    // IncomeReport Report
    public ReportsDTO.IncomeReport generateIncomeReport(int year){
        // Get all appointment for the year
        List<Appointment> appointments = Db.Appointment.select(-1 , apt ->
                apt.getDateTime().getYear() == year && apt.getStatus() == Appointment.Status.Completed
        );
        
        // Calculate monthly income
        Map<Integer, Double> monthlyIncomeMap = new HashMap<>();
        Map<Integer, Integer> monthlyCountMap = new HashMap<>();
        
        for(Appointment apt: appointments){
            int month = apt.getDateTime().getMonthValue();
            
            // Calculate income from services
            List<ApptService> services = Db.ApptService.select(-1, s ->
                    s.getAppointmentId().equals(apt.getId())
            );

            double serviceIncome = services.stream().mapToDouble(ApptService::getFee).sum();
            
            //Calculate income from medicine
            List<ApptMedicine> medicines = Db.ApptMedicine.select(-1, m ->
                    m.getAppointmentId().equals(apt.getId())
            );
            double medicineIncome = medicines.stream()
                    .mapToDouble(m -> m.getPrice() * m.getQuantity())
                    .sum();
            
            // Total income
            double totalIncome = serviceIncome + medicineIncome;
            monthlyIncomeMap.merge(month, totalIncome, Double::sum);
            monthlyCountMap.merge(month, 1, Integer::sum);
        }
        
        List<ReportsDTO.MonthlyIncome> monthlyIncomes = new ArrayList<>();
        for(int month = 1; month <= 12; month++){
            double income = monthlyIncomeMap.getOrDefault(month, 0.0);
            int count = monthlyCountMap.getOrDefault(month, 0);
            monthlyIncomes.add(new ReportsDTO.MonthlyIncome(month, income, count));
        }
        
        // Calculate payment method breakdown
        List<Payment> payments = Db.Payment.select(-1, p ->
                appointments.stream().anyMatch(apt -> apt.getId().equals(p.getAppointmentId()))
        );
        
        Map<Payment.Method, Double> paymentAmountMap = new HashMap<>();
        Map<Payment.Method, Integer> paymentCountMap = new HashMap<>();
        
        for(Payment payment: payments){
            String aptId = payment.getAppointmentId();
            Optional<Appointment> apt = appointments.stream()
                    .filter(a ->  a.getId().equals(aptId))
                    .findAny();
            
            if(apt.isPresent()){
                List<ApptService> services = Db.ApptService.select(-1, s ->
                        s.getAppointmentId().equals(aptId)
                );
                List<ApptMedicine> medicines = Db.ApptMedicine.select(-1, m ->
                        m.getAppointmentId().equals(aptId)
                );
                
                double serviceIncome = services.stream().mapToDouble(ApptService::getFee).sum();
                double medicineIncome = medicines.stream()
                        .mapToDouble(m -> m.getPrice() * m.getQuantity())
                        .sum();
                double totalIncome = serviceIncome + medicineIncome;
                
                paymentAmountMap.merge(payment.getPaymentMethod(), totalIncome, Double::sum);
                paymentCountMap.merge(payment.getPaymentMethod(), 1, Integer::sum);
            }
        }
        
        List<ReportsDTO.PaymentBreakdown> paymentBreakdown = paymentAmountMap.entrySet().stream()
                .map(entry -> new ReportsDTO.PaymentBreakdown(
                        entry.getKey(), 
                        entry.getValue(), 
                        paymentCountMap.get(entry.getKey())
                ))
                .collect(Collectors.toList());
        
        double totalIncome = monthlyIncomes.stream().mapToDouble(ReportsDTO.MonthlyIncome::getIncome).sum();
        
        return new ReportsDTO.IncomeReport(year, totalIncome, monthlyIncomes, paymentBreakdown);
    }

    // Patient Number Report
    public ReportsDTO.PatientNumberReport generatePatientNumberReport(int year){
        // Get all appointment for the year
        List<Appointment> appointments = Db.Appointment.select(-1, apt ->
                apt.getDateTime().getYear() == year
        );
        
        // Get unique patients for the year
        Set<String> uniquePatients = appointments.stream()
                .map(Appointment::getCustomerId)
                .collect(Collectors.toSet());
        
        // Calcualte monthly patients counts
        Map<Integer, Set<String>> monthlyPatientMap = new HashMap<>(); // 每月所有来过的患者
        Map<Integer, Set<String>> monthlyNewPatientMap = new HashMap<>(); // 每月的新患者
        
        // Add customerId to its relevant month (avoid repetition)
        for (Appointment apt : appointments) {
            int month = apt.getDateTime().getMonthValue();
            String customerId = apt.getCustomerId();
            
            monthlyPatientMap.computeIfAbsent(month, k -> new HashSet<>()).add(customerId);
            
            // Check if this is a new patient (first appointment in this year)
            boolean isNewPatient = appointments.stream()
                .filter(a -> a.getCustomerId().equals(customerId))
                .filter(a -> a.getDateTime().getYear() == year)
                .min(Comparator.comparing(Appointment::getDateTime))
                .map(a -> a.getDateTime().getMonthValue() == month)
                .orElse(false);
            
            if (isNewPatient) {
                monthlyNewPatientMap.computeIfAbsent(month, k -> new HashSet<>()).add(customerId);
            }
        }  
        
        // Construct monthly patients report (total patients + new patients)
        List<ReportsDTO.MonthlyPatients> monthlyPatients = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            int patientCount = monthlyPatientMap.getOrDefault(month, new HashSet<>()).size();
            int newPatients = monthlyNewPatientMap.getOrDefault(month, new HashSet<>()).size();
            monthlyPatients.add(new ReportsDTO.MonthlyPatients(month, patientCount, newPatients));
        }
        
        // Calculate department patient counts
        Map<String, Set<String>> departmentPatientMap = new HashMap<>();
        for (Appointment apt : appointments) {
            String deptId = apt.getDepartmentId();
            String customerId = apt.getCustomerId();
            departmentPatientMap.computeIfAbsent(deptId, k -> new HashSet<>()).add(customerId);
        }
        
        // Convert to ReportsDTO Object
        List<ReportsDTO.DepartmentPatients> departmentPatients = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : departmentPatientMap.entrySet()) {
            String deptId = entry.getKey();
            int patientCount = entry.getValue().size();
            
            // Get department name
            List<Department> departments = Db.Department.select(1, d -> d.getId().equals(deptId));
            String deptName = departments.isEmpty() ? "Unknown" : departments.get(0).getDepartmentName();
            
            departmentPatients.add(new ReportsDTO.DepartmentPatients(deptId, deptName, patientCount));
        }
        
        // Return Object
        return new ReportsDTO.PatientNumberReport(year, uniquePatients.size(), monthlyPatients, departmentPatients);
    }

    // Docter Performance Report
    public ReportsDTO.DocterPerformanceReport generateDoctorPerformanceReport(int year) {
        // Get all appointments for the year
        List<Appointment> appointments = Db.Appointment.select(-1, apt -> 
            apt.getDateTime().getYear() == year && apt.getDoctorId() != null
        );
        
        // Group appointments by doctor
        Map<String, List<Appointment>> doctorAppointments = appointments.stream()
            .collect(Collectors.groupingBy(Appointment::getDoctorId));
        
        List<ReportsDTO.DoctorPerformance> doctorPerformances = new ArrayList<>();
        
        for (Map.Entry<String, List<Appointment>> entry : doctorAppointments.entrySet()) {
            String doctorId = entry.getKey();
            List<Appointment> doctorAppts = entry.getValue();
            
            // Get doctor name
            List<Doctor> doctors = Db.Doctor.select(1, d -> d.getId().equals(doctorId));
            String doctorName = doctors.isEmpty() ? "Unknown" : doctors.get(0).getUserName();
            
            int totalAppointments = doctorAppts.size();
            int completedAppointments = (int) doctorAppts.stream()
                .filter(apt -> apt.getStatus() == Appointment.Status.Completed)
                .count();
            
            // Calculate average rating
            List<Comment> comments = Db.Comment.select(-1, c -> 
                doctorAppts.stream().anyMatch(apt -> apt.getId().equals(c.getAppointmentId())) &&
                c.getTargetId().equals(doctorId)
            );
            
            double averageRating = comments.isEmpty() ? 0.0 : 
                comments.stream().mapToDouble(c -> c.getRating().getLevel()).average().orElse(0.0);
            
            // Calculate total revenue
            double totalRevenue = 0.0;
            for (Appointment apt : doctorAppts) {
                List<ApptService> services = Db.ApptService.select(-1, s -> 
                    s.getAppointmentId().equals(apt.getId())
                );
                List<ApptMedicine> medicines = Db.ApptMedicine.select(-1, m -> 
                    m.getAppointmentId().equals(apt.getId())
                );
                
                double serviceRevenue = services.stream().mapToDouble(ApptService::getFee).sum();
                double medicineRevenue = medicines.stream()
                    .mapToDouble(m -> m.getPrice() * m.getQuantity())
                    .sum();
                
                totalRevenue += serviceRevenue + medicineRevenue;
            }
            
            doctorPerformances.add(new ReportsDTO.DoctorPerformance(
                doctorId, doctorName, totalAppointments, completedAppointments, 
                averageRating, totalRevenue
            ));
        }
        
        // Sort by total revenue descending
        doctorPerformances.sort((a, b) -> Double.compare(b.getTotalRevenue(), a.getTotalRevenue()));
        
        return new ReportsDTO.DocterPerformanceReport(year, doctorPerformances);      
    }

    public JPanel getView() { return viewReport; }
}*/


package amc.controller.Manager;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.entity.*;
import amc.model.db_impl.Db;
import amc.view.manager.ReportPanel;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReportCtl extends AbstractSubCtl{
    private final ReportPanel viewReport = new ReportPanel();

    public ReportCtl(AmcCtl ROOT){
        super(ROOT);
        setupReportGeneration();
    }

    // Setup report generation: listens to dashboard events and loads report data
    private void setupReportGeneration(){
        viewReport.btnGenerate.addActionListener(evt -> {
            String type = viewReport.getSelectedReportType();
            int year = viewReport.getSelectedYear();

            try {
                String card = switch(type){
                    case "Income Report" -> {
                        var rData = this.generateIncomeReport(year);
                        viewReport.incomeReportPanel1.displayReport(rData);
                        yield "incomeReport";
                    }
                    case "Patients Number Report" -> {
                        var rData = this.generatePatientNumberReport(year);
                        viewReport.patientNumberReportPanel1.displayReport(rData);
                        yield "patientNumberReport";
                    }
                    case "Doctor Performance Report" -> {
                        var rData = this.generateDoctorPerformanceReport(year);
                        viewReport.doctorPerformanceReportPanel1.displayReport(rData);
                        yield "doctorPerformanceReport";
                    }
                    default -> "";
                };
                if (card.isEmpty()) return;
                viewReport.switchReport(card);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    viewReport, "Error: " + ex.getMessage(), 
                    "Report Error:", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    // IncomeReport Report 
    public ReportsDTO.IncomeReport generateIncomeReport(int year){
        // Get all completed appointments for the year
        List<Appointment> appointments = Db.Appointment.select(-1 , apt ->
                apt.getDateTime().getYear() == year && apt.getStatus() == Appointment.Status.Completed
        );
        
        // Calculate monthly income
        Map<Integer, Double> monthlyIncomeMap = new HashMap<>();
        
        for(Appointment apt: appointments){
            int month = apt.getDateTime().getMonthValue();
            
            // Calculate total income for this appointment
            List<ApptService> services = Db.ApptService.select(-1, s ->
                    s.getAppointmentId().equals(apt.getAppointmentId())
            );
            List<ApptMedicine> medicines = Db.ApptMedicine.select(-1, m ->
                    m.getAppointmentId().equals(apt.getAppointmentId())
            );
            
            double serviceIncome = services.stream().mapToDouble(ApptService::getFee).sum();
            double medicineIncome = medicines.stream()
                    .mapToDouble(m -> m.getPrice() * m.getQuantity())
                    .sum();
            double totalIncome = serviceIncome + medicineIncome;
            
            monthlyIncomeMap.merge(month, totalIncome, Double::sum);
        }
        
        // Create monthly income list
        List<ReportsDTO.MonthlyIncome> monthlyIncomes = new ArrayList<>();
        for(int month = 1; month <= 12; month++){
            double income = monthlyIncomeMap.getOrDefault(month, 0.0);
            monthlyIncomes.add(new ReportsDTO.MonthlyIncome(month, income, 0)); // Set count to 0 since not used
        }
        
        // Calculate payment method breakdown
        List<Payment> payments = Db.Payment.select(-1, p ->
                appointments.stream().anyMatch(apt -> apt.getAppointmentId().equals(p.getAppointmentId()))
        );
        
        Map<Payment.Method, Double> paymentAmountMap = new HashMap<>();
        
        for(Payment payment: payments){
            String aptId = payment.getAppointmentId();
            Optional<Appointment> apt = appointments.stream()
                    .filter(a ->  a.getAppointmentId().equals(aptId))
                    .findAny();
            
            if(apt.isPresent()){
                List<ApptService> services = Db.ApptService.select(-1, s ->
                        s.getAppointmentId().equals(aptId)
                );
                List<ApptMedicine> medicines = Db.ApptMedicine.select(-1, m ->
                        m.getAppointmentId().equals(aptId)
                );
                
                double serviceIncome = services.stream().mapToDouble(ApptService::getFee).sum();
                double medicineIncome = medicines.stream()
                        .mapToDouble(m -> m.getPrice() * m.getQuantity())
                        .sum();
                double totalIncome = serviceIncome + medicineIncome;
                
                paymentAmountMap.merge(payment.getPaymentMethod(), totalIncome, Double::sum);
            }
        }
        
        // Create payment breakdown list (no count needed for pie chart)
        List<ReportsDTO.PaymentBreakdown> paymentBreakdown = paymentAmountMap.entrySet().stream()
                .map(entry -> new ReportsDTO.PaymentBreakdown(
                        entry.getKey(), 
                        entry.getValue(), 
                        0 // Set count to 0 since not used
                ))
                .collect(Collectors.toList());
        
        double totalIncome = monthlyIncomes.stream().mapToDouble(ReportsDTO.MonthlyIncome::getIncome).sum();
        
        return new ReportsDTO.IncomeReport(year, totalIncome, monthlyIncomes, paymentBreakdown);
    }

    // Patient Number Report 
    public ReportsDTO.PatientNumberReport generatePatientNumberReport(int year){
        // Get all appointment for the year
        List<Appointment> appointments = Db.Appointment.select(-1, apt ->
                apt.getDateTime().getYear() == year
        );
        
        // Get unique patients for the year
        Set<String> uniquePatients = appointments.stream()
                .map(Appointment::getCustomerId)
                .collect(Collectors.toSet());
        
        // Calcualte monthly patients counts
        Map<Integer, Set<String>> monthlyPatientMap = new HashMap<>(); 
        Map<Integer, Set<String>> monthlyNewPatientMap = new HashMap<>(); 
        
        // Add customerId to its relevant month (avoid repetition)
        for (Appointment apt : appointments) {
            int month = apt.getDateTime().getMonthValue();
            String customerId = apt.getCustomerId();
            
            monthlyPatientMap.computeIfAbsent(month, k -> new HashSet<>()).add(customerId);
            
            // Check if this is a new patient (first appointment in this year)
            boolean isNewPatient = appointments.stream()
                .filter(a -> a.getCustomerId().equals(customerId))
                .filter(a -> a.getDateTime().getYear() == year)
                .min(Comparator.comparing(Appointment::getDateTime))
                .map(a -> a.getDateTime().getMonthValue() == month)
                .orElse(false);
            
            if (isNewPatient) {
                monthlyNewPatientMap.computeIfAbsent(month, k -> new HashSet<>()).add(customerId);
            }
        }  
        
        // Construct monthly patients report (total patients + new patients)
        List<ReportsDTO.MonthlyPatients> monthlyPatients = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            int patientCount = monthlyPatientMap.getOrDefault(month, new HashSet<>()).size();
            int newPatients = monthlyNewPatientMap.getOrDefault(month, new HashSet<>()).size();
            monthlyPatients.add(new ReportsDTO.MonthlyPatients(month, patientCount, newPatients));
        }
        
        // Calculate department patient counts
        Map<String, Set<String>> departmentPatientMap = new HashMap<>();
        for (Appointment apt : appointments) {
            String deptId = apt.getDepartmentId();
            String customerId = apt.getCustomerId();
            departmentPatientMap.computeIfAbsent(deptId, k -> new HashSet<>()).add(customerId);
        }
        
        // Convert to ReportsDTO Object
        List<ReportsDTO.DepartmentPatients> departmentPatients = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : departmentPatientMap.entrySet()) {
            String deptId = entry.getKey();
            int patientCount = entry.getValue().size();
            
            // Get department name
            List<Department> departments = Db.Department.select(1, d -> d.getDepartmentId().equals(deptId));
            String deptName = departments.isEmpty() ? "Unknown" : departments.get(0).getDepartmentName();
            
            departmentPatients.add(new ReportsDTO.DepartmentPatients(deptId, deptName, patientCount));
        }
        
        // Return Object
        return new ReportsDTO.PatientNumberReport(year, uniquePatients.size(), monthlyPatients, departmentPatients);
    }

    // Doctor Performance Report 
    public ReportsDTO.DocterPerformanceReport generateDoctorPerformanceReport(int year) {
        // Get all appointments for the year
        List<Appointment> appointments = Db.Appointment.select(-1, apt -> 
            apt.getDateTime().getYear() == year && apt.getDoctorId() != null
        );
        
        // Group appointments by doctor
        Map<String, List<Appointment>> doctorAppointments = appointments.stream()
            .collect(Collectors.groupingBy(Appointment::getDoctorId));
        
        List<ReportsDTO.DoctorPerformance> doctorPerformances = new ArrayList<>();
        
        for (Map.Entry<String, List<Appointment>> entry : doctorAppointments.entrySet()) {
            String doctorId = entry.getKey();
            List<Appointment> doctorAppts = entry.getValue();
            
            // Get doctor name
            List<Doctor> doctors = Db.Doctor.select(1, d -> d.getUserId().equals(doctorId));
            String doctorName = doctors.isEmpty() ? "Unknown" : doctors.get(0).getUserName();
            
            // Calculate average rating only
            List<Comment> comments = Db.Comment.select(-1, c -> 
                doctorAppts.stream().anyMatch(apt -> apt.getAppointmentId().equals(c.getAppointmentId())) &&
                c.getTargetId().equals(doctorId)
            );
            
            double averageRating = comments.isEmpty() ? 0.0 : 
                comments.stream().mapToDouble(c -> c.getRating().getLevel()).average().orElse(0.0);
            
            // Only store doctor name and average rating (other fields set to 0 since not used)
            doctorPerformances.add(new ReportsDTO.DoctorPerformance(
                doctorId, doctorName, 0, 0, averageRating, 0.0
            ));
        }
        
        // Sort by average rating descending for ranking
        doctorPerformances.sort((a, b) -> Double.compare(b.getAverageRating(), a.getAverageRating()));
        
        return new ReportsDTO.DocterPerformanceReport(year, doctorPerformances);      
    }

    public JPanel getView() { return viewReport; }
}
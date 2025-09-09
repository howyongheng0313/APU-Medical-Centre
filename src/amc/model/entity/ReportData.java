package amc.model.entity;

import java.util.List;

public class ReportData {
    
    public static class IncomeReport {
        private final int year;
        private final double totalIncome;
        private final List<MonthlyIncome> monthlyIncomes;
        private final List<PaymentBreakdown> paymentBreakdown;
        
        public IncomeReport(int year, double totalIncome, List<MonthlyIncome> monthlyIncomes, List<PaymentBreakdown> paymentBreakdown ){
        this.year = year;
        this.totalIncome = totalIncome;
        this.monthlyIncomes = monthlyIncomes;
        this.paymentBreakdown = paymentBreakdown;
        }

        public int getYear() {return year;}
        public double getTotalIncome() {return totalIncome;}
        public List<MonthlyIncome> getMonthlyIncomes() {return monthlyIncomes;}
        public List<PaymentBreakdown> getPaymentBreakdown() {return paymentBreakdown;}
    }
    
    public static class MonthlyIncome {
        private final int month;
        private final double income;
        private final int appointmentCount;
        
        public MonthlyIncome(int month, double income, int appointmentCount){
            this.month = month;
            this.income = income;
            this.appointmentCount = appointmentCount;
        }

        public int getMonth() {return month;}
        public double getIncome() {return income;}
        public int getAppointmentCount() {return appointmentCount;}
    }
    
    public static class PaymentBreakdown {
        private final Payment.Method method;
        private final double amount;
        private final int count;
        
        public PaymentBreakdown(Payment.Method method, double amount, int count) {
            this.method = method;
            this.amount = amount;
            this.count = count;
        }
        
        public Payment.Method getMethod() { return method; }
        public double getAmount() { return amount; }
        public int getCount() { return count; }
    }
    
    public static class PatientNumberReport {
        private final int year;
        private final int totalPatients;
        private final List<MonthlyPatients> monthlyPatients;
        private final List<DepartmentPatients> departmentPatients;
        
        public PatientNumberReport(int year, int totalPatients, List<MonthlyPatients> monthlyPatients, List<DepartmentPatients> departmentPatients){
            this.year = year;
            this.totalPatients = totalPatients;
            this.monthlyPatients = monthlyPatients;
            this.departmentPatients = departmentPatients;  
        }
        
        public int getYear() { return year; }
        public int getTotalPatients() { return totalPatients; }
        public List<MonthlyPatients> getMonthlyPatients() { return monthlyPatients; }
        public List<DepartmentPatients> getDepartmentPatients() { return departmentPatients; }
    }
    
    public static class MonthlyPatients{
        private final int month;
        private final int patientCount;
        private final int newPatients;
        
        public MonthlyPatients(int month, int patientCount, int newPatients) {
            this.month = month;
            this.patientCount = patientCount;
            this.newPatients = newPatients;
        }
        
        public int getMonth() { return month; }
        public int getPatientCount() { return patientCount; }
        public int getNewPatients() { return newPatients; }
    }
    
    public static class DepartmentPatients {
        private final String departmentId;
        private final String departmentName;
        private final int patientCount;
        
        public DepartmentPatients(String departmentId, String departmentName, int patientCount) {
            this.departmentId = departmentId;
            this.departmentName = departmentName;
            this.patientCount = patientCount;
        }
        
        public String getDepartmentId() { return departmentId; }
        public String getDepartmentName() { return departmentName; }
        public int getPatientCount() { return patientCount; }
    }
    
    public static class DocterPerformanceReport{
        private final int year;
        private final List<DoctorPerformance> doctorPerformances;
        
        public DocterPerformanceReport(int year, List<DoctorPerformance> doctorPerformances) {
            this.year = year;
            this.doctorPerformances = doctorPerformances;
        }
        
        public int getYear() { return year; }
        public List<DoctorPerformance> getDoctorPerformances() { return doctorPerformances; }
    }
    
    public static class DoctorPerformance {
        private final String doctorId;
        private final String doctorName;
        private final int totalAppointments;
        private final int completedAppointments;
        private final double averageRating;
        private final double totalRevenue;
        
        public DoctorPerformance(
            String doctorId,
            String doctorName,
            int totalAppointments,
            int completedAppointments,
            double averageRating,
            double totalRevenue
        ) {
            this.doctorId = doctorId;
            this.doctorName = doctorName;
            this.totalAppointments = totalAppointments;
            this.completedAppointments = completedAppointments;
            this.averageRating = averageRating;
            this.totalRevenue = totalRevenue;
        }
        
        public String getDoctorId() { return doctorId; }
        public String getDoctorName() { return doctorName; }
        public int getTotalAppointments() { return totalAppointments; }
        public int getCompletedAppointments() { return completedAppointments; }
        public double getAverageRating() { return averageRating; }
        public double getTotalRevenue() { return totalRevenue; }
    }
     
}

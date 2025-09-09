package amc.controller;

import amc.view.manager.Dashboard;
import amc.view.manager.ManagerPanel;
import amc.view.share.BarButton;
import amc.view.share.BarPanel;

import java.awt.event.ActionEvent;
import javax.swing.*;

public class ManagerCtl extends UserCtl {
    private final BarButton dashBoardBarBtn    = new BarButton("DashBoard");
    private final BarButton employeesBarBtn    = new BarButton("Employees");
    private final BarButton commentsBarBtn     = new BarButton("Comments");
    private final BarButton appointmentsBarBtn = new BarButton("Appts");
    private final BarButton servicesBarBtn     = new BarButton("Services");
    private final BarButton medicinesBarBtn    = new BarButton("Medicines");
    private final ManagerPanel viewBody = new ManagerPanel();
    private final BarPanel     viewBar  = new BarPanel(
        dashBoardBarBtn,
        employeesBarBtn,
        commentsBarBtn,
        appointmentsBarBtn,
        servicesBarBtn,
        medicinesBarBtn
    );
    private final ReportCtl reportCtl = new ReportCtl(getROOT());
    
    // Manager Control Constructor
    public ManagerCtl(AmcCtl ROOT) {
        super(ROOT);
        dashBoardBarBtn.addActionListener((ActionEvent e) -> {
        });

        employeesBarBtn.addActionListener((ActionEvent e) -> {
        });

        commentsBarBtn.addActionListener((ActionEvent e) -> {
        });

        appointmentsBarBtn.addActionListener((ActionEvent e) -> {
        });

        servicesBarBtn.addActionListener((ActionEvent e) -> {
        });

        medicinesBarBtn.addActionListener((ActionEvent e) -> {
        });
        
        // Initialize Report Generation
        setupReportGeneration();
    }
    
    private void setupReportGeneration(){
        Dashboard dashboard = viewBody.getDashboard();
        dashboard.btnGenerate.addActionListener(evt -> {
            String type = dashboard.getSelectedReportType();
            int year = dashboard.getSelectedYear();

            try {
                String card = switch(type){
                    case "Income Report" -> {
                        var rData = reportCtl.generateIncomeReport(year);
                        dashboard.incomeReportPanel1.displayReport(rData);
                        yield "incomeReport";
                    }
                    case "Patients Number Report" -> {
                        var rData = reportCtl.generatePatientNumberReport(year);
                        dashboard.patientNumberReportPanel1.displayReport(rData);
                        yield "patientNumberReport";
                    }
                    case "Doctor Performance Report" -> {
                        var rData = reportCtl.generateDoctorPerformanceReport(year);
                        dashboard.doctorPerformanceReportPanel1.displayReport(rData);
                        yield "doctorPerformanceReport";
                    }
                    default -> "";
                };
                if (card.isEmpty()) return;
                dashboard.switchReport(card);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    dashboard, "Error: " + ex.getMessage(), 
                    "Report Error:", JOptionPane.ERROR_MESSAGE
                );
            }
        });
           
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarPanel getViewBar() { return viewBar; }
}

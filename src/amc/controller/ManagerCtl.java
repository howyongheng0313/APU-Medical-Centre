package amc.controller;

import amc.model.entity.CommentsDTO;
import amc.view.manager.Dashboard;
import amc.view.manager.ManagerPanel;
import amc.view.share.BarButton;
import amc.view.share.BarPanel;

import java.awt.event.ActionEvent;
import javax.swing.*;

public class ManagerCtl extends UserCtl {
    // Navigation buttons
    private final BarButton dashBoardBarBtn    = new BarButton("DashBoard");
    private final BarButton employeesBarBtn    = new BarButton("Employees");
    private final BarButton commentsBarBtn     = new BarButton("Comments");
    private final BarButton appointmentsBarBtn = new BarButton("Appts");
    private final BarButton servicesBarBtn     = new BarButton("Services");
    private final BarButton medicinesBarBtn    = new BarButton("Medicines");
    
    // Main view components
    private final ManagerPanel viewBody = new ManagerPanel();
    private final BarPanel     viewBar  = new BarPanel(
        dashBoardBarBtn,
        employeesBarBtn,
        commentsBarBtn,
        appointmentsBarBtn,
        servicesBarBtn,
        medicinesBarBtn
    );
    
    // Sub-controllers
    private final ReportCtl  reportCtl  = new ReportCtl(getROOT());
    private final CommentCtl commentCtl = new CommentCtl(getROOT());
    
    // Constructor
    public ManagerCtl(AmcCtl ROOT) {
        super(ROOT);
        dashBoardBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.showCard("dashboard");
        });

        employeesBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.showCard("employee");
        });

        commentsBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.showCard("comment");
            loadCommentSummary();
        });

        appointmentsBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.showCard("appointment");
        });

        servicesBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.showCard("service");
        });

        medicinesBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.showCard("medicine");
        });
        
        // Initialize features
        setupReportGeneration();
        setupCommentFeature();
    }
    
    // Setup report generation: listens to dashboard events and loads report data
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

    // Setup comment feature: Handles switching between summary and details view
    private void setupCommentFeature() {
        // From summary → show details
        viewBody.getCommentsPanel().addPropertyChangeListener("showDetails", evt -> {
            String id = viewBody.getCommentsPanel().getSelectedRecipientId();
            CommentsDTO.RecipientType type = viewBody.getCommentsPanel().getSelectedRecipientType();
            if (id != null && !id.isEmpty()) loadCommentDetails(id, type);
        });

        // From details → return to summary
        viewBody.getCommentsPanel().addPropertyChangeListener("returnToSummary", evt -> {
            loadCommentSummary();
        });
    }

    // Load and display comment summaries
    private void loadCommentSummary() {
        try {
            var summaries = commentCtl.getCommentSummarys();
            viewBody.getCommentsPanel().showCommentSummary(summaries);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                viewBody, "Error loading comment summary: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Load and display detailed comments for a specific recipient.
    private void loadCommentDetails(String recipientId, CommentsDTO.RecipientType recipientType) {
        try {
            var details = commentCtl.getCommentDetails(recipientId, recipientType);
            String name = details.isEmpty() ? "Unknown" : details.get(0).recipientName;
            viewBody.getCommentsPanel().showCommentDetails(name, details);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                viewBody, "Error loading comment details: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarPanel getViewBar() { return viewBar; }
}
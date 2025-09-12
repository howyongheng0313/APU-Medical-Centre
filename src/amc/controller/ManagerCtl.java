package amc.controller;

import amc.view.share.BarButton;
import amc.view.share.BarPanel;
import amc.view.share.UserPanel;

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
    private final UserPanel viewBody = new UserPanel();
    private final BarPanel  viewBar  = new BarPanel(
        dashBoardBarBtn,
        employeesBarBtn,
        commentsBarBtn,
        appointmentsBarBtn,
        servicesBarBtn,
        medicinesBarBtn
    );
    
    // Constructor
    public ManagerCtl(AmcCtl ROOT) {
        super(ROOT);
        dashBoardBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.switchPage("Report");
        });

        employeesBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.switchPage("employee");
        });

        commentsBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.switchPage("CommentLs");
        });

        appointmentsBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.switchPage("appointment");
        });

        servicesBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.switchPage("service");
        });

        medicinesBarBtn.addActionListener((ActionEvent e) -> {
            viewBody.switchPage("medicine");
        });
        
        // Initialize features
        ReportCtl    reportCtl    = new ReportCtl(getROOT());
        CommentLsCtl commentLsCtl = new CommentLsCtl(getROOT());
        viewBody.add(reportCtl.getView()   , "Report");
        viewBody.add(commentLsCtl.getView(), "CommentLs");
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarPanel getViewBar() { return viewBar; }
}
package amc.controller;

import amc.view.share.BarButton;
import amc.view.share.BarComp;
import amc.view.share.UserPanel;
import javax.swing.JPanel;

public class ManagerCtl extends UserCtl {
    private final BarButton dashBoardBarBtn    = new BarButton("DashBoard", "Report");
    private final BarButton employeesBarBtn    = new BarButton("Employees", "Employees");
    private final BarButton commentsBarBtn     = new BarButton("Comments" , "Comments");
    private final BarButton appointmentsBarBtn = new BarButton("Appts    ", "Appointments");
    private final BarButton servicesBarBtn     = new BarButton("Services" , "Services");
    private final BarButton medicinesBarBtn    = new BarButton("Medicines", "Medicines");
    private final UserPanel viewBody = new UserPanel();
    private final BarComp   viewBar  = new BarComp(viewBody,
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

        // Initialize features
        ReportCtl   reportCtl    = new ReportCtl(getROOT());
        CommentsCtl commentLsCtl = new CommentsCtl(getROOT());
        viewBody.add(reportCtl.getView()   , "Report");
        viewBody.add(commentLsCtl.getView(), "Comments");
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

package amc.controller;

import amc.view.manager.ManagerPanel;
import amc.view.share.BarButton;
import amc.view.share.BarComp;
import javax.swing.JPanel;

public class ManagerCtl extends UserCtl {
    private final BarButton dashBoardBarBtn    = new BarButton("DashBoard", "DashBoard");
    private final BarButton employeesBarBtn    = new BarButton("Employees", "Employees");
    private final BarButton commentsBarBtn     = new BarButton("Comments" , "Comments");
    private final BarButton appointmentsBarBtn = new BarButton("Appts    ", "Appointments");
    private final BarButton servicesBarBtn     = new BarButton("Services" , "Services");
    private final BarButton medicinesBarBtn    = new BarButton("Medicines", "Medicines");
    private final ManagerPanel viewBody = new ManagerPanel();
    private final BarComp      viewBar  = new BarComp(viewBody,
        dashBoardBarBtn,
        employeesBarBtn,
        commentsBarBtn,
        appointmentsBarBtn,
        servicesBarBtn,
        medicinesBarBtn
    );

    public ManagerCtl(AmcCtl ROOT) {
        super(ROOT);
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

package amc.controller;

import amc.view.manager.ManagerPanel;
import amc.view.share.BarButton;
import amc.view.share.BarPanel;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

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
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarPanel getViewBar() { return viewBar; }
}

package amc.controller;

import amc.controller.manager.ServicesCtl;
import amc.controller.manager.ReportCtl;
import amc.controller.manager.MedicinesCtl;
import amc.controller.manager.EmployeesCtl;
import amc.controller.manager.CommentsCtl;
import amc.controller.manager.AppointmentsCtl;
import amc.view.share.BarButton;
import amc.view.share.BarComp;
import amc.view.share.UserPanel;
import javax.swing.JPanel;

public class ManagerCtl extends UserCtl {
    private final BarButton dashBoardBarBtn    = new BarButton("DashBoard", "Report");
    private final BarButton employeesBarBtn    = new BarButton("Employees", "Employees");
    private final BarButton commentsBarBtn     = new BarButton("Comments" , "Comments");
    private final BarButton appointmentsBarBtn = new BarButton("Appts"    , "Appointments");
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
        ReportCtl       reportCtl       = new ReportCtl(getROOT());
        EmployeesCtl    employeesCtl    = new EmployeesCtl(getROOT());
        CommentsCtl     commentsCtl     = new CommentsCtl(getROOT());
        AppointmentsCtl appointmentsCtl = new AppointmentsCtl(getROOT());
        ServicesCtl     servicesCtl     = new ServicesCtl(getROOT());
        MedicinesCtl    medicinesCtl    = new MedicinesCtl(getROOT());
        viewBody.add(reportCtl.getView()      , "Report");
        viewBody.add(employeesCtl.getView()   , "Employees");
        viewBody.add(commentsCtl.getView()    , "Comments");
        viewBody.add(appointmentsCtl.getView(), "Appointments");
        viewBody.add(servicesCtl.getView()    , "Services");
        viewBody.add(medicinesCtl.getView()   , "Medicines");
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

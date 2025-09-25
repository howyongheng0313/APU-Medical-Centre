package amc.controller;

import amc.controller.Manager.ServiceCtl;
import amc.controller.Manager.ReportCtl;
import amc.controller.Manager.MedicineCtl;
import amc.controller.Manager.EmployeeCtl;
import amc.controller.Manager.CommentsCtl;
import amc.controller.Manager.AppointmentCtl;
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
        ReportCtl      reportCtl      = new ReportCtl(getROOT());
        CommentsCtl    commentLsCtl   = new CommentsCtl(getROOT());
        ServiceCtl     serviceCtl     = new ServiceCtl(getROOT());
        EmployeeCtl    employeeCtl    = new EmployeeCtl(getROOT());
        MedicineCtl    medicineCtl    = new MedicineCtl(getROOT());
        AppointmentCtl appointmentCtl = new AppointmentCtl(getROOT());
        viewBody.add(reportCtl.getView()     , "Report");
        viewBody.add(commentLsCtl.getView()  , "Comments");
        viewBody.add(serviceCtl.getView()    , "Services");
        viewBody.add(employeeCtl.getView()   , "Employees");
        viewBody.add(medicineCtl.getView()   , "Medicines");
        viewBody.add(appointmentCtl.getView(), "Appointments");
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarComp getViewBar() { return viewBar; }
}

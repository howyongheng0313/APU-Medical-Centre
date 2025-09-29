package amc.controller.staff;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.controller.share.AppointmentCtl;
import amc.controller.share.JumpTree;
import amc.model.db_impl.Db;
import amc.model.entity.Appointment;
import amc.model.entity.Department;
import amc.view.staff.PayingsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;

public class PayingsCtl extends AbstractSubCtl {
    private final PayingsPanel viewPayings = new PayingsPanel();

    public PayingsCtl(AmcCtl ROOT) {
        super(ROOT);
        refreshPayAppt();
        initComboDept();
        viewPayings.btnSearch.addActionListener((ActionEvent evt) -> {
            refreshPayAppt();
        });
        Db.Appointment.addTblListener(() -> refreshPayAppt());
        Db.Department.addTblListener(() -> initComboDept());
        viewPayings.tblPayAppt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                openAppointment(evt);
            }
        });
    }

    private void refreshPayAppt() {
        String sApptId  = viewPayings.getSearchApptId();
        String sCusInfo = viewPayings.getSearchCusInfo();
        String sDeptId  = viewPayings.getSearchDepartment();
        List<Appointment> apptLs = Db.Appointment.select(-1, appt -> {
            return (sApptId.isEmpty() || appt.getId().equals(sApptId)) &&
                (sDeptId.isEmpty() || appt.getDepartmentId().equals(sDeptId)) &&
                (sCusInfo.isEmpty() ||
                    appt.getCustomerId().equals(sCusInfo) ||
                    appt.getCustomer().getUserName().matches(sCusInfo) ||
                    appt.getCustomer().getEmail().matches(sCusInfo)
                );
        });
        viewPayings.renderPayAppt(apptLs);
    }

    private void initComboDept() {
        List<Department> deptLs = Db.Department.select(1, dept -> true);
        viewPayings.renderComboDept(deptLs);
    }

    private void openAppointment(MouseEvent evt) {
        if (evt.getClickCount() != 2) return;
        int selectedIndex = viewPayings.tblPayAppt.getSelectedRow();
        String selectedID = (String) viewPayings.tblPayAppt.getValueAt(selectedIndex, 0);
        Appointment selectedAppt = Db.Appointment.getById(selectedID);
        AppointmentCtl apptCtl = new AppointmentCtl(getROOT(), selectedAppt, JumpTree.StfPaying);
        apptCtl.startView();
    }

    public JPanel getView() { return viewPayings; }
}

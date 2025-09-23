package amc.controller;

import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.Appointment;
import amc.model.entity.Customer;
import amc.model.entity.Doctor;
import amc.model.entity.Staff;
import amc.view.share.AppointmentPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppointmentCtl extends AbstractSubCtl {
    private AppointmentPanel viewAppt = new AppointmentPanel();
    private Appointment appt;
    private ApptNode node;

    public AppointmentCtl(AmcCtl ROOT, Appointment appt, ApptNode node) {
        super(ROOT);
        this.appt = appt;
        this.node = node;
        showInit();
        if (node.isShowConsult) consultProc();
        if (node.isShowResult)  resultProc();
        if (node.isShowPaying)  payingProc();
        
    }

    private void showInit() {
        viewAppt.showInit(node);
        viewAppt.renderDetail(appt);

        String cusName = null,
               stfName = null,
               docName = null;
        if (node.isShowCustomer) {
            Customer cus = Db.Customer.select(1,
                DbMan.checkUserID(appt.getCustomerId())
            ).get(0);
            cusName = cus.getUserName();
            if (node.cusProfileNode != null) viewAppt.lblCustomerName.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    new ProfileCtl(getROOT(), cus, node.cusProfileNode).startView();
                }
            });
        }

        if (node.isShowStaff && appt.getStaffId() != null) {
            Staff stf = Db.Staff.select(1,
                DbMan.checkUserID(appt.getStaffId())
            ).get(0);
            stfName = stf.getUserName();
            if (node.stfProfileNode != null) viewAppt.lblStaffName.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    new ProfileCtl(getROOT(), stf, node.stfProfileNode).startView();
                }
            });
        }

        if (node.isShowDoctor && appt.getDoctorId() != null) {
            Doctor doc = Db.Doctor.select(1,
                DbMan.checkUserID(appt.getDoctorId())
            ).get(0);
            docName = doc.getUserId();
            if (node.docProfileNode != null) viewAppt.lblDoctorName.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    new ProfileCtl(getROOT(), doc, node.docProfileNode).startView();
                }
            });
        }

        viewAppt.renderUserName(cusName, stfName, docName);
    }

    private void consultProc() {
        
    }

    private void resultProc() {
    }

    private void payingProc() {
    }

    public void startView() {
        getROOT().pushPage(viewAppt);
    }
}

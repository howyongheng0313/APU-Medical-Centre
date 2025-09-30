package amc.controller.share;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.*;
import amc.view.share.AppointmentPanel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AppointmentCtl extends AbstractSubCtl {
    private final AppointmentPanel viewAppt = new AppointmentPanel();
    private final Appointment appt;
    private final ApptNode node;
    private double totalFee;

    public AppointmentCtl(AmcCtl ROOT, Appointment appt, ApptNode node) {
        super(ROOT);
        this.appt = appt;
        this.node = node;
        writeNodeRecord();
        showInit();
        if (node.isShowConsult) consultProc();
        if (node.isShowResult)  resultProc();
        if (node.isShowPaying)  payingProc();
        viewAppt.picUndo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                getROOT().popPage(viewAppt);
            }
        });
    }

    private void writeNodeRecord() {
        if (node.cusProfileNode != null) node.cusProfileNode.recordApptId(appt.getId());
        if (node.stfProfileNode != null) node.stfProfileNode.recordApptId(appt.getId());
        if (node.docProfileNode != null) node.docProfileNode.recordApptId(appt.getId());
    }

    private void showInit() {
        viewAppt.showInit(node);
        viewAppt.renderDetail(appt);

        String cusName = null,
               stfName = null,
               docName = null;
        if (node.isShowCustomer) {
            Customer cus = Db.Customer.getById(appt.getCustomerId());
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
                DbMan.checkById(appt.getStaffId())
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
                DbMan.checkById(appt.getDoctorId())
            ).get(0);
            docName = doc.getId();
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
        viewAppt.renderConsultPage(
            Db.Service.select(-1, model->true),
            Db.Medicine.select(-1, model->true)
        );
        viewAppt.btnEndCon.addActionListener((ActionEvent evt) -> endConsult());
    }

    private void endConsult() {
        AppointmentPanel.EndConsultContext ctx = viewAppt.getEndConsultContext();
        List<ApptService>  selectedService  = new ArrayList<> ();
        List<ApptMedicine> selectedMedicine = new ArrayList<> ();
        for (Vector svcRow : ctx.serviceChooseList()) {
            if (!(Boolean) svcRow.get(1)) continue;
            Service service = (Service) svcRow.getLast();
            selectedService.add(new ApptService(
                appt.getId(),
                service.getId(),
                service.getFee()
            ));
        }
        for (Vector mdcRow: ctx.medicineChooseList()) {
            if (0 < (Integer) mdcRow.get(1)) continue;
            Medicine medicine = (Medicine) mdcRow.getLast();
            selectedMedicine.add(new ApptMedicine(
                appt.getId(),
                medicine.getId(),
                medicine.getPrice(),
                (Integer) mdcRow.get(1)
            ));
        }

        Db.ApptService.insert(selectedService);
        Db.ApptMedicine.insert(selectedMedicine);
        appt.setFeedback(ctx.feedback());
        appt.setStatus(Appointment.Status.EndCons);
        Db.Appointment.update(1, DbMan.checkById(appt.getId()), model->appt);

        getROOT().popPage(viewAppt);
    }

    private void resultProc() {
        double[] total = new double[] {0};
        List<ApptService> serviceLs = Db.ApptService.select(-1, model -> {
            if (!model.getAppointmentId().equals(appt.getId())) return false;
            total[0] += model.getFee();
            return true;
        });
        List<ApptMedicine> medicineLs = Db.ApptMedicine.select(-1, model -> {
            if (!model.getAppointmentId().equals(appt.getId())) return false;
            total[0] += model.getTotalPrice();
            return true;
        });
        totalFee = total[0];
        viewAppt.renderResultPage(serviceLs, medicineLs, totalFee, appt.getFeedback());
    }

    private void payingProc() {
        viewAppt.btnPayCash.addActionListener((ActionEvent evt) -> paying(Payment.Method.Cash));
        viewAppt.btnPayCard.addActionListener((ActionEvent evt) -> paying(Payment.Method.CreditDebit));
        viewAppt.btnPayEwallet.addActionListener((ActionEvent evt) -> paying(Payment.Method.EWallet));
    }

    private void paying(Payment.Method method) {
        Db.Payment.insert(List.of(new Payment(Db.Payment.newId(), appt.getId(), method)));
        appt.setStatus(Appointment.Status.Completed);
        Db.Appointment.update(1, DbMan.checkById(appt.getId()), model -> appt);

        getROOT().popPage(viewAppt);
    }

    public void startView() {
        getROOT().pushPage(viewAppt);
    }
}

package amc.controller.staff;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.Appointment;
import amc.model.entity.Customer;
import amc.model.entity.Department;
import amc.model.entity.Doctor;
import amc.model.entity.Staff;
import amc.view.staff.ApptPendingComp;
import amc.view.staff.BookingsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class BookingsCtl extends AbstractSubCtl {
    private final BookingsPanel viewBookings = new BookingsPanel();
    private Appointment newBookAppt = null;

    public BookingsCtl(AmcCtl ROOT) {
        super(ROOT);
        refreshBookLs();
        Db.Appointment.addTblListener(() -> {
            refreshBookLs();
        });
        newBookProc();
    }

    private void refreshBookLs() {
        List<Appointment> apptLs = Db.Appointment.select(-1,
            DbMan.checkByStatus(Appointment.Status.Pending)
        );

        List<ApptPendingComp> bookingLs = new ArrayList<>();
        for (Appointment appt: apptLs) {
            ApptPendingComp bookAppt = new ApptPendingComp(appt);
            
            List<Doctor> docLs = findAvailableDoctor(appt.getDepartmentId(), appt.getDateTime());
            bookAppt.initComboDoc(docLs);

            bookAppt.btnConfirm.addActionListener((ActionEvent evt) -> {
                if (bookAppt.getSelectedDoc() == null || !(getROOT().getCurrentUser() instanceof  Staff)) return;
                Db.Appointment.update(1, DbMan.checkById(bookAppt.getAppointment().getId()), model -> {
                    model.setDoctorId(bookAppt.getSelectedDoc().getId());
                    model.setStaffId(getROOT().getCurrentUser().getId());
                    model.setStatus(Appointment.Status.Booked);
                    return model;
                });
            });

            bookAppt.btnReject.addActionListener((ActionEvent evt) -> {
                Db.Appointment.update(1, DbMan.checkById(bookAppt.getAppointment().getId()), model -> {
                    model.setStatus(Appointment.Status.Cancelled);
                    return model;
                });
            });
            bookingLs.add(bookAppt);
        }
        viewBookings.renderBookingList(bookingLs);
    }

    private List<Doctor> findAvailableDoctor(String deptId, LocalDateTime time) {
        List<String> busyDocLs = new ArrayList<>();
        DbMan.Query<Appointment> busyDocEach = (appt) -> {
            if (appt.getStatus().equals(Appointment.Status.Booked) &&
                appt.getDepartmentId().equals(deptId) && (
                appt.getDateTime().compareTo(time.plusMinutes(30))  >= 0 ||
                appt.getDateTime().compareTo(time.minusMinutes(30)) <= 0
            )) busyDocLs.add(appt.getDoctorId());
            return false;
        };
        Db.Appointment.select(-1, busyDocEach);
        return Db.Doctor.select(-1, doc -> {
            return (
                doc.getDepartmentId().equals(deptId) &&
                !busyDocLs.contains(doc.getId())
            );
        });
    }

    private void newBookProc() {
        viewBookings.btnNewBook.addActionListener((ActionEvent evt) -> {
            newBookAppt = new Appointment(Db.Appointment.newId(), null, null, null, Appointment.Status.Booked);
            newBookAppt.setStaffId(getROOT().getCurrentUser().getId());
            viewBookings.openNewBookDialog();
            refreshNewBookCusLs();
            refreshNewComboDept();
        });
        viewBookings.btnNewSearch.addActionListener((ActionEvent evt) -> refreshNewBookCusLs());
        viewBookings.tblNewCus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) { selectNewBookCusID(evt); }
        });
        viewBookings.btnNewNext.addActionListener((ActionEvent evt) -> selectNewDeptTime());
        viewBookings.btnNewBack.addActionListener((ActionEvent evt) -> viewBookings.switchNewStep("PickCustomer"));
        viewBookings.newBookComp.btnConfirm.addActionListener((ActionEvent evt) -> {
            Doctor doc = viewBookings.newBookComp.getSelectedDoc();
            if (doc == null || !(getROOT().getCurrentUser() instanceof Staff)) return;
            newBookAppt.setDoctorId(doc.getId());
            Db.Appointment.insert(List.of(newBookAppt));
            viewBookings.closeNewBookDialog();
        });
        viewBookings.newBookComp.btnReject.addActionListener((ActionEvent evt) -> {
            viewBookings.switchNewStep("PickDeptTime");
        });
    }

    private void refreshNewBookCusLs() {
        String search = viewBookings.getNewSearchCus().toLowerCase();
        List<Customer> cusLs = Db.Customer.select(-1, DbMan.searchCusInfo(search));
        viewBookings.renderNewCusLs(cusLs);
    }

    private void refreshNewComboDept() {
        List<Department> deptLs = Db.Department.select(-1, dept -> true);
        viewBookings.renderNewComboDept(deptLs);
    }

    private void selectNewBookCusID(MouseEvent evt) {
        if (evt.getClickCount() != 2) return;
        newBookAppt.setCustomerId(viewBookings.getNewSelectedCusId());
        viewBookings.switchNewStep("PickDeptTime");
    }

    private void selectNewDeptTime() {
        Department dept = viewBookings.getNewSelectedDept();
        if (dept == null) return;
        newBookAppt.setDepartmentId(dept.getId());
        newBookAppt.setDateTime(viewBookings.getNewSelectedTime());

        viewBookings.newBookComp.setAppointment(newBookAppt);
        viewBookings.newBookComp.initComboDoc(
            findAvailableDoctor(newBookAppt.getDepartmentId(), newBookAppt.getDateTime())
        );
        viewBookings.switchNewStep("PickDoctor");
    }

    public JPanel getView() { return viewBookings; }
}

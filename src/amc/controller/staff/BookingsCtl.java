package amc.controller.staff;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.Appointment;
import amc.model.entity.Doctor;
import amc.model.entity.Staff;
import amc.view.staff.ApptPendingComp;
import amc.view.staff.BookingsPanel;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class BookingsCtl extends AbstractSubCtl {
    private final BookingsPanel viewBookings = new BookingsPanel();

    public BookingsCtl(AmcCtl ROOT) {
        super(ROOT);
        refreshBookLs();
        Db.Appointment.addTblListener(() -> {
            refreshBookLs();
        });
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

    public JPanel getView() { return viewBookings; }
}

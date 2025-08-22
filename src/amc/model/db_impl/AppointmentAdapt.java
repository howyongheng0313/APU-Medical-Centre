package amc.model.db_impl;

import amc.model.DataUtil;
import amc.model.DbAdapter;
import amc.model.entity.Appointment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class AppointmentAdapt extends DbAdapter<Appointment> {
    protected AppointmentAdapt() {}

    @Override
    public Appointment getMod(List<String> row) {
        Appointment model = new Appointment(
            row.get(0),
            row.get(1),
            row.get(4),
            LocalDateTime.of(
                DataUtil.str2date(row.get(5)),
                DataUtil.str2time(row.get(6))
            ),
            Appointment.Status.valueOf(row.get(7))
        );
        if (row.get(2).isEmpty()) model.setDoctorId(row.get(2));
        if (row.get(3).isEmpty()) model.setStaffId(row.get(3));
        if (row.get(8).isEmpty()) model.setFeedback(row.get(8));
        return model;
    }

    @Override
    public List<String> getRow(Appointment model) {
        List<String> row = List.of(
            model.getAppointmentId(),
            model.getCustomerId(),
            Objects.toString(model.getDoctorId(), ""),
            Objects.toString(model.getStaffId(), ""),
            model.getDepartmentId(),
            DataUtil.date2str(model.getDateTime().toLocalDate()),
            DataUtil.time2str(model.getDateTime().toLocalTime()),
            model.getStatus().name(),
            Objects.toString(model.getFeedback(), "")
        );
        return row;
    }
}

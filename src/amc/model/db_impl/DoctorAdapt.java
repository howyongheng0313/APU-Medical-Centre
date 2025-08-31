package amc.model.db_impl;

import amc.model.DataUtil;
import amc.model.DbAdapter;
import amc.model.entity.Doctor;
import amc.model.entity.User;

import java.util.List;

public class DoctorAdapt extends DbAdapter<Doctor> {
    protected DoctorAdapt() {}

    @Override
    public Doctor getMod(List<String> row) {
        Doctor model = new Doctor(
            row.get(0),
            row.get(1),
            DataUtil.str2date(row.get(2)),
            User.Gender.valueOf(row.get(3)),
            row.get(4),
            row.get(5)
        );
        return model;
    }

    @Override
    public List<String> getRow(Doctor model) {
        List<String> row = List.of(
            model.getUserId(),
            model.getUserName(),
            DataUtil.date2str(model.getDateOfBirth()),
            model.getGender().name(),
            DataUtil.formatEmail(model.getEmail()),
            model.getContact()
        );
        return row;
    }
}

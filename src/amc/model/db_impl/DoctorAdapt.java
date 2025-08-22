package amc.model.db_impl;

import amc.model.DbAdapter;
import amc.model.entity.Doctor;
import java.util.List;

public class DoctorAdapt extends DbAdapter<Doctor> {
    protected DoctorAdapt() {}

    @Override
    public Doctor getMod(List<String> row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getRow(Doctor model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

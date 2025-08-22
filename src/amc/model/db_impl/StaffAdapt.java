package amc.model.db_impl;

import amc.model.DbAdapter;
import amc.model.entity.Staff;
import java.util.List;

public class StaffAdapt extends DbAdapter<Staff> {
    protected StaffAdapt() {}

    @Override
    public Staff getMod(List<String> row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getRow(Staff model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

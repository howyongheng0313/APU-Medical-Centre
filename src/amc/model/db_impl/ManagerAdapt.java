package amc.model.db_impl;

import amc.model.DbAdapter;
import amc.model.entity.Manager;
import java.util.List;

public class ManagerAdapt extends DbAdapter<Manager> {
    protected ManagerAdapt() {}
    @Override
    public Manager getMod(List<String> row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getRow(Manager model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

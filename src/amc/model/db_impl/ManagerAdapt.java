package amc.model.db_impl;

import amc.model.DataUtil;
import amc.model.DbAdapter;
import amc.model.entity.Manager;
import amc.model.entity.User;

import java.util.List;

public class ManagerAdapt extends DbAdapter<Manager> {
    protected ManagerAdapt() {}

    @Override
    public Manager getMod(List<String> row) {
        Manager model = new Manager(
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
    public List<String> getRow(Manager model) {
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

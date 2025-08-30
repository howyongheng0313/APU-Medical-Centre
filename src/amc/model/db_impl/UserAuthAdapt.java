package amc.model.db_impl;

import amc.model.DataUtil;
import amc.model.DbAdapter;
import amc.model.entity.Password;
import amc.model.entity.Role;
import amc.model.entity.UserAuth;
import java.util.List;

public class UserAuthAdapt extends DbAdapter<UserAuth> {
    protected UserAuthAdapt() {}

    @Override
    public UserAuth getMod(List<String> row) {
        UserAuth model = new UserAuth(
            row.get(0),
            Role.valueOf(row.get(1)),
            new Password(row.get(2), row.get(3))
        );
        return model;
    }

    @Override
    public List<String> getRow(UserAuth model) {
        List<String> row = List.of(
            DataUtil.formatEmail(model.getEmail()),
            model.getRole().name(),
            model.getPassword().getHash64(),
            model.getPassword().getSalt64()
        );
        return row;
    }
}

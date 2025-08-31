package amc.model.db_impl;

import amc.model.DataUtil;
import amc.model.DbAdapter;
import amc.model.entity.Service;
import java.util.List;

public class ServiceAdapt extends DbAdapter<Service> {
    protected ServiceAdapt() {}

    @Override
    public Service getMod(List<String> row) {
        Service model = new Service(
            row.get(0),
            row.get(1),
            DataUtil.str2amount(row.get(2)),
            row.get(3)
        );
        return model;
    }

    @Override
    public List<String> getRow(Service model) {
        List<String> row = List.of(
            model.getServiceId(),
            model.getServiceName(),
            DataUtil.amount2str(model.getFee()),
            model.getDepartmentId()
        );
        return row;
    }
}

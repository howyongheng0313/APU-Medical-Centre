package amc.model.db_impl;

import amc.model.DataUtil;
import amc.model.DbAdapter;
import amc.model.entity.ApptService;
import java.util.List;

public class ApptServiceAdapt extends DbAdapter<ApptService> {
    protected ApptServiceAdapt() {}

    @Override
    public ApptService getMod(List<String> row) {
        ApptService model = new ApptService(
            row.get(0),
            row.get(1),
            DataUtil.str2amount(row.get(2))
        );
        return model;
    }

    @Override
    public List<String> getRow(ApptService model) {
        List<String> row = List.of(
            model.getAppointmentId(),
            model.getServiceId(),
            DataUtil.amount2str(model.getFee())
        );
        return row;
    }
}

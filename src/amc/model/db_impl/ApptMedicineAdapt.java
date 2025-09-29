package amc.model.db_impl;

import amc.model.DataUtil;
import amc.model.DbAdapter;
import amc.model.entity.ApptMedicine;
import java.util.List;

public class ApptMedicineAdapt extends DbAdapter<ApptMedicine> {
    protected ApptMedicineAdapt() {}

    @Override
    public ApptMedicine getMod(List<String> row) {
        ApptMedicine model = new ApptMedicine(
            row.get(0),
            row.get(1),
            DataUtil.str2amount(row.get(2)),
            Integer.parseInt(row.get(3))
        );
        return model;
    }

    @Override
    public List<String> getRow(ApptMedicine model) {
        List<String> row = List.of(
            model.getAppointmentId(),
            model.getMedicineId(),
            DataUtil.amount2str(model.getPrice()),
            String.valueOf(model.getQuantity())
        );
        return row;
    }
}

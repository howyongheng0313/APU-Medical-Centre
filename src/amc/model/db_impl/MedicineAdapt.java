package amc.model.db_impl;

import amc.model.DataUtil;
import amc.model.DbAdapter;
import amc.model.entity.Medicine;
import java.util.List;

public class MedicineAdapt extends DbAdapter<Medicine> {
    protected MedicineAdapt() {}

    @Override
    public Medicine getMod(List<String> row) {
        Medicine model = new Medicine(
            row.get(0),
            row.get(1),
            DataUtil.str2amount(row.get(2))
        );
        return model;
    }

    @Override
    public List<String> getRow(Medicine model) {
        List<String> row = List.of(
            model.getMedicineId(),
            model.getMedicineName(),
            DataUtil.amount2str(model.getPrice())
        );
        return row;
    }
}

package amc.model.db_impl;

import amc.model.DbAdapter;
import amc.model.entity.Department;
import java.util.List;

public class DepartmentAdapt extends DbAdapter<Department> {
    protected DepartmentAdapt() {}

    @Override
    public Department getMod(List<String> row) {
        Department model = new Department(
            row.get(0),
            row.get(1)
        );
        return model;
    }

    @Override
    public List<String> getRow(Department model) {
        List<String> row = List.of(
            model.getDepartmentId(),
            model.getDepartmentName()
        );
        return row;
    }
}

package amc.model.db_impl;

import amc.model.DbAdapter;
import java.util.List;

public class NoneAdapt extends DbAdapter<List<String>> {
    @Override
    public List<String> getMod(List<String> row) {
        return row;
    }

    @Override
    public List<String> getRow(List<String> model) {
        return model;
    }
}

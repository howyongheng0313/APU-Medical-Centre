package amc.model.db_impl;

import amc.model.DbAdapter;
import amc.model.entity.Customer;
import java.util.List;

public class CustomerAdapt extends DbAdapter<Customer> {
    protected CustomerAdapt() {}

    @Override
    public Customer getMod(List<String> row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getRow(Customer model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

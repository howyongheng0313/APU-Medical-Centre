package amc.model;

import java.util.List;

public abstract class DbAdapter<T> {
    protected DbAdapter() {}
    public abstract T getMod(List<String> row);
    public abstract List<String> getRow(T model);
}

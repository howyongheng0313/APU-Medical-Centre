package amc.model;

import amc.model.entity.*;

public final class DbMan {
    @FunctionalInterface
    public static interface Query<T> {
        boolean apply(T model);
    }
    
    @FunctionalInterface
    public static interface Alter<T> {
        T apply(T model);
    }

    private DbMan() {}

    public static Query<UserAuth> checkUserRole(String email) {
        return (model) -> {
            return model.getEmail().equals(DataUtil.formatEmail(email));
        };
    }

    public static <T extends User> Query<T> checkUser(String email) {
        return (model) -> {
            return model.getEmail().equals(DataUtil.formatEmail(email));
        };
    }
}

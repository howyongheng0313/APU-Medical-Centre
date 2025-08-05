package amc.controller;

import amc.model.MyModel;

public class DbMan {
    @FunctionalInterface
    public static interface Query<T> {
        boolean apply(T model);
    }
    @FunctionalInterface
    public static interface Alter<T> {
        T apply(T model);
    }

    public static Query<MyModel> checkId(int id) {
        return model -> model.getUuid() == id;
    }

    public static Alter<MyModel> birthPlus() {
        return model -> {
            model.setBirth(model.getBirth().plusDays(1));
            return model;
        };
    }
}

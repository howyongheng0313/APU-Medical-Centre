package amc.controller;

import amc.model.MyModel;

public class ROps {
    @FunctionalInterface
    public static interface filter<T> {
        boolean apply(T model);
    }
    @FunctionalInterface
    public static interface changer<T> {
        T apply(T model);
    }

    public static filter<MyModel> checkId(int id) {
        return model -> model.getUuid() == id;
    }

    public static changer<MyModel> birthPlus() {
        return model -> {
            model.setBirth(model.getBirth().plusDays(1));
            return model;
        };
    }
}

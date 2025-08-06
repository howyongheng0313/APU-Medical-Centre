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

    public static Query<MyModel> checkEmail(String email){
        return model ->  {
            return model.getEmail().equals(email);
        };
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
    
    /*
    UserType.txt
        1. Email
        2. User role (Manager/ Doctor/ Staff/ Customer)
    
    if email exist {get data[1]}
    String role = data[1]
    switch(role):
    case "Manager" -> ...;
    case "Doctor" -> ...;
    case "Staff" -> ...;
    case "Customer" -> ...;
    */
    
}

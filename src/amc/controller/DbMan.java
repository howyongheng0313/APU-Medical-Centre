package amc.controller;

public class DbMan {
    @FunctionalInterface
    public static interface Query<T> {
        boolean apply(T model);
    }
    
    @FunctionalInterface
    public static interface Alter<T> {
        T apply(T model);
    }
}

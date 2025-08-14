package amc.controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface Convertor<E> {
    public E convert(String element);
    public String strify(E element);

    public static final Convertor<LocalDate> DATE = new Convertor<>() {
        @Override
        public LocalDate convert(String element) {
            return LocalDate.parse(element, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        @Override
        public String strify(LocalDate element) {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(element);
        }
    };

    public static final Convertor<Number> NUM = new Convertor<>() {
        @Override
        public Number convert(String element) {
            return Integer.valueOf(element);
        }

        @Override
        public String strify(Number element) {
            return element.toString();
        }
    };
}

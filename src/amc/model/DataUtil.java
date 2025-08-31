package amc.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class DataUtil {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy:MM:dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DecimalFormat AMOUNT_FORMAT;

    static {
        AMOUNT_FORMAT = new DecimalFormat("0.00");
        AMOUNT_FORMAT.setRoundingMode(RoundingMode.HALF_EVEN);
    }

    private DataUtil() {}

    public static LocalDate str2date(String date) {
        return (LocalDate) DATE_FORMAT.parse(date);
    }

    public static LocalTime str2time(String time) {
        return (LocalTime) TIME_FORMAT.parse(time);
    }

    public static String date2str(LocalDate date) {
        return DATE_FORMAT.format(date);
    }

    public static String time2str(LocalTime time) {
        return TIME_FORMAT.format(time);
    }

    public static double str2amount(String amount) {
        return AMOUNT_FORMAT.parse(amount, new ParsePosition(3)).doubleValue();
    }

    public static String amount2str(double amount) {
        return "MYR" + AMOUNT_FORMAT.format(amount);
    }

    public static String formatEmail(String email) {
        return email.strip().toLowerCase();
    }
}

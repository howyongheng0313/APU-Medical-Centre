package amc.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public final class DataUtil {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DecimalFormat AMOUNT_FORMAT;

    private static final String EMAIL_LOCALE = "[a-z0-9](?:[a-z0-9.!#$%&'*+/=?^_`{|}~-]{0,61}[a-z0-9])?";
    private static final String EMAIL_DOMAIN = "[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^(?!.*\\.\\.)" + EMAIL_LOCALE + "@" +
        EMAIL_DOMAIN + "(?:\\." + EMAIL_DOMAIN + ")*\\.[a-z]{2,}$"
    );
    private static final List<String> LICENSE_CODES = List.of(
        "MMC", "NSR", "APC", "LCP", "TCM", "AHP", "DC", "PC"
    );

    static {
        AMOUNT_FORMAT = new DecimalFormat("0.00");
        AMOUNT_FORMAT.setRoundingMode(RoundingMode.HALF_EVEN);
    }

    private DataUtil() {}

    public static LocalDate str2date(String date) {
        return LocalDate.from(DATE_FORMAT.parse(date));
    }

    public static LocalTime str2time(String time) {
        return LocalTime.from(TIME_FORMAT.parse(time));
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
        String lower = email.strip().toLowerCase();
        return EMAIL_PATTERN.matcher(lower).matches() ? lower : "";
    }

    public static boolean validContact(String contact) {
        if (contact == null || contact.length() < 7 || 15 < contact.length()) return false;
        if (!(contact.startsWith("+") || contact.startsWith("0"))) return false;
        if (contact.charAt(1) == '0') return false;
        return (contact.substring(1).chars().allMatch(Character::isDigit));
    }

    public static boolean validLicense(String license) {
        if (license == null) return false;
        for (String code: LICENSE_CODES) {
            if (license.startsWith(code)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(validLicense("MMC 27666"));
    }
}

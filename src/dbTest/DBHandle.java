/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbTest;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author kzy
 */
public class DBHandle {
    @FunctionalInterface
    public static interface RowFilter {
        boolean apply(List<String> row);
    }
    @FunctionalInterface
    public static interface RowChanger {
        List<String> apply(List<String> row);
    }

    private static final Path TEMP_DIR;
    static {
        Path path = Path.of("./temp");
        try {
            Files.createDirectory(path);
        } catch (IOException e) {}
        TEMP_DIR = path;
    }
    private static final Pattern UNESC_PTN = Pattern.compile("\\\\[\\\\n|]|\\|");

    private static List<String> unesc_split(String line) {
        Matcher mch = DBHandle.UNESC_PTN.matcher(line);
        List<String> row  = new ArrayList<>();
        StringBuilder strb = new StringBuilder();
        while (mch.find()) {
            String raw = mch.group();
            String esc = switch (raw) {
                case "\\\\"-> "\\";
                case "\\n" -> "\n";
                case "\\|" -> "|";
                case "|"   -> "";
                default    -> "";
            };
            mch.appendReplacement(strb, Matcher.quoteReplacement(esc));
            if ("|".equals(raw)) {
                row.add(strb.toString());
                strb.setLength(0);
            }
        }
        mch.appendTail(strb);
        row.add(strb.toString());
        return row;
    }

    private static String esc_bind(List<String> row) {
        StringBuilder strb = new StringBuilder();
        for (int index = 0; index < row.size(); index++) {
            if (index != 0) strb.append("|");
            strb.append(row.get(index)
                .replaceAll("\\\\", "\\\\")
                .replaceAll("\\|" , "\\|")
                .replaceAll("\n"  , "\\n")
            );
        }
        return strb.toString();
    }

    private final Path path;

    public DBHandle(String file_path) {
        this.path = Path.of(file_path);
    }

    public boolean insert(List<List<String>> rowList) {
        try (BufferedWriter writer = Files.newBufferedWriter(this.path, StandardOpenOption.APPEND)) {
            for (List<String> row: rowList) {
                writer.write(DBHandle.esc_bind(row));
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<List<String>> select(DBHandle.RowFilter filter, int limit) {
        try (BufferedReader reader = Files.newBufferedReader(this.path)) {
            List<List<String>> result = new ArrayList<>();
            int selected = 0;
            String line;
            reader.readLine();
            while ((selected < limit || limit == -1) && (line = reader.readLine()) != null) {
                List<String> row = DBHandle.unesc_split(line);
                if (filter.apply(row)) {
                    result.add(row);
                    selected++;
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int modify(RowFilter filter, RowChanger changer, int limit) throws IOException {
        int updated = 0;
        Path tmp = Files.createTempFile(DBHandle.TEMP_DIR, "update-", ".tmp");
        try (
            BufferedWriter writer = Files.newBufferedWriter(tmp);
            BufferedReader reader = Files.newBufferedReader(this.path);
        ) {
            String line;
            writer.write(reader.readLine());
            writer.newLine();
            while ((line = reader.readLine()) != null) {
                List<String> row = DBHandle.unesc_split(line);
                if ((updated < limit || limit == -1) && filter.apply(row)) {
                    row = changer.apply(row);
                    updated++;
                }
                if (row == null) continue;
                writer.write(DBHandle.esc_bind(row));
                writer.newLine();
            }
        }
        Files.move(tmp, this.path, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        return updated;
    }

    public int update(RowFilter filter, RowChanger changer, int limit) {
        try {
            return this.modify(filter, changer, limit);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int delete(RowFilter filter, int limit) {
        return this.update(filter, row -> null, limit);
    }
}

package amc.model;

import amc.controller.ROps;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbHandle<T> {
    private static final String SEPARATOR = ":";
    private static final Path TEMP_DIR;
    static {
        Path path = Path.of("./temp");
        try {
            Files.createDirectory(path);
        } catch (IOException e) {}
        TEMP_DIR = path;
    }

    private static final Pattern UNESC_PTN = Pattern.compile("\\\\[\\\\n"+SEPARATOR+"]|\\"+SEPARATOR+"");

    private static List<String> unesc_split(String line) {
        Matcher mch = UNESC_PTN.matcher(line);
        List<String>  strRow = new ArrayList<>();
        StringBuilder strb   = new StringBuilder();
        while (mch.find()) {
            String raw = mch.group();
            String esc = switch (raw) {
                case "\\\\"-> "\\";
                case "\\n" -> "\n";
                case "\\"+SEPARATOR -> SEPARATOR;
                case SEPARATOR -> "";
                default    -> "";
            };
            mch.appendReplacement(strb, Matcher.quoteReplacement(esc));
            if (SEPARATOR.equals(raw)) {
                strRow.add(strb.toString());
                strb.setLength(0);
            }
        }
        mch.appendTail(strb);
        strRow.add(strb.toString());
        return strRow;
    }

    private static String esc_bind(List<String> strRow) {
        StringBuilder strb = new StringBuilder();
        for (int index = 0; index < strRow.size(); index++) {
            if (index != 0) strb.append(SEPARATOR);
            strb.append(strRow.get(index)
                .replaceAll("\\\\", "\\\\")
                .replaceAll("\\"+SEPARATOR , "\\"+SEPARATOR)
                .replaceAll("\n"  , "\\n")
            );
        }
        return strb.toString();
    }

    private final Path path;
    private final DbRow<T> rowTemp;
    
    public DbHandle(DbRow<T> rowTemp, Path path) {
        this.rowTemp = rowTemp;
        this.path = path;
    }

    public boolean insert(List<T> modelLs) {
        try (BufferedWriter writer = Files.newBufferedWriter(this.path, StandardOpenOption.APPEND)) {
            for (T model: modelLs) {
                List<String> strRow = this.rowTemp.getRow(model);
                writer.write(esc_bind(strRow));
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<T> select(int limit, ROps.filter<T> filter) {
        try (BufferedReader reader = Files.newBufferedReader(this.path)) {
            List<T> modelLs = new ArrayList<>();
            int selected = 0;
            String line;
            reader.readLine();
            while ((selected < limit || limit == -1) && (line = reader.readLine()) != null) {
                List<String> strRow = unesc_split(line);
                T model = this.rowTemp.create(strRow);
                if (filter.apply(model)) {
                    modelLs.add(model);
                    selected++;
                }
            }
            return modelLs;
        } catch (IOException e) {
            return null;
        }
    }

    private int modify(int limit, ROps.filter<T> filter, ROps.changer<T> changer) throws IOException {
        int updated = 0;
        Path tmp = Files.createTempFile(TEMP_DIR, "update-", ".tmp");
        try (
            BufferedWriter writer = Files.newBufferedWriter(tmp);
            BufferedReader reader = Files.newBufferedReader(this.path);
        ) {
            String line;
            writer.write(reader.readLine());
            writer.newLine();
            while ((line = reader.readLine()) != null) {
                List<String> strRow = unesc_split(line);
                T model = this.rowTemp.create(strRow);
                if ((updated < limit || limit == -1) && filter.apply(model)) {
                    model = changer.apply(model);
                    updated++;
                }
                if (model == null) continue;
                strRow = this.rowTemp.getRow(model);
                writer.write(esc_bind(strRow));
                writer.newLine();
            }
        }
        Files.move(tmp, this.path, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        return updated;
    }

    public int update(int limit, ROps.filter<T> filter, ROps.changer<T> changer) {
        try {
            return this.modify(limit, filter, changer);
        } catch (IOException e) {
            return -1;
        }
    }

    public int delete(int limit, ROps.filter<T> filter) {
        return this.update(limit, filter, model -> null);
    }
}

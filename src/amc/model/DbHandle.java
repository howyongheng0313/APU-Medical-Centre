package amc.model;

import amc.controller.DbMan;
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
        List<String>  row = new ArrayList<>();
        StringBuilder strb   = new StringBuilder();
        while (mch.find()) {
            String raw = mch.group();
            String esc = switch (raw) {
                case "\\"+SEPARATOR -> SEPARATOR;
                case SEPARATOR -> "";
                case "\\\\"-> "\\";
                case "\\n" -> "\n";
                default    -> "";
            };
            mch.appendReplacement(strb, Matcher.quoteReplacement(esc));
            if (SEPARATOR.equals(raw)) {
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
            if (index != 0) strb.append(SEPARATOR);
            strb.append(row.get(index)
                .replaceAll("\\\\", "\\\\")
                .replaceAll("\\"+SEPARATOR , "\\"+SEPARATOR)
                .replaceAll("\n"  , "\\n")
            );
        }
        return strb.toString();
    }

    private final Path path;
    private final DbAdapter<T> rowAdapter;
    
    public DbHandle(DbAdapter<T> rowAdapter, Path path) {
        this.rowAdapter = rowAdapter;
        this.path = path;
    }

    public boolean insert(List<T> modelLs) {
        try (BufferedWriter writer = Files.newBufferedWriter(this.path, StandardOpenOption.APPEND)) {
            for (T model: modelLs) {
                List<String> row = this.rowAdapter.getRow(model);
                writer.write(esc_bind(row));
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<T> select(int limit, DbMan.Query<T> query) {
        try (BufferedReader reader = Files.newBufferedReader(this.path)) {
            List<T> modelLs = new ArrayList<>();
            int selected = 0;
            String line;
            reader.readLine();
            while ((selected < limit || limit == -1) && (line = reader.readLine()) != null) {
                List<String> row = unesc_split(line);
                T model = this.rowAdapter.getMod(row);
                if (query.apply(model)) {
                    modelLs.add(model);
                    selected++;
                }
            }
            return modelLs;
        } catch (IOException e) {
            return null;
        }
    }

    private int modify(int limit, DbMan.Query<T> query, DbMan.Alter<T> alter) throws IOException {
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
                List<String> row = unesc_split(line);
                T model = this.rowAdapter.getMod(row);
                if ((updated < limit || limit == -1) && query.apply(model)) {
                    model = alter.apply(model);
                    updated++;
                }
                if (model == null) continue;
                row = this.rowAdapter.getRow(model);
                writer.write(esc_bind(row));
                writer.newLine();
            }
        }
        Files.move(tmp, this.path, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        return updated;
    }

    public int update(int limit, DbMan.Query<T> query, DbMan.Alter<T> alter) {
        try {
            return this.modify(limit, query, alter);
        } catch (IOException e) {
            return -1;
        }
    }

    public int delete(int limit, DbMan.Query<T> query) {
        return this.update(limit, query, model -> null);
    }
}

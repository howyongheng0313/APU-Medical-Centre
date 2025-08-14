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
    
    // Class-level variable
    private static final String SEPARATOR = "|";
    private static final Path TEMP_DIR; 
    static {
        Path path = Path.of("./temp");
        try {
            Files.createDirectory(path);
        } catch (IOException e) {}
        TEMP_DIR = path;
    }

    private static final Pattern UNESC_PTN = Pattern.compile("\\\\[\\\\n"+SEPARATOR+"]|\\"+SEPARATOR+"");

    /*
    ** Type: Static method
    ** Desciption: Convert a row of string to [strings]
    ** Use Case: Used when reading each row of data from txt file
    ** Example: "Tom\\:20\\nDeveloper" --> ["Tom:20", "Developer"]
    */
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

    /* 
    ** Type: Static method
    ** Description: Convert [strings] to a row of string
    ** Use case: Used when writting data into txt file
    ** Example: ["Tom:20", "Developer"] --> "Tom\\:20\\nDeveloper"
    */
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

    // Object-level variable
    private final Path path; 
    private final DbAdapter<T> rowAdapter; 
    
    /*
    ** Type: Constructor
    ** Description: Create a databasehandler
    ** Parameters:
    **    1. rowAdpater: Convert string to model || Convert model to string
    **    2. file path: The text file we handling
    */
    public DbHandle(DbAdapter<T> rowAdapter, Path path) {
        this.rowAdapter = rowAdapter;
        this.path = path;
    }

    // Insert
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

    //Select
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

    /* 
    ** Core modify function
    ** Applied in update() and delete()
    */
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

    // Update
    public int update(int limit, DbMan.Query<T> query, DbMan.Alter<T> alter) {
        try {
            return this.modify(limit, query, alter);
        } catch (IOException e) {
            return -1;
        }
    }

    // Delete
    public int delete(int limit, DbMan.Query<T> query) {
        return this.update(limit, query, model -> null);
    }

    public static DbHandle<MyModel> MYMODEL = new DbHandle<> (
        DbAdapter.MYMODEL, Path.of("./database/MyModel.txt")
    );

    public static DbHandle<List<String>> ROLEMAP = new DbHandle<> (
        DbAdapter.DEFAULT, Path.of("./database/RoleMap.txt")
    );

    public static DbHandle<Customer> CUSTOMER = new DbHandle<> (
        DbAdapter.CUSTOMER, Path.of("./database/Customer.txt")
    );
    
    public static DbHandle<Manager> MANAGER = new DbHandle<> (
        DbAdapter.MANAGER, Path.of("./database/Manager.txt")
    );
    
    public static DbHandle<Doctor> DOCTOR = new DbHandle<> (
        DbAdapter.DOCTOR, Path.of("./database/Doctor.txt")
    );
    
    public static DbHandle<Staff> DOCTOR = new DbHandle<> (
        DbAdapter.STAFF, Path.of("./database/Staff.txt")
    );
}

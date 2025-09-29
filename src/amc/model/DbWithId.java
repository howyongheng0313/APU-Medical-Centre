package amc.model;

import amc.model.entity.WithId;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DbWithId<T extends WithId> extends DbHandle<T> {
    private final String idPrefix;
    private int sequence = 0;

    public DbWithId(DbAdapter<T> rowAdapter, Path path, String idPrefix) {
        super(rowAdapter, path);
        this.idPrefix = idPrefix;

        // Initialize sequence safely
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    // Skip non-numeric lines (headers)
                    if (line.matches("\\d+")) {
                        sequence = Integer.parseInt(line);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(); // log error
            }
        }
    }

    @Override
    public boolean insert(List<? extends T> modelLs) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            // Write current sequence at the top of the file
            writer.write(String.format("%03d%n", sequence));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return super.insert(modelLs);
    }

    public String newId() {
        return String.format("%s-%03d", idPrefix, ++sequence);
    }

    public T getById(String id) {
        List<T> entityLs = this.select(1, DbMan.checkById(id));
        return entityLs.size() == 1 ? entityLs.get(0) : null;
    }
}

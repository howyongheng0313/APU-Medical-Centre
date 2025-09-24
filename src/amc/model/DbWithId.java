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
        this.idPrefix = idPrefix;
        super(rowAdapter, path);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String seq = reader.readLine();
            sequence = Integer.parseInt(seq);
        } catch (IOException e) {}
    }

    @Override
    public boolean insert(List<T> modelLs) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.WRITE)) {
            writer.write(String.format("%03d", sequence));
        } catch (IOException e) { return false; }
        return super.insert(modelLs);
    }

    public String newId() {
        return String.format("%s-%03d", idPrefix, ++sequence);
    }

    public T getById(String id) {
        List<T> entityLs = this.select(1, DbMan.checkById(id));
        return entityLs.size() == 1 ? entityLs.getFirst() : null;
    }
}

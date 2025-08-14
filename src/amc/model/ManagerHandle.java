package amc.model;

import java.nio.file.Path;

public class ManagerHandle extends DbHandle {
    
    public ManagerHandle(DbAdapter rowAdapter, Path path) {
        super(rowAdapter, path);
    }
    
}

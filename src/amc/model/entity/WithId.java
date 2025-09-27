package amc.model.entity;

import java.util.Objects;

public abstract class WithId {
    private final String id;

    public WithId(String id) {
        this.id = id;
    }

    public String getId() { return id; }

    @Override
    public boolean equals(Object entity) {
        if (entity instanceof WithId withId) return withId.id.equals(this.id);
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }
}

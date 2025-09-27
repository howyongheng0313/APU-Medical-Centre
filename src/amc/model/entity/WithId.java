package amc.model.entity;

public abstract class WithId {
    private final String id;

    public WithId(String id) {
        this.id = id;
    }

    public String getId() { return id; }
}

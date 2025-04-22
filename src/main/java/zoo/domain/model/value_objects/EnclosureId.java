package zoo.domain.model.value_objects;

public record EnclosureId(String id) {
    public EnclosureId {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Enclosure ID cannot be empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnclosureId that = (EnclosureId) o;
        return id.equals(that.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
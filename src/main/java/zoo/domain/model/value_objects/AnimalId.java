package zoo.domain.model.value_objects;

public record AnimalId(String id) {
    public AnimalId {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Animal ID cannot be empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalId animalId = (AnimalId) o;
        return id.equals(animalId.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
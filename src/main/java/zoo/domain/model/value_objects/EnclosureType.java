package zoo.domain.model.value_objects;

public enum EnclosureType {
    PREDATOR_ENCLOSURE(AnimalType.PREDATOR),
    HERBIVORE_ENCLOSURE(AnimalType.HERBIVORE),
    AVIARY(AnimalType.BIRD),
    AQUARIUM(AnimalType.AQUATIC),
    REPTILE_HOUSE(AnimalType.REPTILE),
    INSECTARIUM(AnimalType.INSECTIVORE);

    private final AnimalType compatibleAnimalType;

    EnclosureType(AnimalType compatibleAnimalType) {
        this.compatibleAnimalType = compatibleAnimalType;
    }

    public boolean isCompatibleWith(AnimalType animalType) {
        return this.compatibleAnimalType == animalType;
    }
}
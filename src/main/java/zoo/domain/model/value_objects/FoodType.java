package zoo.domain.model.value_objects;

public enum FoodType {
    MEAT,
    FISH,
    FRUIT,
    VEGETABLES,
    GRAIN,
    INSECTS,
    UNKNOWN;

    public boolean isCompatibleWith(AnimalType animalType) {
        return switch (this) {
            case MEAT, FISH -> animalType == AnimalType.PREDATOR;
            case FRUIT, VEGETABLES, GRAIN -> animalType == AnimalType.HERBIVORE;
            case INSECTS -> animalType == AnimalType.BIRD || animalType == AnimalType.INSECTIVORE;
            default -> false;
        };
    }
}
package zoo.domain.model;

import lombok.Getter;
import lombok.Setter;
import zoo.domain.model.value_objects.EnclosureId;
import zoo.domain.model.value_objects.EnclosureType;

@Setter
@Getter
public class Enclosure {
    private EnclosureId id;
    private EnclosureType type;
    private double size;
    private int currentAnimals;
    private int maxCapacity;

    public Enclosure() {}

    public Enclosure(EnclosureId id, EnclosureType type, double size, int maxCapacity) {
        this.id = id;
        this.type = type;
        this.size = size;
        this.maxCapacity = maxCapacity;
        this.currentAnimals = 0;
    }

    public boolean canAcceptAnimal(Animal animal) {
        if (currentAnimals >= maxCapacity) return false;
        return type.isCompatibleWith(animal.getType());
    }

    public void addAnimal(Animal animal) {
        if (!canAcceptAnimal(animal)) {
            throw new IllegalStateException("Cannot add animal to enclosure");
        }
        currentAnimals++;
    }

    public void removeAnimal() {
        if (currentAnimals <= 0) {
            throw new IllegalStateException("No animals to remove");
        }
        currentAnimals--;
    }

    public void clean() {
        System.out.println("Mopped enclosure");
        if (currentAnimals > 0) {
            throw new IllegalStateException("Cannot clean enclosure with animals inside");
        }
    }
}
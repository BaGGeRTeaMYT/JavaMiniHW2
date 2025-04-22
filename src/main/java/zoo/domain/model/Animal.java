package zoo.domain.model;

import lombok.Getter;
import lombok.Setter;
import zoo.domain.model.events.AnimalMovedEvent;
import zoo.domain.model.events.DomainEventPublisher;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.model.value_objects.AnimalType;
import zoo.domain.model.value_objects.EnclosureId;
import zoo.domain.model.value_objects.FoodType;

import java.time.LocalDate;

@Setter
@Getter
public class Animal {
    private AnimalId id;
    private String species;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private FoodType favoriteFood;
    private boolean healthy;
    private EnclosureId enclosureId;
    private AnimalType type;

    public Animal() {}

    public Animal(AnimalId id, String species, String name, LocalDate birthDate,
                  String gender, FoodType favoriteFood, boolean healthy, AnimalType type) {
        this.id = id;
        this.species = species;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.favoriteFood = favoriteFood;
        this.healthy = healthy;
        this.enclosureId = null;
        this.type = type;
    }

    public void feed(FoodType food) {
        if (!healthy) {
            throw new IllegalStateException("Cannot feed a sick animal");
        }
        if (!favoriteFood.equals(food)) {
            throw new IllegalArgumentException("This animal doesn't like this food");
        }
    }

    public void heal() {
        this.healthy = true;
    }

    public void moveToEnclosure(Enclosure newEnclosure) {
        if (!newEnclosure.canAcceptAnimal(this)) {
            throw new IllegalStateException("Enclosure cannot accept this animal");
        }
        this.enclosureId = newEnclosure.getId();
        DomainEventPublisher.publish(new AnimalMovedEvent(this.id, newEnclosure.getId()));
    }
}

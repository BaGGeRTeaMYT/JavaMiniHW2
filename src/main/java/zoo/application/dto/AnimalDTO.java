package zoo.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import zoo.domain.model.Animal;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.model.value_objects.AnimalType;
import zoo.domain.model.value_objects.EnclosureId;
import zoo.domain.model.value_objects.FoodType;

import java.time.LocalDate;

@Getter
@Setter
public class AnimalDTO {
    private String id;
    private String species;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String gender;
    private FoodType favoriteFood;
    private boolean healthy;
    private AnimalType type;
    private String enclosureId;

    public AnimalDTO(String id, String species, String name, LocalDate birthDate,
                     String gender, FoodType favoriteFood, boolean healthy, AnimalType type, String enclosureId) {
        this.id = id;
        this.species = species;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.favoriteFood = favoriteFood;
        this.healthy = healthy;
        this.type = type;
        this.enclosureId = enclosureId;
    }

    // Статический метод для преобразования из доменной модели
    public static AnimalDTO fromDomain(Animal animal) {
        return new AnimalDTO(
                animal.getId().id(),
                animal.getSpecies(),
                animal.getName(),
                animal.getBirthDate(),
                animal.getGender(),
                animal.getFavoriteFood(),
                animal.isHealthy(),
                animal.getType(),
                animal.getEnclosureId() != null ? animal.getEnclosureId().id() : null
        );
    }

    // Метод для преобразования в доменную модель
    public Animal toDomain() {
        Animal animal = new Animal();
        animal.setId(new AnimalId(this.id));
        animal.setSpecies(this.species);
        animal.setName(this.name);
        animal.setBirthDate(this.birthDate);
        animal.setGender(this.gender);
        animal.setFavoriteFood(this.favoriteFood);
        animal.setHealthy(this.healthy);
        animal.setType(this.type);
        if (this.enclosureId != null) {
            animal.setEnclosureId(new EnclosureId(this.enclosureId));
        }
        return animal;
    }

}

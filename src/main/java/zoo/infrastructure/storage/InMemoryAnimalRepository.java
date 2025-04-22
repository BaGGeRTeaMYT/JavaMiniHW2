package zoo.infrastructure.storage;

import org.springframework.stereotype.Repository;
import zoo.domain.model.Animal;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.repository.AnimalRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryAnimalRepository implements AnimalRepository {
    private final Map<AnimalId, Animal> animals = new ConcurrentHashMap<>();

    @Override
    public Optional<Animal> findById(AnimalId id) {
        return Optional.ofNullable(animals.get(id));
    }

    @Override
    public List<Animal> findAll() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public Animal save(Animal animal) {
        if (animal.getId() == null) {
            animal.setId(new AnimalId(UUID.randomUUID().toString()));
        }
        animals.put(animal.getId(), animal);
        return animal;
    }

    @Override
    public void deleteById(AnimalId id) {
        animals.remove(id);
    }

    @Override
    public long count() {
        return animals.size();
    }

    @Override
    public List<Animal> findByEnclosureId(AnimalId enclosureId) {
        return animals.values().stream()
                .filter(animal -> enclosureId.equals(animal.getEnclosureId()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> countBySpecies() {
        return animals.values().stream()
                .collect(Collectors.groupingBy(
                        Animal::getSpecies,
                        Collectors.counting()
                ));
    }
}
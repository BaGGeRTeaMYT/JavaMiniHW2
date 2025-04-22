package zoo.domain.repository;

import zoo.domain.model.Animal;
import zoo.domain.model.value_objects.AnimalId;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AnimalRepository {
    Optional<Animal> findById(AnimalId id);
    List<Animal> findAll();
    Animal save(Animal animal);
    void deleteById(AnimalId id);
    long count();
    List<Animal> findByEnclosureId(AnimalId enclosureId);
    Map<String, Long> countBySpecies();
}
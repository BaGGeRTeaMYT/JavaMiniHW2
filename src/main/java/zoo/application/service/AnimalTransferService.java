package zoo.application.service;

import org.springframework.stereotype.Service;
import zoo.domain.model.Animal;
import zoo.domain.model.Enclosure;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.model.value_objects.EnclosureId;
import zoo.domain.repository.AnimalRepository;
import zoo.domain.repository.EnclosureRepository;

@Service
public class AnimalTransferService {
    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;

    public AnimalTransferService(AnimalRepository animalRepository,
                                 EnclosureRepository enclosureRepository) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
    }

    public void transferAnimal(AnimalId animalId, EnclosureId newEnclosureId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        Enclosure newEnclosure = enclosureRepository.findById(newEnclosureId)
                .orElseThrow(() -> new IllegalArgumentException("New enclosure not found"));

        if (animal.getEnclosureId() != null) {
            Enclosure currentEnclosure = enclosureRepository.findById(animal.getEnclosureId())
                    .orElseThrow(() -> new IllegalStateException("Current enclosure not found"));
            currentEnclosure.removeAnimal();
            enclosureRepository.save(currentEnclosure);
        }

        animal.moveToEnclosure(newEnclosure);
        newEnclosure.addAnimal(animal);

        animalRepository.save(animal);
        enclosureRepository.save(newEnclosure);
    }
}

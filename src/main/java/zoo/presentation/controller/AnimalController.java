package zoo.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo.application.dto.AnimalDTO;
import zoo.application.service.AnimalTransferService;
import zoo.domain.model.Animal;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.model.value_objects.EnclosureId;
import zoo.domain.repository.AnimalRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    private final AnimalTransferService animalTransferService;

    public AnimalController(AnimalRepository animalRepository,
                            AnimalTransferService animalTransferService) {
        this.animalRepository = animalRepository;
        this.animalTransferService = animalTransferService;
    }

    @GetMapping
    public List<AnimalDTO> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(AnimalDTO::fromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getAnimalById(@PathVariable String id) {
        return animalRepository.findById(new AnimalId(id))
                .map(AnimalDTO::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AnimalDTO addAnimal(@RequestBody AnimalDTO animalDTO) {
        Animal animal = animalDTO.toDomain();
        animal = animalRepository.save(animal);
        if (animal.getEnclosureId() != null) {
            String enclosureId = animal.getEnclosureId().toString();
            animal.setEnclosureId(null);
            transferAnimal(animal.getId().toString(), enclosureId);
        }
        return AnimalDTO.fromDomain(animal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable String id) {
        animalRepository.deleteById(new AnimalId(id));
    }

    @PostMapping("/{animalId}/transfer/{enclosureId}")
    public void transferAnimal(@PathVariable String animalId,
                               @PathVariable String enclosureId) {
        animalTransferService.transferAnimal(
                new AnimalId(animalId),
                new EnclosureId(enclosureId)
        );
    }
}

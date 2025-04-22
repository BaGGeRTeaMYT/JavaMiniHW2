package zoo.application.service;

import org.springframework.stereotype.Service;
import zoo.application.dto.ZooStatisticsDTO;
import zoo.domain.repository.AnimalRepository;
import zoo.domain.repository.EnclosureRepository;


@Service
public class ZooStatisticsService {
    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;

    public ZooStatisticsService(AnimalRepository animalRepository,
                                EnclosureRepository enclosureRepository) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
    }

    public ZooStatisticsDTO getStatistics() {
        long totalAnimals = animalRepository.count();
        long totalEnclosures = enclosureRepository.count();
        long freeEnclosures = enclosureRepository.findByCurrentAnimalsLessThanMaxCapacity().size();

        return new ZooStatisticsDTO(
                totalAnimals,
                totalEnclosures,
                freeEnclosures,
                animalRepository.countBySpecies(),
                enclosureRepository.countByType()
        );
    }
}

package zoo.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ZooStatisticsDTO {
    private long totalAnimals;
    private long totalEnclosures;
    private long freeEnclosures;
    private Map<String, Long> animalsByType;
    private Map<String, Long> enclosuresByType;

    public ZooStatisticsDTO(long totalAnimals, long totalEnclosures, long freeEnclosures,
                            Map<String, Long> animalsByType, Map<String, Long> enclosuresByType) {
        this.totalAnimals = totalAnimals;
        this.totalEnclosures = totalEnclosures;
        this.freeEnclosures = freeEnclosures;
        this.animalsByType = animalsByType;
        this.enclosuresByType = enclosuresByType;
    }

}

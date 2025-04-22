package zoo.domain.model.events;

import lombok.Getter;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.model.value_objects.EnclosureId;

import java.time.LocalDateTime;

@Getter
public class AnimalMovedEvent {
    private final AnimalId animalId;
    private final EnclosureId newEnclosureId;
    private final LocalDateTime timestamp;

    public AnimalMovedEvent(AnimalId animalId, EnclosureId newEnclosureId) {
        this.animalId = animalId;
        this.newEnclosureId = newEnclosureId;
        this.timestamp = LocalDateTime.now();
    }

}
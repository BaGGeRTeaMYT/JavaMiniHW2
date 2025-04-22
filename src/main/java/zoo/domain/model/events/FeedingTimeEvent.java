package zoo.domain.model.events;

import lombok.Getter;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.model.value_objects.FoodType;

import java.time.LocalDateTime;

@Getter
public class FeedingTimeEvent {
    private final AnimalId animalId;
    private final FoodType foodType;
    private final LocalDateTime timestamp;

    public FeedingTimeEvent(AnimalId animalId, FoodType foodType) {
        this.animalId = animalId;
        this.foodType = foodType;
        this.timestamp = LocalDateTime.now();
    }

}
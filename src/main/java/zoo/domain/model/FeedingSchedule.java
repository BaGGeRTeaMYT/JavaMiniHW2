package zoo.domain.model;

import lombok.Getter;
import lombok.Setter;
import zoo.domain.model.events.DomainEventPublisher;
import zoo.domain.model.events.FeedingTimeEvent;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.model.value_objects.FoodType;

import java.time.LocalTime;

@Setter
@Getter
public class FeedingSchedule {
    private String id;
    private AnimalId animalId;
    private LocalTime feedingTime;
    private FoodType foodType;
    private boolean completed;

    public FeedingSchedule() {}

    public FeedingSchedule(String id, AnimalId animalId, LocalTime feedingTime, FoodType foodType) {
        this.id = id;
        this.animalId = animalId;
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.completed = false;
    }

    public void updateSchedule(LocalTime newTime, FoodType newFood) {
        this.feedingTime = newTime;
        this.foodType = newFood;
        this.completed = false;
    }

    public void markAsCompleted() {
        this.completed = true;
        DomainEventPublisher.publish(new FeedingTimeEvent(this.animalId, this.foodType));
    }
}

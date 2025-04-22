package zoo.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import zoo.domain.model.FeedingSchedule;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.model.value_objects.FoodType;

import java.time.LocalTime;

@Getter
@Setter
public class FeedingScheduleDTO {
    private String id;
    private String animalId;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime feedingTime;

    private FoodType foodType;
    private boolean completed;

    public FeedingScheduleDTO(String id, String animalId, LocalTime feedingTime,
                              FoodType foodType, boolean completed) {
        this.id = id;
        this.animalId = animalId;
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.completed = completed;
    }

    public static FeedingScheduleDTO fromDomain(FeedingSchedule schedule) {
        return new FeedingScheduleDTO(
                schedule.getId(),
                schedule.getAnimalId().id(),
                schedule.getFeedingTime(),
                schedule.getFoodType(),
                schedule.isCompleted()
        );
    }

    public FeedingSchedule toDomain() {
        FeedingSchedule schedule = new FeedingSchedule();
        schedule.setId(this.id);
        schedule.setAnimalId(new AnimalId(this.animalId));
        schedule.setFeedingTime(this.feedingTime);
        schedule.setFoodType(this.foodType);
        schedule.setCompleted(this.completed);
        return schedule;
    }

}
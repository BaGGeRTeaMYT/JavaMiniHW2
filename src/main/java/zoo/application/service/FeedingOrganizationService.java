package zoo.application.service;

import org.springframework.stereotype.Service;
import zoo.domain.model.FeedingSchedule;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.repository.FeedingScheduleRepository;
import zoo.domain.model.value_objects.FoodType;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class FeedingOrganizationService {
    private final FeedingScheduleRepository feedingScheduleRepository;

    public FeedingOrganizationService(FeedingScheduleRepository feedingScheduleRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
    }

    public FeedingSchedule scheduleFeeding(String animalId, LocalTime time, FoodType foodType) {
        FeedingSchedule schedule = new FeedingSchedule();
        schedule.setId(UUID.randomUUID().toString());
        schedule.setAnimalId(new AnimalId(animalId));
        schedule.setFeedingTime(time);
        schedule.setFoodType(foodType);
        return feedingScheduleRepository.save(schedule);
    }

    public List<FeedingSchedule> getTodaysSchedule() {
        return feedingScheduleRepository.findAll();
    }

    public List<FeedingSchedule> getScheduleForAnimal(String animalId) {
        return feedingScheduleRepository.findByAnimalId(new AnimalId(animalId));
    }

    public FeedingSchedule markFeedingAsCompleted(String scheduleId) {
        FeedingSchedule schedule = feedingScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        schedule.markAsCompleted();
        return feedingScheduleRepository.save(schedule);
    }

    public void deleteSchedule(String scheduleId) {
        if (feedingScheduleRepository.findById(scheduleId).isEmpty()) {
            throw new IllegalArgumentException("Feeding schedule not found with id: " + scheduleId);
        }
        feedingScheduleRepository.deleteById(scheduleId);
    }
}

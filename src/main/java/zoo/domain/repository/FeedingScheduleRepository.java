package zoo.domain.repository;

import zoo.domain.model.FeedingSchedule;
import zoo.domain.model.value_objects.AnimalId;

import java.util.List;
import java.util.Optional;

public interface FeedingScheduleRepository {
    Optional<FeedingSchedule> findById(String id);
    List<FeedingSchedule> findAll();
    FeedingSchedule save(FeedingSchedule schedule);
    void deleteById(String id);
    List<FeedingSchedule> findByAnimalId(AnimalId animalId);
}
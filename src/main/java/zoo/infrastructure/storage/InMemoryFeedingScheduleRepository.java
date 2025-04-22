package zoo.infrastructure.storage;

import org.springframework.stereotype.Repository;
import zoo.domain.model.FeedingSchedule;
import zoo.domain.model.value_objects.AnimalId;
import zoo.domain.repository.FeedingScheduleRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryFeedingScheduleRepository implements FeedingScheduleRepository {
    private final Map<String, FeedingSchedule> schedules = new ConcurrentHashMap<>();

    @Override
    public Optional<FeedingSchedule> findById(String id) {
        return Optional.ofNullable(schedules.get(id));
    }

    @Override
    public List<FeedingSchedule> findAll() {
        return new ArrayList<>(schedules.values());
    }

    @Override
    public FeedingSchedule save(FeedingSchedule schedule) {
        schedules.put(schedule.getId(), schedule);
        return schedule;
    }

    @Override
    public void deleteById(String id) {
        schedules.remove(id);
    }

    @Override
    public List<FeedingSchedule> findByAnimalId(AnimalId animalId) {
        return schedules.values().stream()
                .filter(s -> animalId.equals(s.getAnimalId()))
                .collect(Collectors.toList());
    }
}
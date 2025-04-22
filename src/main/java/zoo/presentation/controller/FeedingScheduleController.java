package zoo.presentation.controller;

import org.springframework.web.bind.annotation.*;
import zoo.application.dto.FeedingScheduleDTO;
import zoo.application.service.FeedingOrganizationService;
import zoo.domain.model.value_objects.FoodType;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feeding-schedules")
public class FeedingScheduleController {
    private final FeedingOrganizationService feedingOrganizationService;


    public FeedingScheduleController(FeedingOrganizationService feedingOrganizationService) {
        this.feedingOrganizationService = feedingOrganizationService;
    }

    @GetMapping
    public List<FeedingScheduleDTO> getAllSchedules() {
        return feedingOrganizationService.getTodaysSchedule().stream()
                .map(FeedingScheduleDTO::fromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/animal/{animalId}")
    public List<FeedingScheduleDTO> getSchedulesForAnimal(@PathVariable String animalId) {
        return feedingOrganizationService.getScheduleForAnimal(animalId).stream()
                .map(FeedingScheduleDTO::fromDomain)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FeedingScheduleDTO scheduleFeeding(
            @RequestParam String animalId,
            @RequestParam String time,
            @RequestParam FoodType foodType) {
        return FeedingScheduleDTO.fromDomain(
                feedingOrganizationService.scheduleFeeding(
                        animalId,
                        LocalTime.parse(time),
                        foodType
                )
        );
    }

    @PostMapping("/{id}/complete")
    public FeedingScheduleDTO completeFeeding(@PathVariable String id) {
        return FeedingScheduleDTO.fromDomain(
                feedingOrganizationService.markFeedingAsCompleted(id)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable String id) {
        feedingOrganizationService.deleteSchedule(id);
    }
}

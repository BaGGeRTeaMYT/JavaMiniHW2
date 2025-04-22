package zoo.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zoo.application.dto.ZooStatisticsDTO;
import zoo.application.service.ZooStatisticsService;

@RestController
@RequestMapping("/api/statistics")
public class ZooStatisticsController {
    private final ZooStatisticsService zooStatisticsService;

    public ZooStatisticsController(ZooStatisticsService zooStatisticsService) {
        this.zooStatisticsService = zooStatisticsService;
    }

    @GetMapping
    public ZooStatisticsDTO getStatistics() {
        return zooStatisticsService.getStatistics();
    }
}
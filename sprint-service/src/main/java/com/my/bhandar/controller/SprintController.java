package com.my.bhandar.controller;

import com.my.bhandar.service.SprintService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping("/start-sprints/{relayId}")
    public void startSprints(@PathVariable String relayId) {
        sprintService.sprint(relayId);
    }

}
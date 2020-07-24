package com.my.bhandar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class RelayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RelayService.class);

    private final RestTemplate restTemplate;
    private final Boolean runAsSingleService;
    private final DummySprintService dummySprintService;

    public RelayService(RestTemplate restTemplate, DummySprintService dummySprintService,
                        @Value("${runAsSingleService:false}") Boolean runAsSingleService) {
        this.restTemplate = restTemplate;
        this.dummySprintService = dummySprintService;
        this.runAsSingleService = runAsSingleService;
    }

    public String start() {
        String relayId = UUID.randomUUID().toString();
        executeSprint(relayId);
        return relayId;
    }

    private void executeSprint(String relayId) {
        if (runAsSingleService)
            executeSprintLocally(relayId);
        else
            executeSprintRemote(relayId);
    }


    private void executeSprintLocally(String relayId) {
        LOGGER.info("Running as single service");
        dummySprintService.sprint(relayId);
    }

    private void executeSprintRemote(String relayId) {
        LOGGER.info("Running as multi service");
        restTemplate.getForEntity("http://localhost:8081/start-sprints/" + relayId, String.class);
    }
}
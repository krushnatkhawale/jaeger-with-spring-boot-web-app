package com.my.bhandar.controller;

import com.my.bhandar.service.RelayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelayController {

    private final RelayService relayService;

    public RelayController(RelayService relayService) {
        this.relayService = relayService;
    }

    @GetMapping("/start-relay")
    public ResponseEntity<String> startRelay() {
        String relayId = relayService.start();
        return ResponseEntity.ok(relayId);
    }
}
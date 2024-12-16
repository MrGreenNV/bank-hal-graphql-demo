package ru.afilonov.restservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.afilonov.restservice.services.AmpqService;

@RestController
@RequestMapping("/ampq/report")
public class AmpqController {

    private final AmpqService ampqService;

    @Autowired
    public AmpqController(AmpqService ampqService) {
        this.ampqService = ampqService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> sendMessage(@RequestBody final String request) {
        ampqService.sendMessage(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

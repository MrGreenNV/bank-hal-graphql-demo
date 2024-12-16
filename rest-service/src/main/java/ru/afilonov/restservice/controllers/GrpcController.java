package ru.afilonov.restservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.afilonov.restservice.services.GrpcService;

@RestController
@RequestMapping("/grpc/report")
public class GrpcController {
    private final GrpcService grpcService;

    @Autowired
    public GrpcController(GrpcService grpcService) {
        this.grpcService = grpcService;
    }

    @GetMapping
    public ResponseEntity<String> getReport(@RequestParam final long personId) {
        return ResponseEntity.ok(grpcService.getPathOfReport(personId));
    }
}

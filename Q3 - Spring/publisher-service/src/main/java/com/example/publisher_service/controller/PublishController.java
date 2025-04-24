package com.example.publisher_service.controller;

import com.example.publisher_service.model.Person;
import com.example.publisher_service.service.PersonPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publish-person")
public class PublishController {

    private final PersonPublisher publisher;

    private PublishController (PersonPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    public ResponseEntity<String> publish(@RequestBody Person person) {
        publisher.publish(person);
        return ResponseEntity.ok("Published to queue");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleValidationError(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

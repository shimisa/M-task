package com.example.publisher_service.service;

import com.example.publisher_service.config.RabbitConfig;
import com.example.publisher_service.model.Person;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class PersonPublisher {

    private final Logger logger = LoggerFactory.getLogger(PersonPublisher.class);
    private final RabbitTemplate rabbitTemplate;
    private final Validator validator;

    public PersonPublisher(RabbitTemplate rabbitTemplate, Validator validator) {
        this.rabbitTemplate = rabbitTemplate;
        this.validator = validator;
    }

    public void publish(@Valid Person person) {
        var violations = validator.validate(person);
        if (!violations.isEmpty()) {
            var errorMessage = violations.stream()
                    .map(v -> v.getPropertyPath() + " " + v.getMessage())
                    .reduce("", (a, b) -> a + " " + b);
            logger.error("Validation errors: {}", errorMessage);
            throw new IllegalArgumentException("Invalid person data: " + errorMessage);
        }

        logger.info("Publishing person to RabbitMQ: {}", person);
        rabbitTemplate.convertAndSend(RabbitConfig.PERSON_QUEUE, person);
    }
}
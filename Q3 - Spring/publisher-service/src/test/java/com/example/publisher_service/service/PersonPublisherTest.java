package com.example.publisher_service.service;

import com.example.publisher_service.config.RabbitConfig;
import com.example.publisher_service.model.Person;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    private Validator validator;
    private PersonPublisher personPublisher;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        personPublisher = new PersonPublisher(rabbitTemplate, validator);
    }

    @Test
    void shouldPublishValidPerson() {
        Person person = new Person(
                "John",
                "Doe",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1)
        );

        personPublisher.publish(person);

        verify(rabbitTemplate).convertAndSend(anyString(), any(Person.class));
    }

    @Test
    void shouldThrowExceptionWhenFirstNameIsBlank() {
        Person person = new Person(
                "",
                "Doe",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1)
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> personPublisher.publish(person)
        );
        assertTrue(exception.getMessage().contains("First name is required"));
        verify(rabbitTemplate, never()).convertAndSend(anyString(), any(Person.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        Person person = new Person(
                "John",
                "Doe",
                "invalid-email",
                LocalDate.of(1990, 1, 1)
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> personPublisher.publish(person)
        );
        assertTrue(exception.getMessage().contains("Email should be valid"));
        verify(rabbitTemplate, never()).convertAndSend(anyString(), any(Person.class));
    }

    @Test
    void shouldThrowExceptionWhenBirthDateIsInFuture() {
        Person person = new Person(
                "John",
                "Doe",
                "john.doe@example.com",
                LocalDate.now().plusDays(1)
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> personPublisher.publish(person)
        );
        assertTrue(exception.getMessage().contains("Birth date must be in the past"));
        verify(rabbitTemplate, never()).convertAndSend(anyString(), any(Person.class));
    }
}
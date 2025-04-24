package com.example.consumer_service.listener;

import com.example.consumer_service.client.PersonServiceClient;
import com.example.consumer_service.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonListenerTest {

    @Mock
    private PersonServiceClient personClient;

    private PersonListener personListener;

    @BeforeEach
    void setUp() {
        personListener = new PersonListener(personClient);
    }

    @Test
    void receive_ShouldCallPersonService_WhenPersonIsValid() {
        // Arrange
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john@example.com");
        person.setBirthDate(LocalDate.parse("1990-01-01"));

        // Act
        personListener.receive(person);

        // Assert
        verify(personClient, times(1)).sendToPersonService(person);
    }

    @Test
    void receive_ShouldHandleNullPerson() {
        // Act
        personListener.receive(null);

        // Assert
        verify(personClient, never()).sendToPersonService(any());
    }

    @Test
    void receive_ShouldHandleEmptyPerson() {
        // Arrange
        Person person = new Person();

        // Act
        personListener.receive(person);

        // Assert
        verify(personClient, never()).sendToPersonService(any());
    }

    @Test
    void receive_ShouldHandlePersonClientException() {
        // Arrange
        Person person = new Person();
        person.setFirstName("John");
        doThrow(new RuntimeException("Service error"))
                .when(personClient).sendToPersonService(any());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            personListener.receive(person);
        });
    }
}
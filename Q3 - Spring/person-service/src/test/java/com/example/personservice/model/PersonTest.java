package com.example.personservice.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void shouldCreatePersonWithAllFields() {
        // Arrange
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String email = "john@example.com";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);

        // Act
        Person person = new Person(id, firstName, lastName, email, birthDate);

        // Assert
        assertEquals(id, person.getId());
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(email, person.getEmail());
        assertEquals(birthDate, person.getBirthDate());
    }

    @Test
    void shouldCreatePersonWithNullId() {
        // Arrange
        String firstName = "Jane";
        String lastName = "Doe";
        String email = "jane@example.com";
        LocalDate birthDate = LocalDate.of(1995, 6, 15);

        // Act
        Person person = new Person(null, firstName, lastName, email, birthDate);

        // Assert
        assertNull(person.getId());
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(email, person.getEmail());
        assertEquals(birthDate, person.getBirthDate());
    }
}
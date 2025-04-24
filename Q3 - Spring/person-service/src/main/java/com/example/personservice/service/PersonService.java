package com.example.personservice.service;

import com.example.personservice.model.Person;
import com.example.personservice.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);


    private final PersonRepository personRepo;

    public PersonService(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    public Person save(Person person) {
        logger.info("Saving person to the DB, ID:  {}", person);
        return personRepo.save(person);
    }

    public List<Person> getAll() {
        return personRepo.findAll();
    }

    public Person getPersonById(Long id) {
        logger.info("Fetching person of ID: {}", id);
        return personRepo.findById(id).orElseThrow(
                () -> new IllegalStateException("person not found")
        );
    }

    public void delete(Long id) {
        logger.info("Dleting person of ID: {}", id);
        personRepo.deleteById(id);
    }
}

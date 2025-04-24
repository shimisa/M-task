package com.example.consumer_service.listener;

import com.example.consumer_service.client.PersonServiceClient;
import com.example.consumer_service.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PersonListener {
    private final Logger logger = LoggerFactory.getLogger(PersonListener.class);

    private PersonServiceClient personClient;

    public PersonListener(PersonServiceClient personClient) {
        this.personClient = personClient;
    }


    @RabbitListener(queues = "person-queue")
    public void receive(Person person) {
        logger.info("Received from RabbitMQ: {}", person);

        if (person == null || person.getFirstName() == null) {
            logger.warn("Invalid person data received");
            return;
        }

        try {
            personClient.sendToPersonService(person);
        } catch (RuntimeException e) {
            logger.error("Error processing person: {}", e.getMessage());
            // Let the exception propagate to trigger message rejection
            throw e;
        }
    }
}

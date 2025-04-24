package com.example.consumer_service.client;

import com.example.consumer_service.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendToPersonService(Person person) {
        restTemplate.postForEntity("http://localhost:8081/person", person, String.class);
    }
}

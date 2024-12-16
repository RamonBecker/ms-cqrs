package com.cqrs.protoapi.controller;


import com.cqrs.protoapi.models.Person;
import com.cqrs.protoapi.services.IPeopleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final IPeopleService peopleService;

    @Value("${api.version}")
    private String apiVersion;

    public PeopleController(IPeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/version")
    public String getApiVersion() {
        return apiVersion;
    }

    @GetMapping("/create-people/{quantity}")
    public String createPeople(@PathVariable("quantity") Integer quantity) {
        peopleService.generatePeople(quantity);
        return String.format("Create %d people", quantity);
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getPeoples() {
        return ResponseEntity.ok(peopleService.getPeoples());

    }
}

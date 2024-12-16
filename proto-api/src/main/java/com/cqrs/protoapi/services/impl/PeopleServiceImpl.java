package com.cqrs.protoapi.services.impl;

import com.cqrs.protoapi.models.Person;
import com.cqrs.protoapi.services.IPeopleService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PeopleServiceImpl implements IPeopleService {

    private final List<Person> peoples = new ArrayList<>();
    private final Faker faker = new Faker();

    @Override
    public List<Person> getPeoples() {
        return peoples;
    }

    @Override
    public Optional<Person> getPersonById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Person> getPersonByName(String name) {
        return List.of();
    }

    @Override
    public Person create(Person person) {
        return null;
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void generatePeople(Integer quantity) {

        if(quantity <= 0) return;

        peoples.clear();

        for (int i = 0; i < quantity; i++) {
            Person person = Person
                         .builder()
                         .id(UUID.randomUUID().toString())
                         .fullname(faker.name().fullName())
                         .birthDate(faker.date().birthday())
                         .age(faker.number().numberBetween(1, 100))
                          .build();
            peoples.add(person);
        }

    }
}

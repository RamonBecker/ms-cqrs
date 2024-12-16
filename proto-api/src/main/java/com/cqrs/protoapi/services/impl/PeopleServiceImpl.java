package com.cqrs.protoapi.services.impl;

import com.cqrs.protoapi.models.Person;
import com.cqrs.protoapi.services.IPeopleService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements IPeopleService {

    private final List<Person> peoples = new ArrayList<>();
    private final Faker faker = new Faker();

    @Override
    public List<Person> getPeoples() {
        return peoples;
    }

    @Override
    public Optional<Person> getPersonById(String idPerson) {
        return peoples.stream().filter(p -> p.getId().equals(idPerson)).findFirst();
    }

    @Override
    public List<Person> getPersonByName(String name) {
        return peoples.stream().filter(p -> p.getFullname().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public Person create(Person person) {
        var newPerson = Person
                .builder()
                .id(UUID.randomUUID().toString())
                .fullname(person.getFullname())
                .birthDate(person.getBirthDate())
                .age(person.getAge())
                .build();
        peoples.add(newPerson);
        return newPerson;
    }

    @Override
    public Person update(String id, Person person) {

        Optional<Person> personToUpdate = getPersonById(id);

        if (personToUpdate.isEmpty())
            throw new RuntimeException("Person not found");

        peoples.remove(personToUpdate.get());

        var updatePeople = changePerson(id, person);

        peoples.add(updatePeople);

        return updatePeople;
    }

    private Person changePerson(String id, Person person) {
        return Person
                .builder()
                .id(id)
                .fullname(person.getFullname())
                .birthDate(person.getBirthDate())
                .age(person.getAge())
                .build();
    }

    @Override
    public void delete(String id) {
        var findPerson = getPersonById(id).orElseThrow(() -> new NoSuchElementException("Element not found"));

        peoples.remove(findPerson);
    }

    @Override
    public void generatePeople(Integer quantity) {

        if (quantity <= 0) return;

        peoples.clear();

        for (int i = 0; i < quantity; i++) {
            var person = Person
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

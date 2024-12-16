package com.cqrs.protoapi.services;

import com.cqrs.protoapi.models.Person;

import java.util.List;
import java.util.Optional;

public interface IPeopleService {

    List<Person> getPeoples();

    Optional<Person> getPersonById(String id);

    List<Person> getPersonByName(String name);

    Person create(Person person);

    Person update(Person person);

    void delete(String id);

    void generatePeople(Integer quantity);

}

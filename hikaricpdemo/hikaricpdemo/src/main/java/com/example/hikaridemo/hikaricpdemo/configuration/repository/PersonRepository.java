package com.example.hikaridemo.hikaricpdemo.configuration.repository;

import com.example.hikaridemo.hikaricpdemo.configuration.domain.Person;

import java.util.List;

public interface PersonRepository {

    Person create(Person person);
    Person read(Long id);
    Person update(Person person);
    void delete(Long id);

    List<Person> getAll();

}

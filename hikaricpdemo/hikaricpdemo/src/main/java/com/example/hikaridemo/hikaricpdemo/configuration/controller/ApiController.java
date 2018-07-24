package com.example.hikaridemo.hikaricpdemo.configuration.controller;

import com.example.hikaridemo.hikaricpdemo.configuration.domain.Person;
import com.example.hikaridemo.hikaricpdemo.configuration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.create(person);
    }

    @GetMapping("/person")
    public Person getPerson(@RequestParam("id") Long id) {
        return personRepository.read(id);
    }


}

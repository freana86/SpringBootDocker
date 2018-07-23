package com.example.jdbc.jdbcdemo.controller;

import com.example.jdbc.jdbcdemo.domain.Uttag;
import com.example.jdbc.jdbcdemo.domain.UttagBuilder;
import com.example.jdbc.jdbcdemo.repository.UttagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SystemAPI {

    @Autowired
    private UttagRepository uttagRepository;

    @GetMapping("/uttag")
    public Uttag createUttag() {
        return uttagRepository.create(UttagBuilder.example().build());
    }

    @DeleteMapping("/uttag/clean")
    public void clearDatabase() {
        this.uttagRepository.dropDB();
    }

    @GetMapping("/uttag/all")
    public List<Uttag> getAllUttag(@RequestParam("clean") boolean cleanDB) {
        return uttagRepository.findAll();
    }


}

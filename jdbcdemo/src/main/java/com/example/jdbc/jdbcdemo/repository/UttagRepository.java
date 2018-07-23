package com.example.jdbc.jdbcdemo.repository;

import com.example.jdbc.jdbcdemo.domain.Uttag;

import java.util.List;


public interface UttagRepository {

    Uttag create(Uttag uttag);

    Uttag read(Integer id);

    Uttag update(Uttag uttag);

    void delete(Integer id);

    void dropDB();

    void createAll(List<Uttag> uttagList);
    List<Uttag> findAll();
}

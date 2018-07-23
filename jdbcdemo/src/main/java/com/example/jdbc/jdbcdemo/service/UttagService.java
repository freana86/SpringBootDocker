package com.example.jdbc.jdbcdemo.service;

import com.example.jdbc.jdbcdemo.domain.Uttag;

import java.util.List;

public interface UttagService {

    // Intern processing
    void processUttag();

    // For API
    List<Uttag> getAllUttag(boolean cleanDB);
    Uttag getUttagBy(Integer id);

    void removeUttag(Integer id);
    void clearDB();

    boolean sync(boolean isActive);

}

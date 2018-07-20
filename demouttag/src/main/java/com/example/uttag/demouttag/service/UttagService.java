package com.example.uttag.demouttag.service;

import com.example.uttag.demouttag.domain.Uttag;

import java.util.List;

public interface UttagService {

    List<Uttag> getUttagList();
    Integer getUttagListSize();

    Integer setMaxCreationOfUttag(Integer max);
    boolean isActive(boolean isActive);

}

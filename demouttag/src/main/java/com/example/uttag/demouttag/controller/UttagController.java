package com.example.uttag.demouttag.controller;


import com.example.uttag.demouttag.domain.Uttag;
import com.example.uttag.demouttag.service.UttagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UttagController {

    @Autowired
    private UttagService uttagService;

    @GetMapping("/uttag")
    public List<Uttag> getUttagList() {
        return uttagService.getUttagList();
    }

    @GetMapping("/uttag/active")
    public boolean getUttagListSize(@RequestParam("active") boolean isActive) {
        return uttagService.isActive(isActive);
    }

    @GetMapping("/uttag/maxuttag")
    public Integer setMaxCreationOfUttag(@RequestParam("max") Integer max) {
        return uttagService.setMaxCreationOfUttag(max);
    }

}

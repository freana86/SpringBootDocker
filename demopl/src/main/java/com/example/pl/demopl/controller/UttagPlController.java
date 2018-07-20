package com.example.pl.demopl.controller;


import com.example.pl.demopl.domain.Uttag;
import com.example.pl.demopl.service.UttagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UttagPlController {

    @Autowired
    private UttagService uttagService;

    @GetMapping("/uttag")
    public Uttag getUttag(@RequestParam("id") Integer id) {
        return uttagService.getUttagBy(id);
    }

    @GetMapping("/uttag/all")
    public List<Uttag> getAllUttag(@RequestParam("clean") boolean cleanDB) {
        return uttagService.getAllUttag(cleanDB);
    }

    @DeleteMapping("/remove")
    public void removeUttag(@RequestParam("id") Integer id) {
        uttagService.removeUttag(id);
    }

    @DeleteMapping("/remove/all")
    public void removeALL() {
        uttagService.clearDB();
    }

}

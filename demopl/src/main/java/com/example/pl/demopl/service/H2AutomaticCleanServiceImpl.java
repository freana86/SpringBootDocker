package com.example.pl.demopl.service;

import com.example.pl.demopl.domain.Uttag;
import com.example.pl.demopl.repository.UttagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class H2AutomaticCleanServiceImpl implements CleaningService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UttagRepository uttagRepository;

    @Override
    @Transactional
    @Scheduled(initialDelay = 1000, fixedDelay = 1_000)
    public void cleanRepository() {
        int elementSize = StreamSupport.stream(uttagRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()).size();
        if(elementSize >= 1000) {
            uttagRepository.deleteAll();
            logger.info("Automatic cleaning for database. Element size: {}", elementSize);
        }

    }
}

package com.example.jdbc.jdbcdemo.service;


import com.example.jdbc.jdbcdemo.client.UttagClient;
import com.example.jdbc.jdbcdemo.domain.Uttag;
import com.example.jdbc.jdbcdemo.repository.UttagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class DemoUttagServiceImpl implements UttagService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UttagClient uttagClient;

    @Autowired
    private UttagRepository uttagRepository;

    private boolean isActive = true;

    @Override
    @Scheduled(initialDelay = 2_000, fixedDelay = 2_000)
    public void processUttag() {
        if(isActive) {
            List<Uttag> uttagList = uttagClient.getUttagList();
            uttagRepository.createAll(uttagList);
        } else {
           Integer totalSize = StreamSupport.stream(uttagRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList()).size();
            logger.info("Uttag sync is disabled. Current Uttag stored i database: {}", totalSize);
        }
    }

    @Override
    public List<Uttag> getAllUttag(boolean cleanDB) {
        List<Uttag> uttagList = StreamSupport.stream(uttagRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if(cleanDB) {
            this.clearDB();
        }

        return uttagList;
    }

    @Override
    public Uttag getUttagBy(Integer id) {
        return uttagRepository.read(id);
    }

    @Override
    public void removeUttag(Integer id) {
        uttagRepository.delete(id);
    }

    @Override
    public void clearDB() {
        uttagRepository.dropDB();
        logger.info("Clear Database");
    }

    @Override
    public boolean sync(boolean isActive) {
        this.isActive = isActive;
        return this.isActive;
    }
}

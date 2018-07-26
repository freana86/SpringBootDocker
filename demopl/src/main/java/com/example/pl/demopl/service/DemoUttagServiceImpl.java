package com.example.pl.demopl.service;

import com.example.pl.demopl.client.UttagClient;
import com.example.pl.demopl.client.UttagClientException;
import com.example.pl.demopl.domain.Uttag;
import com.example.pl.demopl.repository.UttagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
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
        if (isActive) {
            logger.info("Processing data...");
            List<Uttag> uttagList = uttagClient.getUttagList();
            uttagRepository.saveAll(uttagList);
        } else {
            Integer totalSize = StreamSupport.stream(uttagRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList()).size();
            logger.debug("Uttag sync is disabled. Current Uttag stored i database: {}", totalSize);
        }

    }

    @Recover
    private void recover(RuntimeException rE) {
        logger.info("Failed to recover. Turning off sync to service. Error msg: {}", rE.getMessage());
        this.isActive = false;
    }

    @Override
    public List<Uttag> getAllUttag(boolean cleanDB) {
        List<Uttag> uttagList = StreamSupport.stream(uttagRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if (cleanDB) {
            this.clearDB();
        }

        return uttagList;
    }

    @Override
    public Uttag getUttagBy(Integer id) {
        return uttagRepository.findById(id).get();
    }

    @Override
    public void removeUttag(Integer id) {
        uttagRepository.deleteById(id);
    }

    @Override
    public void clearDB() {
        uttagRepository.deleteAll();
        logger.debug("Clear Database");
    }

    @Override
    public boolean sync(boolean isActive) {
        this.isActive = isActive;
        return this.isActive;
    }
}

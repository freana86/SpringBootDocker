package com.example.uttag.demouttag.service;

import com.example.uttag.demouttag.domain.Uttag;
import com.example.uttag.demouttag.domain.UttagBuilder;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoUttagServiceImpl implements UttagService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<Uttag> uttagList;

    private Integer MAX_UTTAG = 5;
    private boolean isActive = true;

    public DemoUttagServiceImpl() {
        uttagList = new ArrayList<>();
    }

    @Override
    public List<Uttag> getUttagList() {
        List<Uttag> deliverList = new ArrayList<>();
        for (Uttag uttag : this.uttagList) {
            Uttag u = new Uttag();
            u.setId(uttag.getId());
            u.setType(uttag.getType());
            u.setUuid(uttag.getUuid());
            deliverList.add(u);
        }
        this.uttagList.clear();
        return deliverList;
    }

    @Override
    public Integer getUttagListSize() {
        if(uttagList == null) {
            return 0;
        }
        return uttagList.size();
    }

    @Override
    public Integer setMaxCreationOfUttag(Integer max) {
        MAX_UTTAG = max;
        return MAX_UTTAG;
    }

    @Override
    public boolean isActive(boolean isActive) {
        this.isActive = isActive;
        return this.isActive;
    }

    @Scheduled(fixedDelay = 10_000)
    private void initUttagList() {
        if(isActive) {
            uttagList.addAll(this.createUttagList());
            logger.info("Uttag waiting for processing: {}", uttagList.size());
        } else {
            logger.info("Uttagservice is not active");
        }
    }

    private List<Uttag> createUttagList() {
        List<Uttag> list = new ArrayList<>();
        int randomCounter = RandomUtils.nextInt(1, MAX_UTTAG);

        for (int i = 0; i < randomCounter; i++ ) {
            list.add(UttagBuilder.example().build());
        }
        return list;
    }

}

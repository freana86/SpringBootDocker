package com.example.democlient.democlient.service;

import com.example.democlient.democlient.client.UttagPLClient;
import com.example.democlient.democlient.domain.Uttag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoClientServiceImplMysql implements ClientService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier(value = "uttagPlMySQLClient")
    private UttagPLClient uttagClient;

    private boolean isActive = false;
    private boolean cleanDB = false;

    @Override
    @Scheduled(initialDelay = 1_000, fixedDelay = 2_000)
    public void processAll() {
        if(isActive) {
            List<Uttag> uttagList = uttagClient.getAllUttag(cleanDB);

            if(!uttagList.isEmpty()) {
                for (Uttag uttag : uttagList) {
                    logger.info("UttagID:{}, UUID:{}, TYPE:{}", uttag.getId(), uttag.getUuid(), uttag.getType());
                    logger.info("Number of uttag recived from PL: {}", uttagList.size());
                    logger.info("");
                }
            } else {
                logger.info("No Uttag recieved from Server");
            }
        } else {
            logger.info("MYSQL Client not enabled.");
        }
    }

    @Override
    public Uttag getUttag(Integer id) {
        return this.uttagClient.getUttagBy(id);
    }

    @Override
    public boolean enableClient(boolean enabled) {
        this.isActive = enabled;
        return isActive;
    }

    @Override
    public boolean cleanServer(boolean clean) {
        this.cleanDB = clean;
        return cleanDB;
    }



}

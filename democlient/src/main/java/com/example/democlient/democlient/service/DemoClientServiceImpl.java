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
public class DemoClientServiceImpl implements ClientService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier(value = "uttagPLClientImpl")
    private UttagPLClient uttagPLClientH2;

    private boolean isActive = true;
    private boolean cleanDB = false;

    @Override
    @Scheduled(initialDelay = 1_000, fixedDelay = 2_000)
    public void processAll() {
        if(isActive) {
            List<Uttag> uttagList = uttagPLClientH2.getAllUttag(cleanDB);

            if(!uttagList.isEmpty()) {
                for (Uttag uttag : uttagList) {
                    logger.info("UttagID:{}, UUID:{}, TYPE:{}", uttag.getId(), uttag.getUuid(), uttag.getType());
                    logger.info("Number of uttag recived from PL: {}", uttagList.size());
                    logger.info("");
                }
            } else {
                logger.debug("No Uttag recieved from Server");
            }
        } else {
            logger.debug("Client not enabled.");
        }
    }

    @Override
    public Uttag getUttag(Integer id) {
        return this.uttagPLClientH2.getUttagBy(id);
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

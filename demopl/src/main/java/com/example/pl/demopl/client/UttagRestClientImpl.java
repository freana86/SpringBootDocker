package com.example.pl.demopl.client;

import com.example.pl.demopl.domain.Uttag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class UttagRestClientImpl implements UttagClient {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RestTemplate restTemplate;

    @Value("${uttag.url}")
    private String url;

    public UttagRestClientImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Uttag> getUttagList() {
        try {
            ResponseEntity<List<Uttag>> response = restTemplate.exchange(
                    this.url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Uttag>>() {
                    });

            List<Uttag> uttagList = response.getBody();

            logger.debug("Found {} Uttag to process...", uttagList.size());
            return uttagList;
        } catch (Exception e){
            throw new UttagClientException("Fel vid anrop till tjänst...", e);
        }
    }

}

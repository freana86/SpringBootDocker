package com.example.jdbc.jdbcdemo.client;


import com.example.jdbc.jdbcdemo.domain.Uttag;
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
        ResponseEntity<List<Uttag>> response = restTemplate.exchange(
                this.url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Uttag>>(){});

        List<Uttag> uttagList = response.getBody();

        logger.info("Found {} Uttag to process...", uttagList.size());
        return uttagList;
    }

}

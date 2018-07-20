package com.example.democlient.democlient.client;

import com.example.democlient.democlient.domain.Uttag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import java.util.List;

@Component
public class UttagPLClientImpl implements UttagPLClient {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RestTemplate restTemplate;

    @Value("${pl.url}")
    private String url;

    public UttagPLClientImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Uttag> getAllUttag(boolean cleanDB) {
        String URI;
        if(cleanDB) {
            URI = url + "/uttag/all?clean=true";
        } else {
            URI = url + "/uttag/all?clean=false";
        }

        ResponseEntity<List<Uttag>> response = restTemplate.exchange(
                URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Uttag>>(){});
        List<Uttag> uttagList = response.getBody();

        return uttagList;
    }

    @Override
    public Uttag getUttagBy(Integer id) {
        String URI = url + "/uttag?id=" + id;
        Uttag uttag = restTemplate.getForObject(URI , Uttag.class);
        return uttag;
    }

    @Override
    public void removeUttagBy(Integer id) {
        String URI = url + "/remove?id=" + id;
        restTemplate.delete(URI);
    }

    @Override
    public void clearDBInPL() {
        String URI = url + "/remove/all";
        restTemplate.delete(URI);
    }
}

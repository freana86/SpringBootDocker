package com.example.pl.demopl.service;

import com.example.pl.demopl.client.UttagClientException;
import com.example.pl.demopl.domain.Uttag;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

public interface UttagService {

    @Retryable(value = {UttagClientException.class}, maxAttempts = 10, backoff = @Backoff(delay = 2000))
    void processUttag();

    // For API
    List<Uttag> getAllUttag(boolean cleanDB);
    Uttag getUttagBy(Integer id);

    void removeUttag(Integer id);
    void clearDB();

    boolean sync(boolean isActive);

}

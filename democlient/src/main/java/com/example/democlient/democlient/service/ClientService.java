package com.example.democlient.democlient.service;

import com.example.democlient.democlient.domain.Uttag;

public interface ClientService {

    void processAll();

    Uttag getUttag(Integer id);

    boolean enableClient(boolean enabled);
    boolean cleanServer(boolean clean);
}

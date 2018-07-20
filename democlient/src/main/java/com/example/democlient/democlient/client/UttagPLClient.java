package com.example.democlient.democlient.client;

import com.example.democlient.democlient.domain.Uttag;

import java.util.List;

public interface UttagPLClient {

    List<Uttag> getAllUttag(boolean cleanDB);
    Uttag getUttagBy(Integer id);
    void removeUttagBy(Integer id);
    void clearDBInPL();

}

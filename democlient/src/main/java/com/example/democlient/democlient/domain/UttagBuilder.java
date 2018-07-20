package com.example.democlient.democlient.domain;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public final class UttagBuilder {
    private Integer id;
    private UUID uuid;
    private String type;

    private UttagBuilder() {
    }

    public UttagBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public UttagBuilder withUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public UttagBuilder withType(String type) {
        this.type = type;
        return this;
    }


    public static UttagBuilder empty() {
        return new UttagBuilder();
    }


    public static UttagBuilder example() {
        UttagBuilder builder = new UttagBuilder();

        builder.withId(null); // DB primary key.
        builder.withType(RandomStringUtils.randomAlphabetic(4));
        builder.withUuid(UUID.randomUUID());

        return builder;
    }


    public Uttag build() {
        Uttag uttag = new Uttag();
        uttag.setId(id);
        uttag.setUuid(uuid);
        uttag.setType(type);
        return uttag;
    }
}

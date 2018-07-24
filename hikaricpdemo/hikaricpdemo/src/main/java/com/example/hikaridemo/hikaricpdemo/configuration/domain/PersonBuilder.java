package com.example.hikaridemo.hikaricpdemo.configuration.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public final class PersonBuilder {
    private Long id;
    private String name;
    private Integer age;

    private PersonBuilder() {
    }

    public PersonBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder withAge(Integer age) {
        this.age = age;
        return this;
    }

    public static PersonBuilder aPerson() {
        return new PersonBuilder();
    }


    public static PersonBuilder example() {
        PersonBuilder builder = new PersonBuilder();

        builder.withId(RandomUtils.nextLong(1, 100));
        builder.withName(RandomStringUtils.randomAlphabetic(10));
        builder.withAge(RandomUtils.nextInt(1, 99));

        return builder;
    }


    public Person build() {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setAge(age);
        return person;
    }
}

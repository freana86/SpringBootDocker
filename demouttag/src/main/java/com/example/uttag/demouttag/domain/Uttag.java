package com.example.uttag.demouttag.domain;

import java.util.Objects;
import java.util.UUID;

public class Uttag {

    private Integer id;
    private UUID uuid;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uttag uttag = (Uttag) o;
        return Objects.equals(id, uttag.id) &&
                Objects.equals(uuid, uttag.uuid) &&
                Objects.equals(type, uttag.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uuid, type);
    }

    @Override
    public String toString() {
        return "Uttag{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", type='" + type + '\'' +
                '}';
    }
}

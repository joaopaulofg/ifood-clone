package com.joaopaulofg.ifood.domain.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;
import java.util.UUID;

public class ClientId {

    private final String value;

    @JsonCreator
    public ClientId(String value) {
        this.value = value;
    }

    public static ClientId of(String value) {
        return new ClientId(value);
    }

    public static ClientId generate() {
        return new ClientId(UUID.randomUUID().toString());
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientId clientId = (ClientId) o;
        return Objects.equals(value, clientId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
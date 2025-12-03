package com.joaopaulofg.catalog.domain.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;
import java.util.UUID;

public class RestaurantId {
    
    private final String value;

    @JsonCreator
    public RestaurantId(String value) {
        this.value = value;
    }

    public static RestaurantId of(String value) {
        return new RestaurantId(value);
    }

    public static RestaurantId generate() {
        return new RestaurantId(UUID.randomUUID().toString());
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantId RestaurantId = (RestaurantId) o;
        return Objects.equals(value, RestaurantId.value);
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

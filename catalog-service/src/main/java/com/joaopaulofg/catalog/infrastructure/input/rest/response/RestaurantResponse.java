package com.joaopaulofg.catalog.infrastructure.input.rest.response;

import com.joaopaulofg.catalog.domain.model.Restaurant;

import java.time.LocalDateTime;

public record RestaurantResponse(
        String id,
        String name,
        String description,
        LocalDateTime creationDate
) {

    public static RestaurantResponse fromDomain(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId().getValue(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getCreationDate()
        );
    }
}


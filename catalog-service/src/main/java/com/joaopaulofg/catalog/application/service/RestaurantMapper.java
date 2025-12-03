package com.joaopaulofg.catalog.application.service;

import com.joaopaulofg.catalog.domain.model.Restaurant;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.RestaurantResponse;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantResponse toResponse(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId().getValue(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getCreationDate()
        );
    }
}

package com.joaopaulofg.ifood.application.service;

import com.joaopaulofg.ifood.domain.model.Restaurant;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.RestaurantResponse;
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

package com.joaopaulofg.ifood.infrastructure.output.persistence.mapper;

import com.joaopaulofg.ifood.domain.model.Restaurant;
import com.joaopaulofg.ifood.domain.v0.RestaurantId;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.RestaurantEntity;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEntityMapper {

    public static RestaurantEntity toEntity(Restaurant restaurant) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId(restaurant.getId().getValue());
        entity.setName(restaurant.getName());
        entity.setDescription(restaurant.getDescription());
        entity.setCreationDate(restaurant.getCreationDate());
        return entity;
    }

    public Restaurant toDomain(RestaurantEntity entity) {
        return new Restaurant(
                new RestaurantId(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                entity.getCreationDate()
        );
    }
}

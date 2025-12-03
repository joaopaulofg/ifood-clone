package com.joaopaulofg.catalog.infrastructure.output.persistence.mapper;

import com.joaopaulofg.catalog.domain.model.Restaurant;
import com.joaopaulofg.catalog.domain.vo.RestaurantId;
import com.joaopaulofg.catalog.infrastructure.output.persistence.entity.RestaurantEntity;
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

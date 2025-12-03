package com.joaopaulofg.catalog.application.port.output;

import com.joaopaulofg.catalog.domain.model.Restaurant;
import com.joaopaulofg.catalog.domain.vo.RestaurantId;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);
    Optional<Restaurant> findById(RestaurantId id);
    List<Restaurant> findAll();
    void deleteById(RestaurantId id);
    boolean existsById(RestaurantId id);

}

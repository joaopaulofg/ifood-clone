package com.joaopaulofg.ifood.application.port.output;

import com.joaopaulofg.ifood.domain.model.Restaurant;
import com.joaopaulofg.ifood.domain.vo.RestaurantId;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);
    Optional<Restaurant> findById(RestaurantId id);
    List<Restaurant> findAll();
    void deleteById(RestaurantId id);
    boolean existsById(RestaurantId id);

}

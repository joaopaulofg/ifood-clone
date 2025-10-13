package com.joaopaulofg.ifood.application.port.input;

import com.joaopaulofg.ifood.domain.vo.RestaurantId;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.RestaurantResponse;

import java.util.List;

public interface RestaurantManagementUseCase {
    RestaurantResponse create(String name, String description);
    RestaurantResponse findRestaurant(RestaurantId id);
    List<RestaurantResponse> findAllRestaurants();
    RestaurantResponse updateRestaurant(RestaurantId id, String name, String description);
    void deleteRestaurant(RestaurantId id);
}

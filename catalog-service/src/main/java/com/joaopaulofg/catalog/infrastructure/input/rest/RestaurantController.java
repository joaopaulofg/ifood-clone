package com.joaopaulofg.catalog.infrastructure.input.rest;

import com.joaopaulofg.catalog.application.port.input.RestaurantManagementUseCase;
import com.joaopaulofg.catalog.domain.vo.RestaurantId;
import com.joaopaulofg.catalog.infrastructure.input.rest.request.CreateRestaurantRequest;
import com.joaopaulofg.catalog.infrastructure.input.rest.request.UpdateRestaurantRequest;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantManagementUseCase restaurantManagement;

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest request) {
        RestaurantResponse response = restaurantManagement.create(request.getName(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants() {
        List<RestaurantResponse> responses = restaurantManagement.findAllRestaurants();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurant(@PathVariable String id) {
        RestaurantResponse response = restaurantManagement.findRestaurant(RestaurantId.of(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(@PathVariable String id,
                                                               @RequestBody UpdateRestaurantRequest request) {
        RestaurantResponse response = restaurantManagement.updateRestaurant(RestaurantId.of(id), request.getName(), request.getDescription());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
        restaurantManagement.deleteRestaurant(RestaurantId.of(id));
        return ResponseEntity.noContent().build();
    }
}
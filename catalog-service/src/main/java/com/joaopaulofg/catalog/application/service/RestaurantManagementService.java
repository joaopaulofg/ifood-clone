package com.joaopaulofg.catalog.application.service;

import com.joaopaulofg.catalog.application.port.input.RestaurantManagementUseCase;
import com.joaopaulofg.catalog.application.port.output.RestaurantRepository;
import com.joaopaulofg.catalog.domain.model.Restaurant;
import com.joaopaulofg.catalog.domain.vo.RestaurantId;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.RestaurantResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RestaurantManagementService implements RestaurantManagementUseCase {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantManagementService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public RestaurantResponse create(String name, String description) {
        Restaurant restaurant = new Restaurant(RestaurantId.generate(), name, description, LocalDateTime.now());
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponse(savedRestaurant);
    }

    @Override
    public RestaurantResponse findRestaurant(RestaurantId id) {
        return restaurantRepository.findById(id)
                .map(restaurantMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id.getValue()));
    }

    @Override
    public List<RestaurantResponse> findAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponse updateRestaurant(RestaurantId id, String name, String description) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id.getValue()));

        if (name != null && !name.trim().isEmpty()) {
            restaurant.setName(name);
        }
        if (description != null && !description.trim().isEmpty()) {
            restaurant.setDescription(description);
        }

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponse(updatedRestaurant);
    }

    @Override
    public void deleteRestaurant(RestaurantId id) {
        if (!restaurantRepository.existsById(id)) {
            throw new EntityNotFoundException("Restaurant not found with id: " + id.getValue());
        }
        restaurantRepository.deleteById(id);
    }
}


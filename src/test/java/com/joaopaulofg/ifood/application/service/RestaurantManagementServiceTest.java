package com.joaopaulofg.ifood.application.service;

import com.joaopaulofg.ifood.application.port.output.RestaurantRepository;
import com.joaopaulofg.ifood.domain.model.Restaurant;
import com.joaopaulofg.ifood.domain.vo.RestaurantId;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.RestaurantResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantManagementServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RestaurantManagementService restaurantManagementService;

    @Test
    void testCreate_Success() {
        String name = "Pizzaria Delícia";
        String description = "Melhores pizzas";

        Restaurant saved = new Restaurant(RestaurantId.generate(), name, description, LocalDateTime.now());
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(saved);
        when(restaurantMapper.toResponse(saved)).thenReturn(
                new RestaurantResponse(saved.getId().getValue(), saved.getName(), saved.getDescription(), saved.getCreationDate())
        );

        RestaurantResponse response = restaurantManagementService.create(name, description);

        assertNotNull(response);
        assertEquals(name, response.name());
        assertEquals(description, response.description());
        verify(restaurantRepository).save(any(Restaurant.class));
        verify(restaurantMapper).toResponse(saved);
    }

    @Test
    void testFindRestaurant_Success() {
        RestaurantId id = RestaurantId.generate();
        Restaurant restaurant = new Restaurant(id, "Sushi Bar", "Comida japonesa", LocalDateTime.now());
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));
        when(restaurantMapper.toResponse(restaurant)).thenReturn(
                new RestaurantResponse(id.getValue(), restaurant.getName(), restaurant.getDescription(), restaurant.getCreationDate())
        );

        RestaurantResponse response = restaurantManagementService.findRestaurant(id);

        assertNotNull(response);
        assertEquals("Sushi Bar", response.name());
        verify(restaurantRepository).findById(id);
        verify(restaurantMapper).toResponse(restaurant);
    }

    @Test
    void testFindRestaurant_NotFound() {
        RestaurantId id = RestaurantId.generate();
        when(restaurantRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> restaurantManagementService.findRestaurant(id));
        assertEquals("Restaurant not found with id: " + id.getValue(), ex.getMessage());
        verify(restaurantRepository).findById(id);
    }

    @Test
    void testFindAllRestaurants_Success() {
        Restaurant r1 = new Restaurant(RestaurantId.generate(), "R1", "D1", LocalDateTime.now());
        Restaurant r2 = new Restaurant(RestaurantId.generate(), "R2", "D2", LocalDateTime.now());

        when(restaurantRepository.findAll()).thenReturn(List.of(r1, r2));
        when(restaurantMapper.toResponse(r1)).thenReturn(new RestaurantResponse(r1.getId().getValue(), r1.getName(), r1.getDescription(), r1.getCreationDate()));
        when(restaurantMapper.toResponse(r2)).thenReturn(new RestaurantResponse(r2.getId().getValue(), r2.getName(), r2.getDescription(), r2.getCreationDate()));

        List<RestaurantResponse> responses = restaurantManagementService.findAllRestaurants();

        assertEquals(2, responses.size());
        assertEquals("R1", responses.get(0).name());
        assertEquals("R2", responses.get(1).name());
        verify(restaurantRepository).findAll();
        verify(restaurantMapper).toResponse(r1);
        verify(restaurantMapper).toResponse(r2);
    }

    @Test
    void testUpdateRestaurant_Success() {
        RestaurantId id = RestaurantId.generate();
        Restaurant restaurant = new Restaurant(id, "Antigo", "Desc antiga", LocalDateTime.now());
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.save(any(Restaurant.class))).thenAnswer(inv -> inv.getArgument(0));
        when(restaurantMapper.toResponse(any(Restaurant.class))).thenAnswer(inv -> {
            Restaurant r = inv.getArgument(0);
            return new RestaurantResponse(r.getId().getValue(), r.getName(), r.getDescription(), r.getCreationDate());
        });

        RestaurantResponse response = restaurantManagementService.updateRestaurant(id, "Novo", "Desc nova");

        assertEquals("Novo", response.name());
        assertEquals("Desc nova", response.description());
        verify(restaurantRepository).save(any(Restaurant.class));
    }

    @Test
    void testDeleteRestaurant_NotExists() {
        RestaurantId id = RestaurantId.generate();
        when(restaurantRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> restaurantManagementService.deleteRestaurant(id));
        assertEquals("Restaurant not found with id: " + id.getValue(), ex.getMessage());
        verify(restaurantRepository).existsById(id);
        verify(restaurantRepository, never()).deleteById(any());
    }

    @Test
    void testDeleteRestaurant_Success() {
        RestaurantId id = RestaurantId.generate();
        when(restaurantRepository.existsById(id)).thenReturn(true);

        assertDoesNotThrow(() -> restaurantManagementService.deleteRestaurant(id));
        verify(restaurantRepository).deleteById(id);
    }
}
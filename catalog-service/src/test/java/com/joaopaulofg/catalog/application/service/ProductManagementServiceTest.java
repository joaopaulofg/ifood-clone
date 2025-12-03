package com.joaopaulofg.catalog.application.service;

import com.joaopaulofg.catalog.application.port.output.ProductRepository;
import com.joaopaulofg.catalog.application.port.output.CategoryRepository;
import com.joaopaulofg.catalog.application.port.output.RestaurantRepository;
import com.joaopaulofg.catalog.domain.exception.CategoryNotFoundException;
import com.joaopaulofg.catalog.domain.exception.RestaurantNotFoundException;
import com.joaopaulofg.catalog.domain.model.Category;
import com.joaopaulofg.catalog.domain.model.Product;
import com.joaopaulofg.catalog.domain.model.Restaurant;
import com.joaopaulofg.catalog.domain.vo.CategoryId;
import com.joaopaulofg.catalog.domain.vo.ProductId;
import com.joaopaulofg.catalog.domain.vo.RestaurantId;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.ProductResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductManagementServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductManagementService productManagementService;

    @Test
    void testCreate_Success() {
        String name = "Burger";
        String description = "Delicious burger";
        BigDecimal price = new BigDecimal("29.90");
        CategoryId categoryId = CategoryId.generate();
        RestaurantId restaurantId = RestaurantId.generate();

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(new Restaurant(restaurantId, "R", "D", LocalDateTime.now())));
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(new Category(categoryId, "C", "D", LocalDateTime.now())));

        Product saved = Product.create(ProductId.generate(), name, description, price, categoryId, restaurantId);
        when(productRepository.save(any(Product.class))).thenReturn(saved);
        when(productMapper.toResponse(saved)).thenReturn(
                ProductResponse.builder()
                        .id(saved.getId().toString())
                        .name(saved.getName())
                        .description(saved.getDescription())
                        .price(saved.getPrice())
                        .categoryId(saved.getCategoryId().toString())
                        .restaurantId(saved.getRestaurantId().toString())
                        .creationDate(saved.getCreationDate())
                        .status(saved.getStatus())
                        .build()
        );

        ProductResponse response = productManagementService.create(name, description, price, categoryId, restaurantId);

        assertNotNull(response);
        assertEquals(name, response.getName());
        assertEquals(price, response.getPrice());
        verify(productRepository).save(any(Product.class));
        verify(productMapper).toResponse(saved);
    }

    @Test
    void testCreate_RestaurantNotFound() {
        String name = "Burger";
        String description = "Delicious burger";
        BigDecimal price = new BigDecimal("29.90");
        CategoryId categoryId = CategoryId.generate();
        RestaurantId restaurantId = RestaurantId.generate();

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class,
                () -> productManagementService.create(name, description, price, categoryId, restaurantId));

        verify(restaurantRepository).findById(restaurantId);
        verify(categoryRepository, never()).findById(any());
        verify(productRepository, never()).save(any());
    }

    @Test
    void testCreate_CategoryNotFound() {
        String name = "Burger";
        String description = "Delicious burger";
        BigDecimal price = new BigDecimal("29.90");
        CategoryId categoryId = CategoryId.generate();
        RestaurantId restaurantId = RestaurantId.generate();

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(new Restaurant(restaurantId, "R", "D", LocalDateTime.now())));
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,
                () -> productManagementService.create(name, description, price, categoryId, restaurantId));

        verify(restaurantRepository).findById(restaurantId);
        verify(categoryRepository).findById(categoryId);
        verify(productRepository, never()).save(any());
    }

    @Test
    void testFindAllProducts_Success() {
        CategoryId categoryId = CategoryId.generate();
        RestaurantId restaurantId = RestaurantId.generate();
        Product p1 = Product.create(ProductId.generate(), "P1", "D1", new BigDecimal("10.00"), categoryId, restaurantId);
        Product p2 = Product.create(ProductId.generate(), "P2", "D2", new BigDecimal("20.00"), categoryId, restaurantId);

        when(productRepository.findAll()).thenReturn(List.of(p1, p2));
        when(productMapper.toResponse(p1)).thenReturn(ProductResponse.builder().id(p1.getId().toString()).name(p1.getName()).description(p1.getDescription()).price(p1.getPrice()).categoryId(p1.getCategoryId().toString()).restaurantId(p1.getRestaurantId().toString()).creationDate(p1.getCreationDate()).status(p1.getStatus()).build());
        when(productMapper.toResponse(p2)).thenReturn(ProductResponse.builder().id(p2.getId().toString()).name(p2.getName()).description(p2.getDescription()).price(p2.getPrice()).categoryId(p2.getCategoryId().toString()).restaurantId(p2.getRestaurantId().toString()).creationDate(p2.getCreationDate()).status(p2.getStatus()).build());

        List<ProductResponse> responses = productManagementService.findAllProducts();

        assertEquals(2, responses.size());
        assertEquals("P1", responses.get(0).getName());
        assertEquals("P2", responses.get(1).getName());
        verify(productRepository).findAll();
        verify(productMapper).toResponse(p1);
        verify(productMapper).toResponse(p2);
    }
}
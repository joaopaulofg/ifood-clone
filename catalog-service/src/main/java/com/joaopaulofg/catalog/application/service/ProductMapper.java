package com.joaopaulofg.catalog.application.service;

import com.joaopaulofg.catalog.domain.model.Product;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategoryId().toString())
                .restaurantId(product.getRestaurantId().toString())
                .creationDate(product.getCreationDate())
                .status(product.getStatus())
                .build();
    }
}

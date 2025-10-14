package com.joaopaulofg.ifood.application.service;

import com.joaopaulofg.ifood.domain.model.Product;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.ProductResponse;
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

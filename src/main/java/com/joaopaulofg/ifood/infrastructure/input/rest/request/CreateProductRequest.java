package com.joaopaulofg.ifood.infrastructure.input.rest.request;

import com.joaopaulofg.ifood.domain.v0.RestaurantId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private UUID categoryId;
    private RestaurantId restaurantId;
}

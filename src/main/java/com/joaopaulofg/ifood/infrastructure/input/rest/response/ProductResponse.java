package com.joaopaulofg.ifood.infrastructure.input.rest.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.joaopaulofg.ifood.domain.vo.ProductStatus;
import com.joaopaulofg.ifood.domain.vo.RestaurantId;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private UUID categoryId;

    private RestaurantId restaurantId;

    private LocalDateTime creationDate;

    private ProductStatus status;
}

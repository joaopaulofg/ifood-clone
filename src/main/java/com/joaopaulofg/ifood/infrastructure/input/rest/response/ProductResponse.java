package com.joaopaulofg.ifood.infrastructure.input.rest.response;

import com.joaopaulofg.ifood.domain.v0.ProductStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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

    private UUID restaurantId;

    private LocalDateTime creationDate;

    private ProductStatus status;
}

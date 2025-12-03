package com.joaopaulofg.ifood.infrastructure.output.client.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CatalogProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price,
        String categoryId,
        String restaurantId,
        LocalDateTime creationDate,
        String status
) {
}

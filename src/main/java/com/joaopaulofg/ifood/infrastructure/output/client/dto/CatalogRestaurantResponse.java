package com.joaopaulofg.ifood.infrastructure.output.client.dto;

import java.time.LocalDateTime;

public record CatalogRestaurantResponse(
        String id,
        String name,
        String description,
        LocalDateTime creationDate
) {
}

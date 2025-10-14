package com.joaopaulofg.ifood.infrastructure.input.rest.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.joaopaulofg.ifood.domain.vo.ProductStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private String categoryId;

    private String restaurantId;

    private LocalDateTime creationDate;

    private ProductStatus status;
}

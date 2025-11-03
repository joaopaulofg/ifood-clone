package com.joaopaulofg.ifood.infrastructure.input.rest.response;

import com.joaopaulofg.ifood.domain.vo.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;
    private String clientId;
    private String restaurantId;
    private OrderStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private BigDecimal totalPrice;
}
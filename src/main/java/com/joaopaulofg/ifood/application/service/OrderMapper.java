package com.joaopaulofg.ifood.application.service;

import com.joaopaulofg.ifood.domain.model.Order;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId().toString())
                .clientId(order.getClientId().toString())
                .restaurantId(order.getRestaurantId().toString())
                .status(order.getStatus())
                .creationDate(order.getCreationDate())
                .updateDate(order.getUpdateDate())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
package com.joaopaulofg.ifood.infrastructure.input.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private String clientId;
    private String restaurantId;
    private List<OrderItemRequest> items;
}
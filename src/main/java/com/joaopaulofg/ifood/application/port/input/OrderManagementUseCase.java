package com.joaopaulofg.ifood.application.port.input;

import java.util.List;

import com.joaopaulofg.ifood.domain.vo.ClientId;
import com.joaopaulofg.ifood.domain.vo.OrderId;
import com.joaopaulofg.ifood.domain.vo.OrderStatus;
import com.joaopaulofg.ifood.domain.vo.RestaurantId;
import com.joaopaulofg.ifood.infrastructure.input.rest.request.OrderItemSpec;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.OrderResponse;

public interface OrderManagementUseCase {
    OrderResponse create(ClientId clientId, RestaurantId restaurantId, List<OrderItemSpec> items);
    OrderResponse findOrder(OrderId id);
    List<OrderResponse> findAllOrders();
    OrderResponse updateStatus(OrderId id, OrderStatus status);
    void deleteOrder(OrderId id);
}
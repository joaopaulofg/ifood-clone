package com.joaopaulofg.ifood.application.port.output;

import com.joaopaulofg.ifood.domain.model.OrderItem;
import com.joaopaulofg.ifood.domain.vo.OrderId;
import com.joaopaulofg.ifood.domain.vo.OrderItemId;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository {
    Optional<OrderItem> findById(OrderItemId id);
    List<OrderItem> findByOrderId(OrderId orderId);
    List<OrderItem> findAll();
    OrderItem save(OrderItem item);
    void deleteById(OrderItemId id);
    boolean existsById(OrderItemId id);
}
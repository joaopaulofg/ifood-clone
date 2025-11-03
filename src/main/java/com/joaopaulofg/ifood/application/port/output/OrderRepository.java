package com.joaopaulofg.ifood.application.port.output;

import com.joaopaulofg.ifood.domain.model.Order;
import com.joaopaulofg.ifood.domain.vo.OrderId;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(OrderId id);
    List<Order> findAll();
    Order save(Order order);
    void deleteById(OrderId id);
    boolean existsById(OrderId id);
}
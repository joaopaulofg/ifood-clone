package com.joaopaulofg.ifood.infrastructure.output.persistence.adapter;

import com.joaopaulofg.ifood.application.port.output.OrderRepository;
import com.joaopaulofg.ifood.domain.model.Order;
import com.joaopaulofg.ifood.domain.vo.OrderId;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.OrderEntity;
import com.joaopaulofg.ifood.infrastructure.output.persistence.mapper.OrderEntityMapper;
import com.joaopaulofg.ifood.infrastructure.output.persistence.repository.SpringDataOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class JpaOrderRepositoryAdapter implements OrderRepository {

    private final SpringDataOrderRepository orderRepository;
    private final OrderEntityMapper mapper = new OrderEntityMapper();

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id.getValue()).map(mapper::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Order save(Order order) {
        OrderEntity saved = orderRepository.save(mapper.toEntity(order));
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(OrderId id) {
        orderRepository.deleteById(id.getValue());
    }

    @Override
    public boolean existsById(OrderId id) {
        return orderRepository.existsById(id.getValue());
    }
}
package com.joaopaulofg.ifood.infrastructure.output.persistence.adapter;

import com.joaopaulofg.ifood.application.port.output.OrderItemRepository;
import com.joaopaulofg.ifood.domain.model.OrderItem;
import com.joaopaulofg.ifood.domain.vo.OrderId;
import com.joaopaulofg.ifood.domain.vo.OrderItemId;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.OrderItemEntity;
import com.joaopaulofg.ifood.infrastructure.output.persistence.mapper.OrderItemEntityMapper;
import com.joaopaulofg.ifood.infrastructure.output.persistence.repository.SpringDataOrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JpaOrderItemRepositoryAdapter implements OrderItemRepository {

    private final SpringDataOrderItemRepository orderItemRepository;
    private final OrderItemEntityMapper mapper = new OrderItemEntityMapper();

    @Override
    public Optional<OrderItem> findById(OrderItemId id) {
        return orderItemRepository.findById(id.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public List<OrderItem> findByOrderId(OrderId orderId) {
        return orderItemRepository.findByOrder_Id(orderId.getValue()).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItem> findAll() {
        return ((List<OrderItemEntity>) orderItemRepository.findAll()).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItem save(OrderItem item) {
        var saved = orderItemRepository.save(mapper.toEntity(item));
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(OrderItemId id) {
        orderItemRepository.deleteById(id.getValue());
    }

    @Override
    public boolean existsById(OrderItemId id) {
        return orderItemRepository.existsById(id.getValue());
    }
}
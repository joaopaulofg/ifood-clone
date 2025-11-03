package com.joaopaulofg.ifood.infrastructure.output.persistence.repository;

import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataOrderItemRepository extends CrudRepository<OrderItemEntity, String> {
    List<OrderItemEntity> findByOrder_Id(String orderId);
}
package com.joaopaulofg.ifood.infrastructure.output.persistence.mapper;

import com.joaopaulofg.ifood.domain.model.Order;
import com.joaopaulofg.ifood.domain.vo.ClientId;
import com.joaopaulofg.ifood.domain.vo.OrderId;
import com.joaopaulofg.ifood.domain.vo.RestaurantId;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.ClientEntity;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.OrderEntity;

public class OrderEntityMapper {

    public Order toDomain(OrderEntity entity) {
        Order order = new Order();
        order.setId(OrderId.of(entity.getId()));
        order.setClientId(ClientId.of(entity.getClient().getId()));
        order.setRestaurantId(RestaurantId.of(entity.getRestaurantId()));
        order.setStatus(entity.getStatus());
        order.setCreationDate(entity.getCreationDate());
        order.setUpdateDate(entity.getUpdateDate());
        order.setTotalPrice(entity.getTotalPrice());
        return order;
    }

    public OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId().getValue());

        ClientEntity client = new ClientEntity();
        client.setId(order.getClientId().getValue());
        entity.setClient(client);


        entity.setRestaurantId(order.getRestaurantId().getValue());

        entity.setStatus(order.getStatus());
        entity.setCreationDate(order.getCreationDate());
        entity.setUpdateDate(order.getUpdateDate());
        entity.setTotalPrice(order.getTotalPrice());
        return entity;
    }
}
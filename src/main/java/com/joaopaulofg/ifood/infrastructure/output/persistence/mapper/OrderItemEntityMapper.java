package com.joaopaulofg.ifood.infrastructure.output.persistence.mapper;

import com.joaopaulofg.ifood.domain.model.OrderItem;
import com.joaopaulofg.ifood.domain.vo.OrderId;
import com.joaopaulofg.ifood.domain.vo.OrderItemId;
import com.joaopaulofg.ifood.domain.vo.ProductId;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.OrderEntity;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.OrderItemEntity;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.ProductEntity;

public class OrderItemEntityMapper {

    public OrderItem toDomain(OrderItemEntity entity) {
        OrderItem item = new OrderItem();
        item.setId(OrderItemId.of(entity.getId()));
        item.setOrderId(OrderId.of(entity.getOrder().getId()));
        item.setProductId(ProductId.of(entity.getProduct().getId()));
        item.setQuantity(entity.getQuantity());
        item.setUnitPrice(entity.getUnitPrice());
        return item;
    }

    public OrderItemEntity toEntity(OrderItem item) {
        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(item.getId().getValue());

        OrderEntity order = new OrderEntity();
        order.setId(item.getOrderId().getValue());
        entity.setOrder(order);

        ProductEntity product = new ProductEntity();
        product.setId(item.getProductId().getValue());
        entity.setProduct(product);

        entity.setQuantity(item.getQuantity());
        entity.setUnitPrice(item.getUnitPrice());
        return entity;
    }
}
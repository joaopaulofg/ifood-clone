package com.joaopaulofg.ifood.domain.model;

import java.math.BigDecimal;

import com.joaopaulofg.ifood.domain.vo.OrderId;
import com.joaopaulofg.ifood.domain.vo.OrderItemId;
import com.joaopaulofg.ifood.domain.vo.ProductId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    private OrderItemId id;

    private OrderId orderId;

    private ProductId productId;

    private Integer quantity;

    private BigDecimal unitPrice;
}
package com.joaopaulofg.ifood.infrastructure.input.rest.request;

import com.joaopaulofg.ifood.domain.vo.ProductId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemSpec {
    private ProductId productId;
    private Integer quantity;
}
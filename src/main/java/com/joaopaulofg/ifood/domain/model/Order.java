package com.joaopaulofg.ifood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.joaopaulofg.ifood.domain.vo.ClientId;
import com.joaopaulofg.ifood.domain.vo.OrderId;
import com.joaopaulofg.ifood.domain.vo.OrderStatus;
import com.joaopaulofg.ifood.domain.vo.RestaurantId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order {

    private OrderId id;

    private ClientId clientId;

    private RestaurantId restaurantId;

    private OrderStatus status;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

    private BigDecimal totalPrice;
    
}

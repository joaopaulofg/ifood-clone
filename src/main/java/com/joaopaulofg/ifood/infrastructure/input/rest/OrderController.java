package com.joaopaulofg.ifood.infrastructure.input.rest;

import com.joaopaulofg.ifood.application.port.input.OrderManagementUseCase;
import com.joaopaulofg.ifood.domain.vo.ClientId;
import com.joaopaulofg.ifood.domain.vo.OrderId;
import com.joaopaulofg.ifood.domain.vo.ProductId;
import com.joaopaulofg.ifood.domain.vo.RestaurantId;
import com.joaopaulofg.ifood.infrastructure.input.rest.request.CreateOrderRequest;
import com.joaopaulofg.ifood.infrastructure.input.rest.request.OrderItemSpec;
import com.joaopaulofg.ifood.infrastructure.input.rest.request.UpdateOrderStatusRequest;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderManagementUseCase orderManagement;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        List<OrderItemSpec> items = request.getItems() == null ? List.of() :
                request.getItems().stream()
                        .map(i -> new OrderItemSpec(ProductId.of(i.getId()), i.getQuantity()))
                        .collect(Collectors.toList());

        OrderResponse response = orderManagement.create(
                ClientId.of(request.getClientId()),
                RestaurantId.of(request.getRestaurantId()),
                items
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> responses = orderManagement.findAllOrders();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String id) {
        OrderResponse response = orderManagement.findOrder(OrderId.of(id));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable String id,
                                                           @RequestBody UpdateOrderStatusRequest request) {
        OrderResponse response = orderManagement.updateStatus(OrderId.of(id), request.getStatus());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderManagement.deleteOrder(OrderId.of(id));
        return ResponseEntity.noContent().build();
    }
}
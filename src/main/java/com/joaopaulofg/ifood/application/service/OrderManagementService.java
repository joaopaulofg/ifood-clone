package com.joaopaulofg.ifood.application.service;

import com.joaopaulofg.ifood.application.port.input.OrderManagementUseCase;
import com.joaopaulofg.ifood.application.port.output.OrderItemRepository;
import com.joaopaulofg.ifood.application.port.output.OrderRepository;
import com.joaopaulofg.ifood.domain.model.Order;
import com.joaopaulofg.ifood.domain.model.OrderItem;
import com.joaopaulofg.ifood.domain.vo.ClientId;
import com.joaopaulofg.ifood.domain.vo.OrderId;
import com.joaopaulofg.ifood.domain.vo.OrderItemId;
import com.joaopaulofg.ifood.domain.vo.OrderStatus;
import com.joaopaulofg.ifood.domain.vo.ProductId;
import com.joaopaulofg.ifood.domain.vo.RestaurantId;
import com.joaopaulofg.ifood.infrastructure.input.rest.request.OrderItemSpec;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.OrderResponse;
import com.joaopaulofg.ifood.infrastructure.output.client.CatalogClient;
import com.joaopaulofg.ifood.infrastructure.output.client.dto.CatalogProductResponse;
import com.joaopaulofg.ifood.infrastructure.output.client.dto.CatalogRestaurantResponse;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderManagementService implements OrderManagementUseCase {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CatalogClient catalogClient;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse create(ClientId clientId, RestaurantId restaurantId, List<OrderItemSpec> items) {
        log.info("Creating order for client: {}, restaurant: {}", clientId.getValue(), restaurantId.getValue());
        
        OrderId orderId = OrderId.generate();
        log.info("Generated OrderId: {}", orderId);
        
        Order order = new Order();
        order.setId(orderId);
        order.setClientId(clientId);
        order.setRestaurantId(restaurantId);
        order.setStatus(OrderStatus.CREATED);
        LocalDateTime now = LocalDateTime.now();
        order.setCreationDate(now);
        order.setUpdateDate(now);
        order.setTotalPrice(BigDecimal.ZERO);

        log.info("Order created, saving to repository...");
        Order savedOrder = orderRepository.save(order);
        log.info("Order saved with ID: {}", savedOrder.getId());

        fetchRestaurant(restaurantId);

        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemSpec spec : items) {
            ProductId productId = spec.getProductId();
            log.info("Processing item for product ID: {}", productId.getValue());
            CatalogProductResponse product = fetchProduct(productId);

            if (!restaurantId.getValue().equals(product.restaurantId())) {
                throw new IllegalArgumentException("Product " + productId.getValue() + " does not belong to restaurant " + restaurantId.getValue());
            }

            BigDecimal unitPrice = product.price();
            BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(spec.getQuantity()));
            total = total.add(lineTotal);

            OrderItem item = new OrderItem();
            item.setId(OrderItemId.generate());
            item.setOrderId(orderId);
            item.setProductId(productId);
            item.setQuantity(spec.getQuantity());
            item.setUnitPrice(unitPrice);
            orderItemRepository.save(item);
        }

        savedOrder.setTotalPrice(total);
        savedOrder.setUpdateDate(LocalDateTime.now());
        Order updatedOrder = orderRepository.save(savedOrder);
        return orderMapper.toResponse(updatedOrder);
    }

    @Override
    public OrderResponse findOrder(OrderId id) {
        return orderRepository.findById(id)
                .map(orderMapper::toResponse)
                .orElse(null);
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse updateStatus(OrderId id, OrderStatus status) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id.getValue()));
        existing.setStatus(status);
        existing.setUpdateDate(LocalDateTime.now());
        Order saved = orderRepository.save(existing);
        return orderMapper.toResponse(saved);
    }

    @Override
    public void deleteOrder(OrderId id) {
        orderRepository.deleteById(id);
    }

    private CatalogProductResponse fetchProduct(ProductId productId) {
        try {
            return catalogClient.getProduct(productId.getValue());
        } catch (FeignException.NotFound ex) {
            throw new IllegalArgumentException("Product not found: " + productId.getValue());
        } catch (FeignException ex) {
            throw new IllegalStateException("Failed to fetch product " + productId.getValue() + " from catalog service", ex);
        }
    }

    private CatalogRestaurantResponse fetchRestaurant(RestaurantId restaurantId) {
        try {
            return catalogClient.getRestaurant(restaurantId.getValue());
        } catch (FeignException.NotFound ex) {
            throw new IllegalArgumentException("Restaurant not found: " + restaurantId.getValue());
        } catch (FeignException ex) {
            throw new IllegalStateException("Failed to fetch restaurant " + restaurantId.getValue() + " from catalog service", ex);
        }
    }
}

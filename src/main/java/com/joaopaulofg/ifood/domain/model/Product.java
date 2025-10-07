package com.joaopaulofg.ifood.domain.model;

import com.joaopaulofg.ifood.domain.exception.InvalidProductException;
import com.joaopaulofg.ifood.domain.v0.ProductId;
import com.joaopaulofg.ifood.domain.v0.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Product {

    private ProductId id;

    private String name;

    private String description;

    private BigDecimal price;

    private UUID categoryId;

    private UUID restaurantId;

    private LocalDateTime creationDate;

    private ProductStatus status;

    private Product(ProductId id, String name, String description, BigDecimal price, UUID categoryId, UUID restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.restaurantId = restaurantId;
        this.creationDate = LocalDateTime.now();
        this.status = price.compareTo(BigDecimal.ZERO) < 0 ? ProductStatus.INACTIVE : ProductStatus.ACTIVE;
    }

    public static Product create(ProductId id, String name, String description, BigDecimal price, UUID categoryId, UUID restaurantId) {
        if(name == null || name.trim().isEmpty()) {
            throw new InvalidProductException("Product name cannot be empty!");
        }
        if(description == null || description.trim().isEmpty()) {
            throw new InvalidProductException("Product description cannot be empty!");
        }
        if(price == null) {
            throw new InvalidProductException("Product price cannot be empty!");
        }
        if(categoryId == null) {
            throw new InvalidProductException("Product category ID cannot be empty!");
        }
        if(restaurantId == null) {
            throw new InvalidProductException("Product restaurant ID cannot be empty!");
        }
        return new Product(id, name, description, price, categoryId, restaurantId);
    }

    public void update(String name, String description, BigDecimal price) {
        if(name == null || name.trim().isEmpty()) {
            this.name = name;
        }
        if(description == null || description.trim().isEmpty()) {
            this.description = description;
        }
        if(price != null &&  price.compareTo(BigDecimal.ZERO) < 0) {
            this.price = price;
        }
    }

    public void activate() {
        if(this.price.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidProductException("Product price cannot be negative!");
        }
        this.status = ProductStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = ProductStatus.INACTIVE;
    }
}

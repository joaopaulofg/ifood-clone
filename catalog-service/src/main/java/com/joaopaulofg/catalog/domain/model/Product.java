package com.joaopaulofg.catalog.domain.model;

import com.joaopaulofg.catalog.domain.exception.InvalidProductException;
import com.joaopaulofg.catalog.domain.vo.ProductId;
import com.joaopaulofg.catalog.domain.vo.ProductStatus;
import com.joaopaulofg.catalog.domain.vo.RestaurantId;
import com.joaopaulofg.catalog.domain.vo.CategoryId;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Product {

    private ProductId id;

    private String name;

    private String description;

    private BigDecimal price;

    private CategoryId categoryId;

    private RestaurantId restaurantId;

    private LocalDateTime creationDate;

    private ProductStatus status;

    private Product(ProductId id, String name, String description, BigDecimal price, CategoryId categoryId, RestaurantId restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.restaurantId = restaurantId;
        this.creationDate = LocalDateTime.now();
        this.status = price.compareTo(BigDecimal.ZERO) < 0
                ? ProductStatus.INACTIVE
                : ProductStatus.ACTIVE;
    }

    // Factory method used to rehydrate a Product from persistence layer
    public static Product restore(ProductId id, String name, String description, BigDecimal price,
                                  CategoryId categoryId, RestaurantId restaurantId,
                                  LocalDateTime creationDate, ProductStatus status) {
        Product product = new Product();
        product.id = id;
        product.name = name;
        product.description = description;
        product.price = price;
        product.categoryId = categoryId;
        product.restaurantId = restaurantId;
        product.creationDate = creationDate;
        product.status = status;
        return product;
    }

    public static Product create(ProductId id, String name, String description, BigDecimal price, CategoryId categoryId, RestaurantId restaurantId) {
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
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        if (description != null && !description.trim().isEmpty()) {
            this.description = description;
        }
        if (price != null && price.compareTo(BigDecimal.ZERO) >= 0) {
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

package com.joaopaulofg.ifood.infrastructure.output.persistence.mapper;

import com.joaopaulofg.ifood.domain.model.Product;
import com.joaopaulofg.ifood.domain.v0.ProductId;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapper {

    public Product toDomain(ProductEntity entity) {
        return Product.create(new ProductId(entity.getId()), entity.getName(), entity.getDescription(),
                entity.getPrice(), entity.getCategoryId(), entity.getRestaurantId());
    }

    public ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.getId().getValue(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategoryId(),
                product.getRestaurantId(),
                product.getCreationDate(),
                product.getStatus()
        );
    }
}

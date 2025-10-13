package com.joaopaulofg.ifood.infrastructure.output.persistence.mapper;

import com.joaopaulofg.ifood.domain.model.Product;
import com.joaopaulofg.ifood.domain.vo.ProductId;
import com.joaopaulofg.ifood.domain.vo.RestaurantId;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.ProductEntity;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.RestaurantEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapper {

    public Product toDomain(ProductEntity entity) {
        return Product.restore(
                new ProductId(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getCategoryId(),
                new RestaurantId(entity.getRestaurant().getId()),
                entity.getCreationDate(),
                entity.getStatus()
        );
    }

    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId().getValue());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setCategoryId(product.getCategoryId());
        entity.setCreationDate(product.getCreationDate());
        entity.setStatus(product.getStatus());

        RestaurantEntity restaurantRef = new RestaurantEntity();
        restaurantRef.setId(product.getRestaurantId().getValue());
        entity.setRestaurant(restaurantRef);

        return entity;
    }
}

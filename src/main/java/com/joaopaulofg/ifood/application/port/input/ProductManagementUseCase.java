package com.joaopaulofg.ifood.application.port.input;

import com.joaopaulofg.ifood.domain.vo.ProductId;
import com.joaopaulofg.ifood.domain.vo.RestaurantId;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.ProductResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductManagementUseCase {

    ProductResponse create(String name, String description, BigDecimal price, UUID categoryId, RestaurantId restaurantId);
    ProductResponse findProduct(ProductId id);
    List<ProductResponse> findAllProducts();
    ProductResponse updateProduct(ProductId id, String name, String description, BigDecimal price);
    void deleteProduct(ProductId id);
    ProductResponse activateProduct(ProductId id);
    ProductResponse deactivateProduct(ProductId id);

}

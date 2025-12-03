package com.joaopaulofg.catalog.application.port.input;

import com.joaopaulofg.catalog.domain.vo.ProductId;
import com.joaopaulofg.catalog.domain.vo.RestaurantId;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.ProductResponse;

import java.math.BigDecimal;
import java.util.List;
import com.joaopaulofg.catalog.domain.vo.CategoryId;

public interface ProductManagementUseCase {

    ProductResponse create(String name, String description, BigDecimal price, CategoryId categoryId, RestaurantId restaurantId);
    ProductResponse findProduct(ProductId id);
    List<ProductResponse> findAllProducts();
    ProductResponse updateProduct(ProductId id, String name, String description, BigDecimal price);
    void deleteProduct(ProductId id);
    ProductResponse activateProduct(ProductId id);
    ProductResponse deactivateProduct(ProductId id);

}

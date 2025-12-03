package com.joaopaulofg.catalog.application.port.output;

import com.joaopaulofg.catalog.domain.model.Product;
import com.joaopaulofg.catalog.domain.vo.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(ProductId id);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(ProductId id);
}

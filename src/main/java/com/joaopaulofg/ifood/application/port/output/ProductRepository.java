package com.joaopaulofg.ifood.application.port.output;

import com.joaopaulofg.ifood.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Optional<Product> findById(UUID id);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(UUID id);
}

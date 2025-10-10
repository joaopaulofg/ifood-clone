package com.joaopaulofg.ifood.application.service;

import com.joaopaulofg.ifood.application.port.input.ProductManagementUseCase;
import com.joaopaulofg.ifood.application.port.output.ProductRepository;
import com.joaopaulofg.ifood.application.port.output.RestaurantRepository;
import com.joaopaulofg.ifood.domain.exception.RestaurantNotFoundException;
import com.joaopaulofg.ifood.domain.model.Product;
import com.joaopaulofg.ifood.domain.v0.ProductId;
import com.joaopaulofg.ifood.domain.v0.RestaurantId;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.ProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductManagementService implements ProductManagementUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private final RestaurantRepository restaurantRepository;

    @Override
    public ProductResponse create(String name, String description, BigDecimal price, UUID categoryId, RestaurantId restaurantId) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId.getValue()));

        Product product = Product.create(ProductId.generate(), name, description, price, categoryId, restaurantId);

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse findProduct(ProductId id) {
        return null;
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(ProductId id, String name, String description, BigDecimal price) {
        return null;
    }

    @Override
    public void deleteProduct(ProductId id) {

    }

    @Override
    public ProductResponse activateProduct(ProductId id) {
        return null;
    }

    @Override
    public ProductResponse deactivateProduct(ProductId id) {
        return null;
    }
}

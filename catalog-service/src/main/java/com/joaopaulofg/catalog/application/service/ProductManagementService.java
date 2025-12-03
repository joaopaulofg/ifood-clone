package com.joaopaulofg.catalog.application.service;

import com.joaopaulofg.catalog.application.port.input.ProductManagementUseCase;
import com.joaopaulofg.catalog.application.port.output.ProductRepository;
import com.joaopaulofg.catalog.application.port.output.CategoryRepository;
import com.joaopaulofg.catalog.application.port.output.RestaurantRepository;
import com.joaopaulofg.catalog.domain.exception.CategoryNotFoundException;
import com.joaopaulofg.catalog.domain.exception.RestaurantNotFoundException;
import com.joaopaulofg.catalog.domain.model.Product;
import com.joaopaulofg.catalog.domain.vo.CategoryId;
import com.joaopaulofg.catalog.domain.vo.ProductId;
import com.joaopaulofg.catalog.domain.vo.RestaurantId;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.ProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductManagementService implements ProductManagementUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse create(String name, String description, BigDecimal price, CategoryId categoryId, RestaurantId restaurantId) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId.getValue()));

        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId.getValue()));

        Product product = Product.create(ProductId.generate(), name, description, price, categoryId, restaurantId);

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse findProduct(ProductId id) {
        return productRepository.findById(id).map(productMapper::toResponse).orElse(null);
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

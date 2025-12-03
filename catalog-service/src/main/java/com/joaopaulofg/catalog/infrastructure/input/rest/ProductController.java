package com.joaopaulofg.catalog.infrastructure.input.rest;

import com.joaopaulofg.catalog.application.port.input.ProductManagementUseCase;
import com.joaopaulofg.catalog.domain.vo.CategoryId;
import com.joaopaulofg.catalog.domain.vo.RestaurantId;
import com.joaopaulofg.catalog.infrastructure.input.rest.request.CreateProductRequest;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductManagementUseCase productManagement;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request){
        ProductResponse response = productManagement.create(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                CategoryId.of(request.getCategoryId()),
                RestaurantId.of(request.getRestaurantId())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> responses = productManagement.findAllProducts();
        return ResponseEntity.ok(responses);
    }
}

package com.joaopaulofg.catalog.infrastructure.input.rest;

import com.joaopaulofg.catalog.application.port.input.CategoryManagementUseCase;
import com.joaopaulofg.catalog.domain.vo.CategoryId;
import com.joaopaulofg.catalog.infrastructure.input.rest.request.CreateCategoryRequest;
import com.joaopaulofg.catalog.infrastructure.input.rest.request.UpdateCategoryRequest;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryManagementUseCase categoryManagement;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CreateCategoryRequest request) {
        CategoryResponse response = categoryManagement.create(request.getName(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> responses = categoryManagement.findAllCategories();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable String id) {
        CategoryResponse response = categoryManagement.findCategory(CategoryId.of(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String id,
                                                           @RequestBody UpdateCategoryRequest request) {
        CategoryResponse response = categoryManagement.updateCategory(CategoryId.of(id), request.getName(), request.getDescription());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryManagement.deleteCategory(CategoryId.of(id));
        return ResponseEntity.noContent().build();
    }
}
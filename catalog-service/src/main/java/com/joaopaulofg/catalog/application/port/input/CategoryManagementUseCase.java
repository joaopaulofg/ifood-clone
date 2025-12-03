package com.joaopaulofg.catalog.application.port.input;

import com.joaopaulofg.catalog.domain.vo.CategoryId;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.CategoryResponse;

import java.util.List;

public interface CategoryManagementUseCase {
    CategoryResponse create(String name, String description);
    CategoryResponse findCategory(CategoryId id);
    List<CategoryResponse> findAllCategories();
    CategoryResponse updateCategory(CategoryId id, String name, String description);
    void deleteCategory(CategoryId id);
}
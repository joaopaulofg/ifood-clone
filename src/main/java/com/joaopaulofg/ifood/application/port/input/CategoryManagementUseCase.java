package com.joaopaulofg.ifood.application.port.input;

import com.joaopaulofg.ifood.domain.vo.CategoryId;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.CategoryResponse;

import java.util.List;

public interface CategoryManagementUseCase {
    CategoryResponse create(String name, String description);
    CategoryResponse findCategory(CategoryId id);
    List<CategoryResponse> findAllCategories();
    CategoryResponse updateCategory(CategoryId id, String name, String description);
    void deleteCategory(CategoryId id);
}
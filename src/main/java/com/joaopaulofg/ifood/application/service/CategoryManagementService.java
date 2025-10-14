package com.joaopaulofg.ifood.application.service;

import com.joaopaulofg.ifood.application.port.input.CategoryManagementUseCase;
import com.joaopaulofg.ifood.application.port.output.CategoryRepository;
import com.joaopaulofg.ifood.domain.model.Category;
import com.joaopaulofg.ifood.domain.vo.CategoryId;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.CategoryResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryManagementService implements CategoryManagementUseCase {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryManagementService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponse create(String name, String description) {
        Category category = new Category(CategoryId.generate(), name, description, LocalDateTime.now());
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    @Override
    public CategoryResponse findCategory(CategoryId id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id.getValue()));
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse updateCategory(CategoryId id, String name, String description) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id.getValue()));

        if (name != null && !name.trim().isEmpty()) {
            category.setName(name);
        }
        if (description != null && !description.trim().isEmpty()) {
            category.setDescription(description);
        }

        Category updated = categoryRepository.save(category);
        return categoryMapper.toResponse(updated);
    }

    @Override
    public void deleteCategory(CategoryId id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found with id: " + id.getValue());
        }
        categoryRepository.deleteById(id);
    }
}
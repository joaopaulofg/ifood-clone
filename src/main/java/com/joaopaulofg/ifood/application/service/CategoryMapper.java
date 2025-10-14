package com.joaopaulofg.ifood.application.service;

import com.joaopaulofg.ifood.domain.model.Category;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId().toString())
                .name(category.getName())
                .description(category.getDescription())
                .creationDate(category.getCreationDate())
                .build();
    }
}
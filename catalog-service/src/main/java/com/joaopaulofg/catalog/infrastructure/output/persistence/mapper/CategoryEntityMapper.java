package com.joaopaulofg.catalog.infrastructure.output.persistence.mapper;

import com.joaopaulofg.catalog.domain.model.Category;
import com.joaopaulofg.catalog.domain.vo.CategoryId;
import com.joaopaulofg.catalog.infrastructure.output.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryEntityMapper {

    public Category toDomain(CategoryEntity entity) {
        return new Category(
                new CategoryId(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                entity.getCreationDate()
        );
    }

    public static CategoryEntity toEntity(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId().getValue());
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());
        entity.setCreationDate(category.getCreationDate());
        return entity;
    }
}
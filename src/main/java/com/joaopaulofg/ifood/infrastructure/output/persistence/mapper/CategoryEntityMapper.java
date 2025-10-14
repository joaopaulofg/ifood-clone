package com.joaopaulofg.ifood.infrastructure.output.persistence.mapper;

import com.joaopaulofg.ifood.domain.model.Category;
import com.joaopaulofg.ifood.domain.vo.CategoryId;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.CategoryEntity;
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
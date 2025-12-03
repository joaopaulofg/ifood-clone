package com.joaopaulofg.catalog.infrastructure.output.persistence.repository;

import com.joaopaulofg.catalog.infrastructure.output.persistence.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataCategoryRepository extends CrudRepository<CategoryEntity, String> {
}
package com.joaopaulofg.ifood.application.port.output;

import com.joaopaulofg.ifood.domain.model.Category;
import com.joaopaulofg.ifood.domain.vo.CategoryId;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(CategoryId id);
    List<Category> findAll();
    boolean existsById(CategoryId id);
    void deleteById(CategoryId id);
}
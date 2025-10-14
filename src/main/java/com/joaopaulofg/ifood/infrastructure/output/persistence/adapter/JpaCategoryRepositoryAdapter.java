package com.joaopaulofg.ifood.infrastructure.output.persistence.adapter;

import com.joaopaulofg.ifood.application.port.output.CategoryRepository;
import com.joaopaulofg.ifood.domain.model.Category;
import com.joaopaulofg.ifood.domain.vo.CategoryId;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.CategoryEntity;
import com.joaopaulofg.ifood.infrastructure.output.persistence.mapper.CategoryEntityMapper;
import com.joaopaulofg.ifood.infrastructure.output.persistence.repository.SpringDataCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class JpaCategoryRepositoryAdapter implements CategoryRepository {

    private final SpringDataCategoryRepository repository;
    private final CategoryEntityMapper mapper;

    public JpaCategoryRepositoryAdapter(SpringDataCategoryRepository repository, CategoryEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = CategoryEntityMapper.toEntity(category);
        CategoryEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Category> findById(CategoryId id) {
        return repository.findById(id.getValue()).map(mapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(CategoryId id) {
        return repository.existsById(id.getValue());
    }

    @Override
    public void deleteById(CategoryId id) {
        repository.deleteById(id.getValue());
    }
}
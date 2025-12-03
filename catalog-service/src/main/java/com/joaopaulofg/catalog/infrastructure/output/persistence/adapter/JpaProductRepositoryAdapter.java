package com.joaopaulofg.catalog.infrastructure.output.persistence.adapter;

import com.joaopaulofg.catalog.application.port.output.ProductRepository;
import com.joaopaulofg.catalog.domain.model.Product;
import com.joaopaulofg.catalog.domain.vo.ProductId;
import com.joaopaulofg.catalog.infrastructure.output.persistence.mapper.ProductEntityMapper;
import com.joaopaulofg.catalog.infrastructure.output.persistence.repository.SpringDataProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class JpaProductRepositoryAdapter implements ProductRepository {

    private final SpringDataProductRepository repository;
    private final ProductEntityMapper mapper;

    @Override
    public Optional<Product> findById(ProductId id) {
        return repository.findById(id.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        var entity = ProductEntityMapper.toEntity(product);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(ProductId id) {
        repository.deleteById(id.getValue());
    }
}

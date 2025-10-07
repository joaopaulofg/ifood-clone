package com.joaopaulofg.ifood.infrastructure.output.persistence.adapter;

import com.joaopaulofg.ifood.application.port.output.ProductRepository;
import com.joaopaulofg.ifood.application.service.ProductMapper;
import com.joaopaulofg.ifood.domain.model.Product;
import com.joaopaulofg.ifood.infrastructure.output.persistence.mapper.ProductEntityMapper;
import com.joaopaulofg.ifood.infrastructure.output.persistence.repository.SpringDataProductRepository;
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
    public Optional<Product> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        var entity = mapper.toEntity(product);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(UUID id) {

    }
}

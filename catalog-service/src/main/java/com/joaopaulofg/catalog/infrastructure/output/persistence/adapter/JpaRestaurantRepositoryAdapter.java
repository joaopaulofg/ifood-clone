package com.joaopaulofg.catalog.infrastructure.output.persistence.adapter;

import com.joaopaulofg.catalog.application.port.output.RestaurantRepository;
import com.joaopaulofg.catalog.domain.model.Restaurant;
import com.joaopaulofg.catalog.domain.vo.RestaurantId;
import com.joaopaulofg.catalog.infrastructure.output.persistence.mapper.RestaurantEntityMapper;
import com.joaopaulofg.catalog.infrastructure.output.persistence.repository.SpringDataRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class JpaRestaurantRepositoryAdapter implements RestaurantRepository {

    private final SpringDataRestaurantRepository repository;
    private final RestaurantEntityMapper mapper;

    @Override
    public Optional<Restaurant> findById(RestaurantId id) {
        return repository.findById(id.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public List<Restaurant> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        var entity = RestaurantEntityMapper.toEntity(restaurant);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(RestaurantId id) {
        repository.deleteById(id.getValue());
    }

    @Override
    public boolean existsById(RestaurantId id) {
        return repository.existsById(id.getValue());
    }
}


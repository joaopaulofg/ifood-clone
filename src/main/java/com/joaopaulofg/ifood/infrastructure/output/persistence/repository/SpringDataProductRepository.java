package com.joaopaulofg.ifood.infrastructure.output.persistence.repository;

import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataProductRepository extends CrudRepository<ProductEntity, String> {
}

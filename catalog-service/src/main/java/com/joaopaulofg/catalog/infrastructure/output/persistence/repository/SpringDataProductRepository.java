package com.joaopaulofg.catalog.infrastructure.output.persistence.repository;

import com.joaopaulofg.catalog.infrastructure.output.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataProductRepository extends CrudRepository<ProductEntity, String> {
}

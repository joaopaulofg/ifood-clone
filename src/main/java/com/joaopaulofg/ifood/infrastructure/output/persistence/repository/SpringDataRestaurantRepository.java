package com.joaopaulofg.ifood.infrastructure.output.persistence.repository;

import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRestaurantRepository extends CrudRepository<RestaurantEntity, String> {
}
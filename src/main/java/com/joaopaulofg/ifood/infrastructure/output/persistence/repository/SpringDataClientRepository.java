package com.joaopaulofg.ifood.infrastructure.output.persistence.repository;

import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpringDataClientRepository extends CrudRepository<ClientEntity, String> {
    Optional<ClientEntity> findByCpf(String cpf);
}
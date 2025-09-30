package com.ifood.clone.domain.ports.out;

import com.ifood.clone.domain.entities.Restaurante;

import java.util.Optional;
import java.util.UUID;

public interface RestauranteRepositoryPort {

    Restaurante salvar(Restaurante restaurante);
    Optional<Restaurante> buscarPorId(UUID id);
}

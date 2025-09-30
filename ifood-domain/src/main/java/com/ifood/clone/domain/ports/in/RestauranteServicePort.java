package com.ifood.clone.domain.ports.in;

import com.ifood.clone.domain.dtos.RestauranteRequestDTO;
import com.ifood.clone.domain.entities.Restaurante;

import java.util.Optional;
import java.util.UUID;

public interface RestauranteServicePort {

    Restaurante criarRestaurante(RestauranteRequestDTO restauranteRequestDTO);
    Restaurante buscarRestaurante(UUID id);

}

package com.ifood.clone.domain.mapper;

import com.ifood.clone.domain.dtos.RestauranteRequestDTO;
import com.ifood.clone.domain.dtos.RestauranteResponseDTO;
import com.ifood.clone.domain.entities.Restaurante;

public class RestauranteMapper {

    public RestauranteResponseDTO toRestauranteDTO(Restaurante restaurante){
        return new RestauranteResponseDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getDescricao(),
                restaurante.getCriadoEm()
        );
    }

    public Restaurante toRestaurante(RestauranteRequestDTO requestDTO){
        return new Restaurante(requestDTO.nome(), requestDTO.descricao());
    }
}

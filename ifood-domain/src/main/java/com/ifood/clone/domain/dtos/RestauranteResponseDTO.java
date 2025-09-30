package com.ifood.clone.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record RestauranteResponseDTO(
        UUID id,
        String nome,
        String descricao,
        LocalDateTime criadoEm
) {
}

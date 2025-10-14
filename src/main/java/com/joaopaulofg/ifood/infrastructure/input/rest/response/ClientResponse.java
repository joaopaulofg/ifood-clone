package com.joaopaulofg.ifood.infrastructure.input.rest.response;

import com.joaopaulofg.ifood.domain.model.Client;

import java.time.LocalDateTime;

public record ClientResponse(
        String id,
        String name,
        String email,
        String cpf,
        LocalDateTime creationDate
) {

    public static ClientResponse fromDomain(Client client) {
        return new ClientResponse(
                client.getId().getValue(),
                client.getName(),
                client.getEmail(),
                client.getCpf(),
                client.getCreationDate()
        );
    }
}
package com.joaopaulofg.ifood.application.service;

import com.joaopaulofg.ifood.domain.model.Client;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.ClientResponse;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientResponse toResponse(Client client) {
        return ClientResponse.fromDomain(client);
    }
}
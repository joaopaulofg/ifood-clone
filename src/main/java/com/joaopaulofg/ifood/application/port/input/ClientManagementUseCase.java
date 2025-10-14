package com.joaopaulofg.ifood.application.port.input;

import com.joaopaulofg.ifood.infrastructure.input.rest.response.ClientResponse;

import java.util.List;

public interface ClientManagementUseCase {
    ClientResponse create(String name, String email, String cpf);
    List<ClientResponse> findAllClients();
    ClientResponse findByCpf(String cpf);
}
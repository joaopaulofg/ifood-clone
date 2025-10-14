package com.joaopaulofg.ifood.application.port.output;

import com.joaopaulofg.ifood.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findByCpf(String cpf);
    List<Client> findAll();
}
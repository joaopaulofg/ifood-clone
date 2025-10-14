package com.joaopaulofg.ifood.application.service;

import com.joaopaulofg.ifood.application.port.input.ClientManagementUseCase;
import com.joaopaulofg.ifood.application.port.output.ClientRepository;
import com.joaopaulofg.ifood.domain.model.Client;
import com.joaopaulofg.ifood.domain.vo.ClientId;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.ClientResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientManagementService implements ClientManagementUseCase {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientManagementService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponse create(String name, String email, String cpf) {
        Client client = Client.create(ClientId.generate(), name, email, cpf);
        Client saved = clientRepository.save(client);
        return clientMapper.toResponse(saved);
    }

    @Override
    public ClientResponse findByCpf(String cpf) {
        return clientRepository.findByCpf(cpf)
                .map(clientMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with cpf: " + cpf));
    }

    @Override
    public List<ClientResponse> findAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }
}
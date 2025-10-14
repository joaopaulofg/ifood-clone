package com.joaopaulofg.ifood.infrastructure.output.persistence.adapter;

import com.joaopaulofg.ifood.application.port.output.ClientRepository;
import com.joaopaulofg.ifood.domain.model.Client;
import com.joaopaulofg.ifood.infrastructure.output.persistence.mapper.ClientEntityMapper;
import com.joaopaulofg.ifood.infrastructure.output.persistence.repository.SpringDataClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class JpaClientRepositoryAdapter implements ClientRepository {

    private final SpringDataClientRepository repository;
    private final ClientEntityMapper mapper;

    @Override
    public Client save(Client client) {
        var entity = ClientEntityMapper.toEntity(client);
        var saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Client> findByCpf(String cpf) {
        return repository.findByCpf(cpf).map(mapper::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}
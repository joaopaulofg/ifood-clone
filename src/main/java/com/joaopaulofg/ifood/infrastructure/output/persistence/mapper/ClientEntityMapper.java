package com.joaopaulofg.ifood.infrastructure.output.persistence.mapper;

import com.joaopaulofg.ifood.domain.model.Client;
import com.joaopaulofg.ifood.domain.vo.ClientId;
import com.joaopaulofg.ifood.infrastructure.output.persistence.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityMapper {

    public Client toDomain(ClientEntity entity) {
        return Client.restore(
                ClientId.of(entity.getId()),
                entity.getName(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getCreationDate()
        );
    }

    public static ClientEntity toEntity(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setId(client.getId().getValue());
        entity.setName(client.getName());
        entity.setEmail(client.getEmail());
        entity.setCpf(client.getCpf());
        entity.setCreationDate(client.getCreationDate());
        return entity;
    }
}
package com.joaopaulofg.ifood.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joaopaulofg.ifood.application.port.output.ClientRepository;
import com.joaopaulofg.ifood.domain.model.Client;
import com.joaopaulofg.ifood.domain.vo.ClientId;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.ClientResponse;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ClientManagementServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientManagementService clientManagementService;

    @Test
    void testFindByCpf_Success() {
        String cpf = "12345678900";
        Client client = Client.create(ClientId.generate(), "João", "joao@email.com", cpf);
        ClientResponse response = new ClientResponse(client.getId().toString(), "João", "joao@email.com", cpf,
                client.getCreationDate());

        when(clientRepository.findByCpf(cpf)).thenReturn(Optional.of(client));
        when(clientMapper.toResponse(client)).thenReturn(response);

        ClientResponse result = clientManagementService.findByCpf(cpf);

        assertNotNull(result);
        assertEquals("João", result.name());
        verify(clientRepository).findByCpf(cpf);
        verify(clientMapper).toResponse(client);
    }

    @Test
    void testFindByCpf_NotFound() {
        String cpf = "00000000000";
        when(clientRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> clientManagementService.findByCpf(cpf));

        assertEquals("Client not found with cpf: " + cpf, exception.getMessage());
    }

    @Test
    void findAllClients_Success() {
        Client client1 = Client.create(ClientId.generate(), "João Gomes", "joao@apple.com", "11215884419");
        Client client2 = Client.create(ClientId.generate(), "Yasmin", "yasmin@apple.com", "12345678911");

        ClientResponse response1 = new ClientResponse(client1.getId().toString(), "João Gomes", "joao@apple.com",
                "11215884419", client1.getCreationDate());
        ClientResponse response2 = new ClientResponse(client2.getId().toString(), "Yasmin", "yasmin@apple.com",
                "12345678911", client2.getCreationDate());

        when(clientRepository.findAll()).thenReturn(List.of(client1, client2));
        when(clientMapper.toResponse(client1)).thenReturn(response1);
        when(clientMapper.toResponse(client2)).thenReturn(response2);

        List<ClientResponse> clients = clientManagementService.findAllClients();

        assertEquals(2, clients.size());
        assertEquals("João Gomes", clients.get(0).name());
        assertEquals("Yasmin", clients.get(1).name());

        verify(clientRepository).findAll();
        verify(clientMapper).toResponse(client1);
        verify(clientMapper).toResponse(client2);
    }
}
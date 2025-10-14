package com.joaopaulofg.ifood.infrastructure.input.rest;

import com.joaopaulofg.ifood.application.port.input.ClientManagementUseCase;
import com.joaopaulofg.ifood.infrastructure.input.rest.request.CreateClientRequest;
import com.joaopaulofg.ifood.infrastructure.input.rest.response.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientManagementUseCase clientManagement;

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody CreateClientRequest request) {
        ClientResponse response = clientManagement.create(request.getName(), request.getEmail(), request.getCpf());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<ClientResponse> responses = clientManagement.findAllClients();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClientResponse> getClientByCpf(@PathVariable String cpf) {
        ClientResponse response = clientManagement.findByCpf(cpf);
        return ResponseEntity.ok(response);
    }

}
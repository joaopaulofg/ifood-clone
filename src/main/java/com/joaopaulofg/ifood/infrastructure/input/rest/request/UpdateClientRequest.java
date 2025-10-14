package com.joaopaulofg.ifood.infrastructure.input.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientRequest {
    private String name;
    private String email;
    private String cpf;
}
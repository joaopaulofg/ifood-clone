package com.joaopaulofg.ifood.domain.model;

import com.joaopaulofg.ifood.domain.exception.InvalidClientException;
import com.joaopaulofg.ifood.domain.vo.ClientId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Client {

    private ClientId id;
    private String name;
    private String email;
    private String cpf;
    private LocalDateTime creationDate;

    private Client(ClientId id, String name, String email, String cpf, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.creationDate = creationDate;
    }

    public static Client restore(ClientId id, String name, String email, String cpf, LocalDateTime creationDate) {
        return new Client(id, name, email, cpf, creationDate);
    }

    public static Client create(ClientId id, String name, String email, String cpf) {
        validate(name, email, cpf);
        return new Client(id, name.trim(), email.trim(), cpf.trim(), LocalDateTime.now());
    }

    public void update(String name, String email, String cpf) {
        validate(name, email, cpf);
        this.name = name.trim();
        this.email = email.trim();
        this.cpf = cpf.trim();
    }

    private static void validate(String name, String email, String cpf) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidClientException("Name must not be empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidClientException("Email must not be empty");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidClientException("Email must be valid");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new InvalidClientException("CPF must not be empty");
        }
        // Basic CPF length check (11 digits). Real validation would be more complex.
        String digits = cpf.replaceAll("[^0-9]", "");
        if (digits.length() != 11) {
            throw new InvalidClientException("CPF must have 11 digits");
        }
    }
}
package com.joaopaulofg.catalog.infrastructure.input.rest.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private String id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
}
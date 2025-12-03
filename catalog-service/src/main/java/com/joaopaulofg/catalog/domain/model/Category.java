package com.joaopaulofg.catalog.domain.model;

import com.joaopaulofg.catalog.domain.vo.CategoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Category {

    private CategoryId id;

    private String name;

    private String description;

    private LocalDateTime creationDate;

    public Category(CategoryId id, String name, String description, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
    }
}
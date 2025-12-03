package com.joaopaulofg.catalog.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.joaopaulofg.catalog.domain.vo.RestaurantId;

@Getter
@Setter
@NoArgsConstructor
public class Restaurant {

    private RestaurantId id;

    private String name;

    private String description;

    private LocalDateTime creationDate;

    public Restaurant(RestaurantId id, String name, String description, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
    }

}

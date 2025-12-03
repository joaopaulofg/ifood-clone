package com.joaopaulofg.ifood.infrastructure.output.client;

import com.joaopaulofg.ifood.infrastructure.output.client.dto.CatalogProductResponse;
import com.joaopaulofg.ifood.infrastructure.output.client.dto.CatalogRestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalogClient", url = "${catalog.service.url}")
public interface CatalogClient {

    @GetMapping("/api/products/{id}")
    CatalogProductResponse getProduct(@PathVariable("id") String id);

    @GetMapping("/api/restaurants/{id}")
    CatalogRestaurantResponse getRestaurant(@PathVariable("id") String id);

}

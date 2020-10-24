package io.henrique.sample.cities.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(CitiesController.class)
class CitiesControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldGetAllCities() {
        webTestClient.get().uri("/cities")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].name").isEqualTo("Porto Alegre")
                .jsonPath("[1].name").isEqualTo("Sao Paulo");
    }

}
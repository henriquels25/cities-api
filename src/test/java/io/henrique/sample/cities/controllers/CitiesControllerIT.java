package io.henrique.sample.cities.controllers;

import io.henrique.sample.cities.services.CitiesService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

@WebFluxTest(CitiesController.class)
@Tag("integrationTest")
class CitiesControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CitiesService citiesService;

    @Test
    void shouldGetAllCities() {
        when(citiesService.getCities()).thenReturn(Flux.just(new CityDTO("Porto Alegre"),
                new CityDTO("Rio de Janeiro")));

        webTestClient.get().uri("/cities")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].name").isEqualTo("Porto Alegre")
                .jsonPath("[1].name").isEqualTo("Rio de Janeiro");
    }

}
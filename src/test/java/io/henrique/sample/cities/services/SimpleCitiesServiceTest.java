package io.henrique.sample.cities.services;

import io.henrique.sample.cities.controllers.CityDTO;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class SimpleCitiesServiceTest {

    private CitiesService service = new SimpleCitiesService();

    @Test
    void shouldReturnTheDefaultListOfCities() {
        StepVerifier.create(service.getCities())
                .expectNext(new CityDTO("Porto Alegre"))
                .expectNext(new CityDTO("São Paulo"))
                .expectComplete();
    }
}
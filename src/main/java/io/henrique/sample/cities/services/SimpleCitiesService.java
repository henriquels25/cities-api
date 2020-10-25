package io.henrique.sample.cities.services;

import io.henrique.sample.cities.controllers.CityDTO;
import reactor.core.publisher.Flux;

public class SimpleCitiesService implements CitiesService {
    @Override
    public Flux<CityDTO> getCities() {
        return Flux.just(new CityDTO("Porto Alegre"), new CityDTO("Sao Paulo"));
    }
}

package io.henrique.sample.cities.services;

import io.henrique.sample.cities.controllers.CityDTO;
import reactor.core.publisher.Flux;

public interface CitiesService {
    Flux<CityDTO> getCities();
}

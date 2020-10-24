package io.henrique.sample.cities.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    @GetMapping
    public Flux<CityDTO> getCities() {
        return Flux.just(new CityDTO("Porto Alegre"), new CityDTO("Sao Paulo"));
    }

}

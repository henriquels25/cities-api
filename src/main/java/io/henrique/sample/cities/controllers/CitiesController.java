package io.henrique.sample.cities.controllers;

import io.henrique.sample.cities.services.CitiesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CitiesController {

    private final CitiesService citiesService;

    @GetMapping
    public Flux<CityDTO> getCities() {
        return citiesService.getCities();
    }

}

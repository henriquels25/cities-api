package io.henrique.sample.cities;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
@Tag("E2ETest")
class CitiesApiE2ETest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnAllCities() {
        webTestClient.get().uri("/cities")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].name").isEqualTo("Porto Alegre")
                .jsonPath("[1].name").isEqualTo("São Paulo");
    }

}

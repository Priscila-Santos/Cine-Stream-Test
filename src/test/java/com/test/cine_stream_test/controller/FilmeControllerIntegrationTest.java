package com.test.cine_stream_test.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilmeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void setup() {
        wireMockServer = new WireMockServer(8081); // porta diferente a aplicação
        wireMockServer.start();
        configureFor("localhost", 8081);
    }

    @AfterAll
    static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void getTodosFilmes_deveRetornarListaDeFilmes() {
        //dado
        wireMockServer.stubFor(get(urlEqualTo("/discover/movie"))
        .willReturn(aResponse()
        .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"page\":1,\"results\":[{\"id\":1,\"title\":\"Filme Exemplo\",\"overview\":\"Descrição do Filme\",\"poster_path\":\"/caminho/do/poster.jpg\"}],\"total_pages\":1,\"total_results\":1}")));

        //quando
        ResponseEntity<Page> response = restTemplate.getForEntity("http://localhost:" + port + "api/filme?page=1", Page.class);

        //Então
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1, response.getBody().getResults().size());
    }

    //Adicionar mais testes
}

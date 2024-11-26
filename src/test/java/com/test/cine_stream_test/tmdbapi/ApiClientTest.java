package com.test.cine_stream_test.tmdbapi;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApiClientTest {

    private static WireMockServer wireMockServer;

    @Autowired
    private ApiClient apiClient;

    @BeforeAll
    static void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8081));
        wireMockServer.start();
        WireMock.configureFor("localhost", 8081);
    }

    @AfterAll
    static void tearDown() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    @Test
    void buscarTodosFilmes_deveRetornarListaDeFilmes() {
        // Configura o WireMock para simular a resposta da API
        wireMockServer.stubFor(get(urlPathEqualTo("/discover/movie"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("language", equalTo("pt-BR"))
                .withHeader("Authorization", containing("Bearer "))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"page\":1,\"results\":[{\"id\":1,\"title\":\"Filme Exemplo\",\"overview\":\"Descrição do Filme\",\"poster_path\":\"/caminho/do/poster.jpg\"}],\"total_pages\":1,\"total_results\":1}")));

        // Executa o método a ser testado
        Page<TmdbFilme> filmes = apiClient.buscarTodosFilmes(1);

        // Verifica os resultados
        assertNotNull(filmes);
        assertEquals(1, filmes.getResults().size());
        assertEquals("Filme Exemplo", filmes.getResults().get(0).getTitle());
    }

    // Adicionar mais testes conforme necessário
}

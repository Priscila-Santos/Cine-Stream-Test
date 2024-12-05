package com.test.cine_stream_test.tmdbapi;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.test.cine_stream_test.config.WiremockExtensionConfig;
import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbListaGeneros;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(WiremockExtensionConfig.class)
public class ApiClientTest {

    private ApiClient apiClient;

    @BeforeEach
    public void setup() {
        apiClient = new ApiClient("http://localhost:8282", "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTRmMjEzMWNmMmU5ZjM1ZjczMWU5MWRkNThkNmY5MyIsIm5iZiI6MTczMTAyMDQ5NC42MDM1MzExLCJzdWIiOiI2NzJkM2NhM2NhZjcyN2YzNzQ4YWE3YzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.Ddh56v5MEKTauIqjp4HJv-xfjCwdfrpsn_JvjAkLHoU");
    }

    @Test
    public void buscarFilmesPorTitulo_deveRetornarFilmesCorrespondentes() throws Exception {
        WireMock.configureFor(8282);
        String query = "Inception";
        String jsonResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/wiremock/responses/search_movie_inception.json")));

        stubFor(get(urlPathEqualTo("/search/movie"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("query", equalTo(query))
                .withQueryParam("language", equalTo("pt-BR"))
                .withHeader("Authorization", containing("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTRmMjEzMWNmMmU5ZjM1ZjczMWU5MWRkNThkNmY5MyIsIm5iZiI6MTczMTAyMDQ5NC42MDM1MzExLCJzdWIiOiI2NzJkM2NhM2NhZjcyN2YzNzQ4YWE3YzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.Ddh56v5MEKTauIqjp4HJv-xfjCwdfrpsn_JvjAkLHoU"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(jsonResponse)));

        Page<TmdbFilme> filmes = apiClient.buscarFilmesPorTitulo(query, 1);

        assertNotNull(filmes);
        assertEquals(2, filmes.getResults().size());

        List<String> titulos = filmes.getResults().stream()
                .map(TmdbFilme::getTitle)
                .collect(Collectors.toList());

        boolean titulosCorrespondentes = titulos.stream().allMatch(title -> title.contains(query));

        assertEquals(true, titulosCorrespondentes, "Todos os títulos devem conter a string de consulta");
    }

    @Test
    public void buscarDetalhesFilme_deveRetornarDetalhesDoFilme() {
        WireMock.configureFor(8282);
        stubFor(get(urlPathEqualTo("/movie/1"))
                .withHeader("Authorization", containing("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTRmMjEzMWNmMmU5ZjM1ZjczMWU5MWRkNThkNmY5MyIsIm5iZiI6MTczMTAyMDQ5NC42MDM1MzExLCJzdWIiOiI2NzJkM2NhM2NhZjcyN2YzNzQ4YWE3YzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.Ddh56v5MEKTauIqjp4HJv-xfjCwdfrpsn_JvjAkLHoU"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"id\":1,\"title\":\"Filme Exemplo\",\"overview\":\"Descrição do Filme\",\"poster_path\":\"/caminho/do/poster.jpg\"}")));

        TmdbFilme filme = apiClient.buscarDetalhesFilme(1L);

        assertNotNull(filme);
        assertEquals("Filme Exemplo", filme.getTitle());
    }

    @Test
    public void generosFilmes_deveRetornarListaDeGeneros() {
        WireMock.configureFor(8282);
        stubFor(get(urlPathEqualTo("/genre/movie/list"))
                .withQueryParam("language", equalTo("pt"))
                .withHeader("Authorization", containing("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTRmMjEzMWNmMmU5ZjM1ZjczMWU5MWRkNThkNmY5MyIsIm5iZiI6MTczMTAyMDQ5NC42MDM1MzExLCJzdWIiOiI2NzJkM2NhM2NhZjcyN2YzNzQ4YWE3YzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.Ddh56v5MEKTauIqjp4HJv-xfjCwdfrpsn_JvjAkLHoU"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"genres\":[{\"id\":28,\"name\":\"Action\"}]}")));

        TmdbListaGeneros generos = apiClient.generosFilmes();

        assertNotNull(generos);
        assertEquals(1, generos.getGenres().size());
        assertEquals("Action", generos.getGenres().get(0).getName());
    }

    @Test
    public void buscarTodasSeries_deveRetornarListaDeSeries() {
        WireMock.configureFor(8282);
        stubFor(get(urlPathEqualTo("/discover/tv"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("language", equalTo("pt-BR"))
                .withHeader("Authorization", containing("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTRmMjEzMWNmMmU5ZjM1ZjczMWU5MWRkNThkNmY5MyIsIm5iZiI6MTczMTAyMDQ5NC42MDM1MzExLCJzdWIiOiI2NzJkM2NhM2NhZjcyN2YzNzQ4YWE3YzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.Ddh56v5MEKTauIqjp4HJv-xfjCwdfrpsn_JvjAkLHoU"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"page\":1,\"results\":[{\"id\":1,\"name\":\"Serie Exemplo\",\"overview\":\"Descrição da Série\",\"poster_path\":\"/caminho/do/poster.jpg\"}],\"total_pages\":1,\"total_results\":1}")));

        Page<TmdbSerie> series = apiClient.buscarTodasSeries(1);

        assertNotNull(series);
        assertEquals(1, series.getResults().size());
        assertEquals("Serie Exemplo", series.getResults().get(0).getName());
    }

    @Test
    public void buscarSeriesPorTitulo_deveRetornarListaDeSeries() throws Exception {
        WireMock.configureFor(8282);
        String query = "Lost";
        String jsonResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/wiremock/responses/search_tv_lost.json")));

        stubFor(get(urlPathEqualTo("/search/tv"))
                .withQueryParam("page", equalTo("1"))
                .withQueryParam("query", equalTo(query))
                .withQueryParam("language", equalTo("pt-BR"))
                .withHeader("Authorization", containing("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTRmMjEzMWNmMmU5ZjM1ZjczMWU5MWRkNThkNmY5MyIsIm5iZiI6MTczMTAyMDQ5NC42MDM1MzExLCJzdWIiOiI2NzJkM2NhM2NhZjcyN2YzNzQ4YWE3YzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.Ddh56v5MEKTauIqjp4HJv-xfjCwdfrpsn_JvjAkLHoU"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(jsonResponse)));

        Page<TmdbSerie> series = apiClient.buscarSeriesPorTitulo(query, 1);

        assertNotNull(series);
        assertEquals(2, series.getResults().size());

        List<String> titulos = series.getResults().stream()
                .map(TmdbSerie::getName)
                .collect(Collectors.toList());

        boolean titulosCorrespondentes = titulos.stream().allMatch(name -> name.contains(query));

        assertEquals(true, titulosCorrespondentes, "Todos os títulos devem conter a string de consulta");
    }




    @Test
    public void buscarDetalhesSerie_deveRetornarDetalhesDaSerie() {
        WireMock.configureFor(8282);
        stubFor(get(urlPathEqualTo("/tv/1"))
                .withHeader("Authorization", containing("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTRmMjEzMWNmMmU5ZjM1ZjczMWU5MWRkNThkNmY5MyIsIm5iZiI6MTczMTAyMDQ5NC42MDM1MzExLCJzdWIiOiI2NzJkM2NhM2NhZjcyN2YzNzQ4YWE3YzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.Ddh56v5MEKTauIqjp4HJv-xfjCwdfrpsn_JvjAkLHoU"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"id\":1,\"name\":\"Serie Exemplo\",\"overview\":\"Descrição da Série\",\"poster_path\":\"/caminho/do/poster.jpg\"}")));

        TmdbSerie serie = apiClient.buscarDetalhesSerie(1L);

        assertNotNull(serie);
        assertEquals("Serie Exemplo", serie.getName());
    }

    @Test
    public void generosSeries_deveRetornarListaDeGeneros() {
        WireMock.configureFor(8282);
        stubFor(get(urlPathEqualTo("/genre/tv/list"))
                .withQueryParam("language", equalTo("pt"))
                .withHeader("Authorization", containing("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTRmMjEzMWNmMmU5ZjM1ZjczMWU5MWRkNThkNmY5MyIsIm5iZiI6MTczMTAyMDQ5NC42MDM1MzExLCJzdWIiOiI2NzJkM2NhM2NhZjcyN2YzNzQ4YWE3YzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.Ddh56v5MEKTauIqjp4HJv-xfjCwdfrpsn_JvjAkLHoU"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"genres\":[{\"id\":28,\"name\":\"Action\"}]}")));

        TmdbListaGeneros generos = apiClient.generosSeries();

        assertNotNull(generos);
        assertEquals(1, generos.getGenres().size());
        assertEquals("Action", generos.getGenres().get(0).getName());
    }

}
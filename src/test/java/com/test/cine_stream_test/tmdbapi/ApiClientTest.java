//package com.test.cine_stream_test.tmdbapi;
//
//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
//import com.test.cine_stream_test.config.WiremockExtensionConfig;
//import com.test.cine_stream_test.tmdbapi.dto.response.Page;
//import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
//import com.test.cine_stream_test.tmdbapi.dto.response.TmdbListaGeneros;
//import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(WiremockExtensionConfig.class)
//@SpringBootTest
//public class ApiClientTest {
//
//    @InjectMocks
//    private ApiClient apiClient;
//
//    @Value("${api.base.url}")
//    private String apiBaseUrl;
//
//    @Value("${api.key}")
//    private String apiKey;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        WireMock.configureFor("localhost", 8282);
//        reset();
//    }
//
//
//
//    @Test
//    public void buscarFilmesPorTitulo_deveRetornarListaDeFilmes() {
//        stubFor(get(urlPathEqualTo("/search/movie"))
//                .withQueryParam("page", equalTo("1"))
//                .withQueryParam("query", equalTo("Inception"))
//                .withQueryParam("language", equalTo("pt-BR"))
//                .withHeader("Authorization", containing("Bearer " + apiKey))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody("{\"page\":1,\"results\":[{\"id\":1,\"title\":\"Inception\",\"overview\":\"A mind-bending thriller\",\"poster_path\":\"/posterPath.jpg\"}],\"total_pages\":1,\"total_results\":1}")));
//
//        Page<TmdbFilme> filmes = apiClient.buscarFilmesPorTitulo("Inception", 1);
//
//        assertNotNull(filmes);
//        assertEquals(1, filmes.getResults().size());
//        assertEquals("Inception", filmes.getResults().get(0).getTitle());
//    }
//
//    @Test
//    public void buscarDetalhesFilme_deveRetornarDetalhesDoFilme() {
//        stubFor(get(urlPathEqualTo("/movie/1"))
//                .withHeader("Authorization", containing("Bearer " + apiKey))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody("{\"id\":1,\"title\":\"Filme Exemplo\",\"overview\":\"Descrição do Filme\",\"poster_path\":\"/caminho/do/poster.jpg\"}")));
//
//        TmdbFilme filme = apiClient.buscarDetalhesFilme(1L);
//
//        assertNotNull(filme);
//        assertEquals("Filme Exemplo", filme.getTitle());
//    }
//
//    @Test
//    public void generosFilmes_deveRetornarListaDeGeneros() {
//        stubFor(get(urlPathEqualTo("/genre/movie/list"))
//                .withQueryParam("language", equalTo("pt"))
//                .withHeader("Authorization", containing("Bearer " + apiKey))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody("{\"genres\":[{\"id\":28,\"name\":\"Action\"}]}")));
//
//        TmdbListaGeneros generos = apiClient.generosFilmes();
//
//        assertNotNull(generos);
//        assertEquals(1, generos.getGenres().size());
//        assertEquals("Action", generos.getGenres().get(0).getName());
//    }
//
//    @Test
//    public void buscarTodasSeries_deveRetornarListaDeSeries() {
//        stubFor(get(urlPathEqualTo("/discover/serie"))
//                .withQueryParam("page", equalTo("1"))
//                .withQueryParam("language", equalTo("pt-BR"))
//                .withHeader("Authorization", containing("Bearer " + apiKey))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody("{\"page\":1,\"results\":[{\"id\":1,\"name\":\"Serie Exemplo\",\"overview\":\"Descrição da Série\",\"poster_path\":\"/caminho/do/poster.jpg\"}],\"total_pages\":1,\"total_results\":1}")));
//
//        Page<TmdbSerie> series = apiClient.buscarTodasSeries(1);
//
//        assertNotNull(series);
//        assertEquals(1, series.getResults().size());
//        assertEquals("Serie Exemplo", series.getResults().get(0).getName());
//    }
//
//    @Test
//    public void buscarSeriesPorTitulo_deveRetornarListaDeSeries() {
//        stubFor(get(urlPathEqualTo("/search/tv"))
//                .withQueryParam("page", equalTo("1"))
//                .withQueryParam("query", equalTo("Breaking Bad"))
//                .withQueryParam("language", equalTo("pt-BR"))
//                .withHeader("Authorization", containing("Bearer " + apiKey))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody("{\"page\":1,\"results\":[{\"id\":1,\"name\":\"Breaking Bad\",\"overview\":\"A high school chemistry teacher turned methamphetamine producer.\",\"poster_path\":\"/posterPath.jpg\"}],\"total_pages\":1,\"total_results\":1}")));
//
//        Page<TmdbSerie> series = apiClient.buscarSeriesPorTitulo("Breaking Bad", 1);
//
//        assertNotNull(series);
//        assertEquals(1, series.getResults().size());
//        assertEquals("Breaking Bad", series.getResults().get(0).getName());
//    }
//
//    @Test
//    public void buscarDetalhesSerie_deveRetornarDetalhesDaSerie() {
//        stubFor(get(urlPathEqualTo("/tv/1"))
//                .withHeader("Authorization", containing("Bearer " + apiKey))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody("{\"id\":1,\"name\":\"Serie Exemplo\",\"overview\":\"Descrição da Série\",\"poster_path\":\"/caminho/do/poster.jpg\"}")));
//
//        TmdbSerie serie = apiClient.buscarDetalhesSerie(1L);
//
//        assertNotNull(serie);
//        assertEquals("Serie Exemplo", serie.getName());
//    }
//
//    @Test
//    public void generosSeries_deveRetornarListaDeGeneros() {
//        stubFor(get(urlPathEqualTo("/genre/tv/list"))
//                .withQueryParam("language", equalTo("pt"))
//                .withHeader("Authorization", containing("Bearer " + apiKey))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody("{\"genres\":[{\"id\":28,\"name\":\"Action\"}]}")));
//
//        TmdbListaGeneros generos = apiClient.generosSeries();
//
//        assertNotNull(generos);
//        assertEquals(1, generos.getGenres().size());
//        assertEquals("Action", generos.getGenres().get(0).getName());
//    }
//
//    // Adicione mais testes conforme necessário
//}

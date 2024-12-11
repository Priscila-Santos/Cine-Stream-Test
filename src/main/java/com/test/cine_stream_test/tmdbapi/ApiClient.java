package com.test.cine_stream_test.tmdbapi;

import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.tmdbapi.dto.response.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Component
public class ApiClient {

    private final RestTemplate restTemplate;
    private final String apiBaseUrl;
    private final String apiKey;

    public ApiClient(@Value("${api.base.url}") String apiBaseUrl, @Value("${api.key}") String apiKey) {
        this.restTemplate = new RestTemplate();
        this.apiBaseUrl = apiBaseUrl;
        this.apiKey = apiKey;
    }

    // ENDPOINTS da API externa

    // FILMES
    public Page<TmdbFilme> buscarTodosFilmes(Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/discover/movie")
                .queryParam("page", page)
                .queryParam("language", "pt-BR")
                .toUriString();

        return getTmdbFilmePage(url);
    }

    public Page<TmdbFilme> buscarFilmesPorTitulo(String titulo, Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/search/movie")
                .queryParam("page", page)
                .queryParam("query", titulo)
                .queryParam("language", "pt-BR")
                .toUriString();

        return getTmdbFilmePage(url);
    }

    private Page<TmdbFilme> getTmdbFilmePage(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", apiKey));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        var response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Page<TmdbFilme>>() {}
        );

        return response.getBody();
    }

    public TmdbFilme buscarDetalhesFilme(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/movie/" + id)
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", apiKey));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        var response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                TmdbFilme.class
        );
        return response.getBody();
    }


    public TmdbListaGeneros generosFilmes() {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/genre/movie/list")
                .queryParam("language", "pt")
                .toUriString();

        return getTmdbListaGeneros(url);
    }

//    public Page<TmdbFilme> buscarFilmesPorNomeGenero(String genreName, Integer page) throws NotFoundException {
//        // Primeiro, buscar a lista de gêneros para encontrar o ID correspondente
//        TmdbListaGeneros generos = buscarGeneros();
//        Integer genreId = generos.getGenres().stream()
//                .filter(g -> g.getName().equalsIgnoreCase(genreName))
//                .map(g -> g.getId())
//                .findFirst()
//                .orElseThrow(() -> new NotFoundException("Gênero não encontrado: " + genreName));
//
//        // Agora, faça a busca usando o ID do gênero
//        return buscarFilmesPorGenero(genreId, page);
//    }

    public Page<TmdbFilme> buscarFilmesPorGenero(Integer genreId, Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/discover/movie")
                .queryParam("with_genres", genreId)
                .queryParam("page", page)
                .queryParam("language", "pt-BR")
                .toUriString();

        return getTmdbFilmePage(url);
    }



    private TmdbListaGeneros getTmdbListaGeneros(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", apiKey));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        var response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                TmdbListaGeneros.class
        );
        return response.getBody();
    }

    public Page<TmdbFilme> buscarFilmesPorAnoLancamento(String ano, Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/search/movie")
                .queryParam("query", " ")
                .queryParam("page", page)
                .queryParam("year", ano)
                .queryParam("language", "pt-BR")
                .toUriString();

        return getTmdbFilmePage(url);
    }

    // SERIES
    public Page<TmdbSerie> buscarTodasSeries(Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/discover/tv")
                .queryParam("page", page)
                .queryParam("language", "pt-BR")
                .toUriString();

        return getTmdbSeriePage(url);
    }

    private Page<TmdbSerie> getTmdbSeriePage(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", apiKey));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        var response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Page<TmdbSerie>>() {}
        );
        return response.getBody();
    }

    public Page<TmdbSerie> buscarSeriesPorTitulo(String titulo, Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/search/tv")
                .queryParam("page", page)
                .queryParam("query", titulo)
                .queryParam("language", "pt-BR")
                .toUriString();

        return getTmdbSeriePage(url);
    }

    public TmdbListaGeneros generosSeries() {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/genre/tv/list")
                .queryParam("language", "pt")
                .toUriString();

        return getTmdbListaGeneros(url);
    }

    public Page<TmdbSerie> buscarSeriesPorAnoLancamento(String ano, Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/search/tv")
                .queryParam("query", " ")
                .queryParam("page", page)
                .queryParam("first_air_date_year", ano)
                .queryParam("language", "pt-BR")
                .toUriString();

        return getTmdbSeriePage(url);
    }

    public TmdbSerie buscarDetalhesSerie(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/tv/" + id)
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", apiKey));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        var response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                TmdbSerie.class
        );
        return response.getBody();
    }

    // Buscar por gênero na API
    public TmdbListaGeneros buscarGeneros() {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/genre")
                .queryParam("language", "pt-BR")
                .toUriString();

        return getTmdbListaGeneros(url);
    }
}

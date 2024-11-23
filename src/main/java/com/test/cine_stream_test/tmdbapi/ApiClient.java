package com.test.cine_stream_test.tmdbapi;

import com.test.cine_stream_test.tmdbapi.dto.response.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ApiClient {
    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Value("${api.key}")
    private String apiKey;

    public ApiClient() {
        this.restTemplate = new RestTemplate();
    }

    // ENDPOINTS da API externa

    //FILMES
    public Page<TmdbFilme> buscarTodosFilmes(Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("discover/movie")
                .queryParam("page", page)
                .queryParam("language", "pt-BR")
                .toUriString();

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


    public Page<TmdbFilme> buscarFilmesPorTitulo(String titulo, Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/search/movie")
                .queryParam("page", page)
                .queryParam("query", titulo)
                .queryParam("language", "pt-BR")
                .toUriString();

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


    // SERIES
    public Page<TmdbSerie> buscarTodasSeries(Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("discover/serie")
                .queryParam("page", page)
                .queryParam("language", "pt-BR")
                .toUriString();

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
                .queryParam("api_key", apiKey)
                .queryParam("query", titulo)
                .queryParam("page", page)
                .queryParam("language", "pt-BR")
                .toUriString();

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

    public TmdbListaGeneros generosSeries() {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/genre/tv/list")
                .queryParam("language", "pt")
                .toUriString();

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

    public Page<TmdbSerie> buscarSeriesPorAnoLancamento(String ano, Integer page) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/search/tv")
                .queryParam("query", " ")
                .queryParam("page", page)
                .queryParam("first_air_date_year", ano)
                .queryParam("language", "pt-BR")
                .toUriString();

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

    //buscar por genero na api
    public TmdbListaGeneros buscarGeneros() {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path("/genre")
                .queryParam("language", "pt-BR")
                .toUriString();

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
}

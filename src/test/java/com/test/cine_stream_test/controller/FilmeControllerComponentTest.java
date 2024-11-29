package com.test.cine_stream_test.controller;

import com.test.cine_stream_test.dto.request.FilmeFavoritoRequest;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.service.FilmeService;
import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbListaGeneros;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FilmeControllerComponentTest {

    private MockMvc mockMvc;

    @Mock
    private FilmeService filmeService;

    @InjectMocks
    private FilmeController filmeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filmeController).build();
    }

    @Test
    public void getTodosFilme_deveRetornarListaDeFilmes() throws Exception {
        Page<TmdbFilme> filmes = new Page<>();
        TmdbFilme filme = new TmdbFilme("Titulo do Filme", "Descricao do Filme", "Diretor do Filme", 8.5, "/poster/path.jpg", 12345L);
        filmes.setResults(Collections.singletonList(filme));
        when(filmeService.buscarTodosFilmes(1)).thenReturn(filmes);

        mockMvc.perform(get("/filmes/all-filmes").param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.results").isArray());
    }

    @Test
    public void getGenres_deveRetornarListaDeGeneros() throws Exception {
        TmdbListaGeneros generos = new TmdbListaGeneros();
        generos.setGenres(Collections.singletonList(new TmdbListaGeneros.Genre("Ação", 1)));
        when(filmeService.buscarGeneros()).thenReturn(generos);

        mockMvc.perform(get("/filmes/genres"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.genres").isArray());
    }

    @Test
    public void buscarPorTitulo_deveRetornarListaDeFilmes() throws Exception {
        Page<TmdbFilme> filmes = new Page<>();
        TmdbFilme filme = new TmdbFilme("Titulo do Filme", "Descricao do Filme", "Diretor do Filme", 8.5, "/poster/path.jpg", 12345L);
        filmes.setResults(Collections.singletonList(filme));
        when(filmeService.buscarFilmePorTitulo("Inception", 1)).thenReturn(filmes);

        mockMvc.perform(get("/filmes").param("titulo", "Inception").param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.results").isArray());
    }

    @Test
    public void adicionarFilmeFavorito_deveRetornarStatusCreated() throws Exception {
        FilmeFavoritoRequest request = new FilmeFavoritoRequest();
        request.setIdUsuario(1L);
        request.setIdFilme(101L);

        mockMvc.perform(post("/filmes/favorito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idUsuario\": 1, \"tmdbId\": 101}"))
                .andExpect(status().isCreated());
    }
}

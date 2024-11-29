package com.test.cine_stream_test.controller;

import com.test.cine_stream_test.dto.request.SerieFavoritaRequest;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.service.SerieService;
import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbListaGeneros;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;
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

public class SerieControllerComponentTest {

    private MockMvc mockMvc;

    @Mock
    private SerieService serieService;

    @InjectMocks
    private SerieController serieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(serieController).build();
    }

    @Test
    public void getTodasSeries_deveRetornarListaDeSeries() throws Exception {
        Page<TmdbSerie> series = new Page<>();
        series.setResults(Collections.singletonList(new TmdbSerie()));
        when(serieService.buscarTodasSeries(1)).thenReturn(series);

        mockMvc.perform(get("/series/all-series").param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.results").isArray());
    }

    @Test
    public void getGenres_deveRetornarListaDeGeneros() throws Exception {
        TmdbListaGeneros generos = new TmdbListaGeneros();
        generos.setGenres(Collections.singletonList(new TmdbListaGeneros.Genre()));
        when(serieService.buscarGeneros()).thenReturn(generos);

        mockMvc.perform(get("/series/genres-series"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.genres").isArray());
    }

    @Test
    public void buscarPorTitulo_deveRetornarListaDeSeries() throws Exception {
        Page<TmdbSerie> series = new Page<>();
        series.setResults(Collections.singletonList(new TmdbSerie()));
        when(serieService.buscarSeriePorTitulo("Breaking Bad", 1)).thenReturn(series);

        mockMvc.perform(get("/series").param("titulo", "Breaking Bad").param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.results").isArray());
    }

    @Test
    public void adicionarSerieFavorita_deveRetornarStatusCreated() throws Exception {
        SerieFavoritaRequest request = new SerieFavoritaRequest();
        request.setIdUsuario(1L);
        request.setIdSerie(101L);

        mockMvc.perform(post("/series/favorita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idUsuario\": 1, \"tmdbId\": 101}"))
                .andExpect(status().isCreated());
    }
}

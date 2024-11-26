package com.test.cine_stream_test.controller;

import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbListaGeneros;
import com.test.cine_stream_test.dto.request.FilmeFavoritoRequest;
import com.test.cine_stream_test.service.FilmeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class FilmeControllerComponentTest {

    @Mock
    private FilmeService filmeService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private FilmeController filmeController;

    @Test
    public void todosFilmes_realizoConsulta_deveRetornarFilmesDaBase() throws Exception {
        // Dado
        var tmdbFilme = new TmdbFilme("Inception", "A mind-bending thriller", "2010-07-16", 8.8, "/posterPath.jpg", 12345L);
        var page = new Page<TmdbFilme>();
        page.setResults(List.of(tmdbFilme));
        Mockito.when(filmeService.buscarTodosFilmes(1)).thenReturn(page);

        // Quando
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/filmes")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(
                        MockMvcResultHandlers.print()
                ).andExpect(
                        MockMvcResultMatchers.status().isOk()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.results[0].title").value("Inception")
                );
    }

    @Test
    public void buscarPorTitulo_realizoConsultaPorTitulo_deveRetornarFilmeDaBase() throws Exception {
        // Dado
        var tmdbFilme = new TmdbFilme("Inception", "A mind-bending thriller", "2010-07-16", 8.8, "/posterPath.jpg", 12345L);
        var page = new Page<TmdbFilme>();
        page.setResults(List.of(tmdbFilme));
        Mockito.when(filmeService.buscarFilmePorTitulo("Inception", 1)).thenReturn(page);

        // Quando
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api")
                                .param("titulo", "Inception")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(
                        MockMvcResultHandlers.print()
                ).andExpect(
                        MockMvcResultMatchers.status().isOk()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.results[0].title").value("Inception")
                );
    }

    @Test
    public void adicionarFilmeFavorito_semInformarFilme_deveRetornarBadRequest() throws Exception {
        // Dado
        var filmeFavoritoRequest = """
                {
                  "userId": 1
                }
                """;

        // Quando
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/favorito")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(filmeFavoritoRequest)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
    }

    @Test
    public void getGenres_realizoConsulta_deveRetornarGenerosDaBase() throws Exception {
        // Dado
        var genero = new TmdbListaGeneros.Genre("Action", 28);
        var tmdbGenero = new TmdbListaGeneros();
        tmdbGenero.setGenres(List.of(genero));
        Mockito.when(filmeService.buscarGeneros()).thenReturn(tmdbGenero);

        // Quando
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/genres")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(
                        MockMvcResultHandlers.print()
                ).andExpect(
                        MockMvcResultMatchers.status().isOk()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.genres[0].name").value("Action")
                );
    }
}

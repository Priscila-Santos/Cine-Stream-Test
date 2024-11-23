package com.test.cine_stream_test.service;

import com.test.cine_stream_test.dto.request.FilmeFavoritoRequest;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.model.FilmeFavorito;
import com.test.cine_stream_test.model.Usuario;
import com.test.cine_stream_test.repository.FilmeFavoritoRepository;
import com.test.cine_stream_test.tmdbapi.ApiClient;
import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class FilmeServiceTest {

    @Mock
    private FilmeFavoritoRepository filmeFavoritoRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ApiClient apiClient;

    @InjectMocks
    private FilmeService filmeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarTodosFilmes_deveRetornarListaDeFilmes() {
        //dado
        Page<TmdbFilme> page = new Page<>();
        when(apiClient.buscarTodosFilmes(1)).thenReturn(page);

        //quando
        Page<TmdbFilme> result = filmeService.buscarTodosFilmes(1);

        //então
        Assertions.assertEquals(page, result);
    }

    @Test
    void adicionarFilmeFavorito_deveSalvarFilmeFavorito() throws NotFoundException {
        //dado
        FilmeFavoritoRequest request = new FilmeFavoritoRequest();
        request.setIdUsuario(1L);
        Usuario usuario = new Usuario();
        when(usuarioService.buscarPorId(1L)).thenReturn(usuario);

        //quando
        filmeService.adicionarFilmeFavorito(request);

        //então
        verify(filmeFavoritoRepository, times(1)).save(any());
    }
}

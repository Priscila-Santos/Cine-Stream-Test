package com.test.cine_stream_test.service;

import com.test.cine_stream_test.dto.request.SerieFavoritaRequest;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.model.Usuario;
import com.test.cine_stream_test.repository.SerieFavoritaRepository;
import com.test.cine_stream_test.tmdbapi.ApiClient;
import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class SerieServiceTest {

    @Mock
    private SerieFavoritaRepository serieFavoritaRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ApiClient apiClient;

    @InjectMocks
    private SerieService serieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarTodasSeries_deveRetornarListaDeSeries() {
        //dado
        Page<TmdbSerie> page = new Page<>();
        when(apiClient.buscarTodasSeries(1)).thenReturn(page);

        //quando
        Page<TmdbSerie> result = serieService.buscarTodasSeries(1);

        //então
        Assertions.assertEquals(page, result);
    }

    @Test
    void buscarSeriePorTitulo_deveRetornarListaDeSeries() {
        //dado
        Page<TmdbSerie> page = new Page<>();
        when(apiClient.buscarSeriesPorTitulo("exemplo", 1)).thenReturn(page);

        //quando
        Page<TmdbSerie> result = serieService.buscarSeriePorTitulo("exemplo",1);

        //então
        Assertions.assertEquals(page, result);
    }

    @Test
    void adicionarSerieFavorita_deveSalvarSerieFavorita() throws NotFoundException {
        //dado
        SerieFavoritaRequest request = new SerieFavoritaRequest();
        request.setIdUsuario(1L);
        Usuario usuario = new Usuario();
        when(usuarioService.buscarPorId(1L)).thenReturn(usuario);

        //quando
        serieService.adicionarSerieFavorita(request);

        //então
        verify(serieFavoritaRepository, times(1)).save(any());
    }


}

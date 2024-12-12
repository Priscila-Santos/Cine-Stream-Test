package com.test.cine_stream_test.service;

import com.test.cine_stream_test.tmdbapi.ApiClient;
import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbListaGeneros;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;
import com.test.cine_stream_test.dto.mapping.SerieFavoritaMapper;
import com.test.cine_stream_test.dto.request.SerieFavoritaRequest;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.model.SerieFavorita;
import com.test.cine_stream_test.model.Usuario;
import com.test.cine_stream_test.repository.SerieFavoritaRepository;
import org.springframework.stereotype.Service;

@Service
public class SerieService {

    private final SerieFavoritaRepository serieFavoritaRepository;
    private final UsuarioService usuarioService;
    private final ApiClient tmdbClient;
    private final ApiClient apiClient;

    public SerieService(SerieFavoritaRepository serieFavoritaRepository, UsuarioService usuarioService, ApiClient tmdbClient, ApiClient apiClient) {
        this.serieFavoritaRepository = serieFavoritaRepository;
        this.usuarioService = usuarioService;
        this.tmdbClient = tmdbClient;
        this.apiClient = apiClient;
    }

    public Page<TmdbSerie> buscarTodasSeries(Integer page) {
        return tmdbClient.buscarTodasSeries(page);
    }

    public Page<TmdbSerie> buscarSeriePorTitulo(String titulo, Integer page) {
        return tmdbClient.buscarSeriesPorTitulo(titulo, page);
    }

    public void adicionarSerieFavorita(SerieFavoritaRequest serieFavoritaRequest) throws NotFoundException {
        Usuario usuario = usuarioService.buscarPorId(serieFavoritaRequest.getIdUsuario());
        SerieFavoritaMapper mapper = new SerieFavoritaMapper();
        SerieFavorita serieFavorita = mapper.toEntity(serieFavoritaRequest, usuario);
        serieFavoritaRepository.save(serieFavorita);
    }

    public TmdbListaGeneros buscarGeneros() {
        return apiClient.generosSeries();
    }

    public Page<TmdbSerie> buscarSeriesPorGenero(Integer genreId, Integer page) {
        return apiClient.buscarSeriesPorGenero(genreId, page);
    }

    public TmdbSerie buscarDetalhesSerie(Long id) {
        return  apiClient.buscarDetalhesSerie(id);
    }


}

package com.test.cine_stream_test.service;

import com.test.cine_stream_test.tmdbapi.ApiClient;
import com.test.cine_stream_test.tmdbapi.dto.response.Page;
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
    private final ApiClient tmdbClient;
    private final UsuarioService usuarioService;

    public SerieService(SerieFavoritaRepository serieFavoritaRepository, ApiClient tmdbClient, UsuarioService usuarioService) {
        this.serieFavoritaRepository = serieFavoritaRepository;
        this.tmdbClient = tmdbClient;
        this.usuarioService = usuarioService;
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
}

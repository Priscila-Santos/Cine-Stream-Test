package com.test.cine_stream_test.service;

import com.test.cine_stream_test.tmdbapi.ApiClient;
import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbGenero;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbListaGeneros;
import com.test.cine_stream_test.dto.mapping.FilmeFavoritoMapper;
import com.test.cine_stream_test.dto.request.FilmeFavoritoRequest;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.model.FilmeFavorito;
import com.test.cine_stream_test.model.Usuario;
import com.test.cine_stream_test.repository.FilmeFavoritoRepository;
import org.springframework.stereotype.Service;


@Service
public class FilmeService {
    private final FilmeFavoritoRepository filmeFavoritoRepository;
    private final UsuarioService usuarioService;
    private final ApiClient tmdbClient;
    private final ApiClient apiClient;

    public FilmeService(FilmeFavoritoRepository filmeFavoritoRepository, UsuarioService usuarioService, ApiClient tmdbClient, ApiClient apiClient) {
        this.filmeFavoritoRepository = filmeFavoritoRepository;
        this.usuarioService = usuarioService;
        this.tmdbClient = tmdbClient;
        this.apiClient = apiClient;
    }

    public Page<TmdbFilme> buscarTodosFilmes(Integer page) {
        return apiClient.buscarTodosFilmes(page);
    }

    public Page<TmdbFilme> buscarFilmePorTitulo(String titulo, Integer page) {
        return tmdbClient.buscarFilmesPorTitulo(titulo, page);
    }

    public void adicionarFilmeFavorito(FilmeFavoritoRequest filmeFavoritoRequest) throws NotFoundException {
        Usuario usuario = usuarioService.buscarPorId(filmeFavoritoRequest.getIdUsuario());

        FilmeFavoritoMapper mapper = new FilmeFavoritoMapper();
        FilmeFavorito filmeFavorito = mapper.toEntity(filmeFavoritoRequest, usuario);

        filmeFavoritoRepository.save(filmeFavorito);
    }

    public TmdbListaGeneros buscarGeneros() {
        return apiClient.generosFilmes();
    }

    public Page<TmdbFilme> buscarFilmesPorGenero(Integer genreId, Integer page) { return apiClient.buscarFilmesPorGenero(genreId, page);}

}

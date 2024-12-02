package com.test.cine_stream_test.service;

import com.test.cine_stream_test.dto.response.FilmeFavoritoResponse;
import com.test.cine_stream_test.dto.response.SerieFavoritaResponse;
import com.test.cine_stream_test.tmdbapi.ApiClient;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;
import com.test.cine_stream_test.dto.mapping.UsuarioMapper;
import com.test.cine_stream_test.dto.request.UsuarioRequest;
import com.test.cine_stream_test.dto.response.UsuarioResponse;
import com.test.cine_stream_test.exception.AlreadyExistsException;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.model.Usuario;
import com.test.cine_stream_test.repository.FilmeFavoritoRepository;
import com.test.cine_stream_test.repository.SerieFavoritaRepository;
import com.test.cine_stream_test.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final ApiClient apiClient;
    private final FilmeFavoritoRepository filmeFavoritoRepository;
    private final SerieFavoritaRepository serieFavoritaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, ApiClient apiClient, FilmeFavoritoRepository filmeFavoritoRepository, SerieFavoritaRepository serieFavoritaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.apiClient = apiClient;
        this.filmeFavoritoRepository = filmeFavoritoRepository;
        this.serieFavoritaRepository = serieFavoritaRepository;
        this.usuarioMapper = new UsuarioMapper();
    }

    public UsuarioResponse criar(UsuarioRequest usuarioRequest) throws AlreadyExistsException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(usuarioRequest.getEmail());
        if (optionalUsuario.isPresent()) {
            throw new AlreadyExistsException(String.format(
                    "E-mail '%s' já cadastrado",
                    usuarioRequest.getEmail()
            ));
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioRequest);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(usuarioSalvo, Collections.emptyList(), Collections.emptyList());
    }

    public UsuarioResponse buscarPorEmail(String email) throws NotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Email %s não encontrado", email
                )));

        List<TmdbFilme> filmesFavoritos = filmeFavoritoRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(filmeFavorito -> apiClient.buscarDetalhesFilme(filmeFavorito.getTmdbId()))
                .toList();

        List<TmdbSerie> seriesFavoritas = serieFavoritaRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(serieFavorita -> apiClient.buscarDetalhesSerie(serieFavorita.getTmdbId()))
                .toList();

        return usuarioMapper.toDTO(usuario, filmesFavoritos, seriesFavoritas);
    }

    public Usuario buscarPorId(Long id) throws NotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Id %s não encontrado", id
                )));
    }

    public List<FilmeFavoritoResponse> buscarFilmesFavoritos(Long id) throws NotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        return usuario.getFilmesFavoritos().stream()
                .map(filmeFavorito -> new FilmeFavoritoResponse(filmeFavorito.getId(), filmeFavorito.getTmdbId(), filmeFavorito.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<SerieFavoritaResponse> buscarSeriesFavoritas(Long id) throws NotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        return usuario.getSeriesFavoritas().stream() .map(serieFavorita -> new SerieFavoritaResponse(serieFavorita.getSerieId(),
                serieFavorita.getTmdbId(), serieFavorita.getTitulo())) .collect(Collectors.toList());
    }


}

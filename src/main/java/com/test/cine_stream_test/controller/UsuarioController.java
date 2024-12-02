package com.test.cine_stream_test.controller;

import com.test.cine_stream_test.dto.request.UsuarioRequest;
import com.test.cine_stream_test.dto.response.FilmeFavoritoResponse;
import com.test.cine_stream_test.dto.response.SerieFavoritaResponse;
import com.test.cine_stream_test.dto.response.UsuarioResponse;
import com.test.cine_stream_test.exception.AlreadyExistsException;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@Validated @RequestBody UsuarioRequest usuario) throws AlreadyExistsException {
        UsuarioResponse novoUsuario =  usuarioService.criar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorEmail(@Validated @RequestParam String email) throws NotFoundException {
        UsuarioResponse emailUsuario = usuarioService.buscarPorEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(emailUsuario);
    }

    @GetMapping("/{id}/filmes-favoritos")
    public ResponseEntity<List<FilmeFavoritoResponse>> getFilmesFavoritos(@PathVariable Long id) throws NotFoundException {
        List<FilmeFavoritoResponse> filmeFavorito = usuarioService.buscarFilmesFavoritos(id);
        return ResponseEntity.ok(filmeFavorito);
    }

    @GetMapping("/{id}/series-favoritos")
    public ResponseEntity<List<SerieFavoritaResponse>> getSeriesFavoritas(@PathVariable Long id) throws NotFoundException {
        List<SerieFavoritaResponse> serieFavorita = usuarioService.buscarSeriesFavoritas(id);
        return ResponseEntity.ok(serieFavorita);
    }
}

package com.test.cine_stream_test.controller;

import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbListaGeneros;
import com.test.cine_stream_test.dto.request.FilmeFavoritoRequest;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping("/all-filmes")
    public ResponseEntity<Page<TmdbFilme>> getTodosFilme(
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Page<TmdbFilme> filmes = filmeService.buscarTodosFilmes(page);
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/genres-filmes")
    public ResponseEntity<TmdbListaGeneros> getGenres() {
        TmdbListaGeneros generos = filmeService.buscarGeneros();
        return ResponseEntity.ok(generos);
    }

    @GetMapping
    public ResponseEntity<Page<TmdbFilme>> buscarPorTitulo(
            @RequestParam String titulo,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Page<TmdbFilme> filmes = filmeService.buscarFilmePorTitulo(titulo, page);
        return ResponseEntity.ok(filmes);
    }

    @PostMapping("/favorito")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarFilmeFavorito(
            @RequestBody FilmeFavoritoRequest filmeFavoritoRequest
            ) throws NotFoundException {
        filmeService.adicionarFilmeFavorito(filmeFavoritoRequest);
    }
}

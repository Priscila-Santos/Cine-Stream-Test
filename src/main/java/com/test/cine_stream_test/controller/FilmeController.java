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
@RequestMapping("/api")
public class FilmeController {
    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping("/filmes/todos")
    public Page<TmdbFilme> getTodosFilme(
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return filmeService.buscarTodosFilmes(page);
    }

    @GetMapping("/genres")
    public ResponseEntity<TmdbListaGeneros> getGenres() {
        TmdbListaGeneros generos = filmeService.buscarGeneros();
        return ResponseEntity.ok(generos);
    }

    @GetMapping
    public Page<TmdbFilme> buscarPorTitulo(
            @RequestParam String titulo,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return filmeService.buscarFilmePorTitulo(titulo, page);
    }

    @PostMapping("/favorito")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarFilmeFavorito(
            @RequestBody FilmeFavoritoRequest filmeFavoritoRequest
            ) throws NotFoundException {
        filmeService.adicionarFilmeFavorito(filmeFavoritoRequest);
    }
}

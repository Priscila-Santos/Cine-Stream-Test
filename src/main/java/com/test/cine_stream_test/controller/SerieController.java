package com.test.cine_stream_test.controller;

import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbListaGeneros;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;
import com.test.cine_stream_test.dto.request.SerieFavoritaRequest;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
public class SerieController {
    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/all-series")
    public ResponseEntity<Page<TmdbSerie>> getTodasSeries(
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Page<TmdbSerie> series = serieService.buscarTodasSeries(page);
        return ResponseEntity.ok(series);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TmdbSerie> getSerieById(@PathVariable Long id) {
        TmdbSerie serie = serieService.buscarDetalhesSerie(id);
        return ResponseEntity.ok(serie);
    }

    @GetMapping("/genres-series")
    public ResponseEntity<TmdbListaGeneros> getGenres() {
        TmdbListaGeneros generos = serieService.buscarGeneros();
        return ResponseEntity.ok(generos);
    }

    @GetMapping
    public ResponseEntity<Page<TmdbSerie>> buscarPorTitulo(
            @RequestParam String titulo,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Page<TmdbSerie> series = serieService.buscarSeriePorTitulo(titulo, page);
        return ResponseEntity.ok(series);
    }

    @GetMapping("/por-genero")
    public ResponseEntity<Page<TmdbSerie>> getSeriesPorGenero(
            @RequestParam Integer genreId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Page<TmdbSerie> series = serieService.buscarSeriesPorGenero(genreId, page);
        return ResponseEntity.ok(series);
    }

    @PostMapping("/favorita")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarSerieFavorita(
            @RequestBody SerieFavoritaRequest serieFavoritaRequest
    ) throws NotFoundException {
        serieService.adicionarSerieFavorita(serieFavoritaRequest);
    }
}

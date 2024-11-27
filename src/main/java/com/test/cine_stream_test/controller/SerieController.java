package com.test.cine_stream_test.controller;

import com.test.cine_stream_test.tmdbapi.dto.response.Page;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;
import com.test.cine_stream_test.dto.request.SerieFavoritaRequest;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
public class SerieController {
    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/todos")
    public Page<TmdbSerie> getTodasSeries(
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return serieService.buscarTodasSeries(page);
    }

    @GetMapping
    public Page<TmdbSerie> buscarPorTitulo(
            @RequestParam String titulo,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return serieService.buscarSeriePorTitulo(titulo, page);
    }

    @PostMapping("/favorita")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarSerieFavorita(
            @RequestBody SerieFavoritaRequest serieFavoritaRequest
            ) throws NotFoundException {
        serieService.adicionarSerieFavorita(serieFavoritaRequest);
    }

}

package com.test.cine_stream_test.dto.response;

public class FilmeFavoritoResponse {
    private Long id;
    private Long tmdbId;
    private String titulo;

    public FilmeFavoritoResponse(Long id, Long tmdbId, String titulo) {
        this.id = id;
        this.tmdbId = tmdbId;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}


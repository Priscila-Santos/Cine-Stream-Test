package com.test.cine_stream_test.model;

import jakarta.persistence.*;

@Entity
public class SerieFavorita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serieId;
    private Long tmdbId;

    @ManyToOne(optional = false)
    private Usuario usuario;

    public SerieFavorita() {}

    public Long getSerieId() {
        return serieId;
    }

    public void setSerieId(Long id) {
        this.serieId = id;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

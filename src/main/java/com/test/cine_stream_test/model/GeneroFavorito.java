package com.test.cine_stream_test.model;

import jakarta.persistence.*;

@Entity
public class GeneroFavorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tmdbId;
    private TipoGenero tipo;

    @ManyToOne(optional = false)
    private Usuario usuario;

    public enum TipoGenero {
        SERIE,
        FILME
    }


    public GeneroFavorito() {}

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

    public TipoGenero getTipo() {
        return tipo;
    }

    public void setTipo(TipoGenero tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

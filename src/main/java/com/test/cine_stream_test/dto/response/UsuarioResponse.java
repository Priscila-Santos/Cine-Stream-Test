package com.test.cine_stream_test.dto.response;

import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;

import java.util.List;

public class UsuarioResponse {
    private Long id;
    private String nome;
    private String nickname;
    private String email;
    private List<TmdbFilme> filmesFavoritos;
    private List<TmdbSerie> seriesFavoritas;

    public UsuarioResponse () {
    }

    public UsuarioResponse(Long id, String nome, String nickname, String email, List<TmdbFilme> filmesFavoritos, List<TmdbSerie> seriesFavoritas) {
        this.id = id;
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.filmesFavoritos = filmesFavoritos;
        this.seriesFavoritas = seriesFavoritas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TmdbFilme> getFilmesFavoritos() {
        return filmesFavoritos;
    }

    public void setFilmesFavoritos(List<TmdbFilme> filmesFavoritos) {
        this.filmesFavoritos = filmesFavoritos;
    }

    public List<TmdbSerie> getSeriesFavoritas() {
        return seriesFavoritas;
    }

    public void setSeriesFavoritas(List<TmdbSerie> seriesFavoritas) {
        this.seriesFavoritas = seriesFavoritas;
    }
}

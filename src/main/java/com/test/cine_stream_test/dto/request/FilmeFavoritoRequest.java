package com.test.cine_stream_test.dto.request;

public class FilmeFavoritoRequest {
    private Long idUsuario;
    private Long idFilme;

    public FilmeFavoritoRequest () {}

    public FilmeFavoritoRequest(Long idUsuario, Long idFilme) {
        this.idUsuario = idUsuario;
        this.idFilme = idFilme;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }
}

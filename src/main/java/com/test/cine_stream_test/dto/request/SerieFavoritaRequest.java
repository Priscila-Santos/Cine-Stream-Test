package com.test.cine_stream_test.dto.request;

public class SerieFavoritaRequest {
    private Long idUsuario;
    private Long idSerie;

    public SerieFavoritaRequest () {}

    public SerieFavoritaRequest(Long idUsuario, Long idSerie) {
        this.idUsuario = idUsuario;
        this.idSerie = idSerie;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Long idSerie) {
        this.idSerie = idSerie;
    }
}

package com.test.cine_stream_test.dto.request;

public class FilmeRequest {
    private String titulo;

    public FilmeRequest() {}

    public FilmeRequest(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

package com.test.cine_stream_test.tmdbapi.dto.response;

public class TmdbGenero {
    private Long id;
    private String name;

    public TmdbGenero() {}

    public TmdbGenero(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

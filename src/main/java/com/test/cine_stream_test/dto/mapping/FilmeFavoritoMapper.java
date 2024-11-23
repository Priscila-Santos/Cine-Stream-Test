package com.test.cine_stream_test.dto.mapping;

import com.test.cine_stream_test.dto.request.FilmeFavoritoRequest;
import com.test.cine_stream_test.model.FilmeFavorito;
import com.test.cine_stream_test.model.Usuario;

public class FilmeFavoritoMapper {

    public FilmeFavorito toEntity(FilmeFavoritoRequest dto, Usuario usuario) {
        FilmeFavorito filmeFavorito = new FilmeFavorito();
        filmeFavorito.setTmdbId(dto.getIdFilme());
        filmeFavorito.setUsuario(usuario);
        return filmeFavorito;
    }
}

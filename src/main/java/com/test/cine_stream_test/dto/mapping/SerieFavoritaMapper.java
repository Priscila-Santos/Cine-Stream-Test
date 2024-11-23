package com.test.cine_stream_test.dto.mapping;

import com.test.cine_stream_test.dto.request.SerieFavoritaRequest;
import com.test.cine_stream_test.model.SerieFavorita;
import com.test.cine_stream_test.model.Usuario;

public class SerieFavoritaMapper {

    public SerieFavorita toEntity(SerieFavoritaRequest dto, Usuario usuario) {
        SerieFavorita serieFavorita = new SerieFavorita();
        serieFavorita.setTmdbId(dto.getIdSerie());
        serieFavorita.setUsuario(usuario);
        return serieFavorita;
    }
}

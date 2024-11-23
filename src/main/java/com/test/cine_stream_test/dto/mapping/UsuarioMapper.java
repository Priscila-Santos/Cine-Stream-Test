package com.test.cine_stream_test.dto.mapping;

import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;
import com.test.cine_stream_test.dto.request.UsuarioRequest;
import com.test.cine_stream_test.dto.response.UsuarioResponse;
import com.test.cine_stream_test.model.Usuario;

import java.util.List;


public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setNickName(dto.getNickname());
        usuario.setEmail(dto.getEmail());
        return usuario;
    }

    public UsuarioResponse toDTO(Usuario usuario, List<TmdbFilme> filmesFavoritos, List<TmdbSerie> seriesFavoritas) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setNickname(usuario.getNickName());
        dto.setEmail(usuario.getEmail());

        dto.setFilmesFavoritos(filmesFavoritos);
        dto.setSeriesFavoritas(seriesFavoritas);

        return dto;
    }
}

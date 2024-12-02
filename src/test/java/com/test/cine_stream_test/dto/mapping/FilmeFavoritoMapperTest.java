package com.test.cine_stream_test.dto.mapping;

import com.test.cine_stream_test.dto.request.FilmeFavoritoRequest;
import com.test.cine_stream_test.model.FilmeFavorito;
import com.test.cine_stream_test.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FilmeFavoritoMapperTest {

    @Test
    public void testToEntity() {
        // Dado
        FilmeFavoritoMapper mapper = new FilmeFavoritoMapper();
        FilmeFavoritoRequest request = new FilmeFavoritoRequest();
        request.setIdFilme(12345L);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Usuario");

        // Quando
        FilmeFavorito filmeFavorito = mapper.toEntity(request, usuario);

        // Então
        assertNotNull(filmeFavorito);
        assertEquals(12345L, filmeFavorito.getTmdbId());
        assertNotNull(filmeFavorito.getUsuario());
        assertEquals(1L, filmeFavorito.getUsuario().getId());
        assertEquals("Usuario", filmeFavorito.getUsuario().getNome());
    }
}


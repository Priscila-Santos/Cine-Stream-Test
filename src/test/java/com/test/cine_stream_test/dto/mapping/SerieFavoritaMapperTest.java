package com.test.cine_stream_test.dto.mapping;

import com.test.cine_stream_test.dto.request.SerieFavoritaRequest;
import com.test.cine_stream_test.model.SerieFavorita;
import com.test.cine_stream_test.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SerieFavoritaMapperTest {

    @Test
    public void testToEntity() {
        // Arrange
        SerieFavoritaMapper mapper = new SerieFavoritaMapper();
        SerieFavoritaRequest request = new SerieFavoritaRequest();
        request.setIdSerie(67890L);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Jane Doe");

        // Act
        SerieFavorita serieFavorita = mapper.toEntity(request, usuario);

        // Assert
        assertNotNull(serieFavorita);
        assertEquals(67890L, serieFavorita.getTmdbId());
        assertNotNull(serieFavorita.getUsuario());
        assertEquals(1L, serieFavorita.getUsuario().getId());
        assertEquals("Jane Doe", serieFavorita.getUsuario().getNome());
    }
}

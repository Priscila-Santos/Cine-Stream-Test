package com.test.cine_stream_test.dto.mapping;

import com.test.cine_stream_test.dto.request.UsuarioRequest;
import com.test.cine_stream_test.dto.response.UsuarioResponse;
import com.test.cine_stream_test.model.Usuario;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbFilme;
import com.test.cine_stream_test.tmdbapi.dto.response.TmdbSerie;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsuarioMapperTest {

    @Test
    public void testToEntity() {
        // dado
        UsuarioMapper mapper = new UsuarioMapper();
        UsuarioRequest request = new UsuarioRequest();
        request.setNome("John Doe");
        request.setNickname("jdoe");
        request.setEmail("john.doe@example.com");

        // quando
        Usuario usuario = mapper.toEntity(request);

        // entao
        assertNotNull(usuario);
        assertEquals("John Doe", usuario.getNome());
        assertEquals("jdoe", usuario.getNickName());
        assertEquals("john.doe@example.com", usuario.getEmail());
    }

    @Test
    public void testToDTO() {
        UsuarioMapper mapper = new UsuarioMapper();
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Priscila  Santos");
        usuario.setNickName("pri");
        usuario.setEmail("priscila@example.com");

        TmdbFilme filme = new TmdbFilme("Inception", "A mind-bending thriller", "2010-07-16", 8.8, "/posterPath.jpg", 100L);
        List<TmdbFilme> filmesFavoritos = Collections.singletonList(filme);

        TmdbSerie serie = new TmdbSerie(Collections.singletonList(18), 200, "Breaking Bad", "A high school chemistry teacher turned methamphetamine producer", "2008-01-20", 9.5, 1256, "/posterPath.jpg");        List<TmdbSerie> seriesFavoritas = Collections.singletonList(serie);

        // dado
        UsuarioResponse dto = mapper.toDTO(usuario, filmesFavoritos, seriesFavoritas);

        // quando
        assertNotNull(dto);
        assertEquals(1L, dto.getId().longValue());
        assertEquals("Priscila  Santos", dto.getNome());
        assertEquals("pri", dto.getNickname());
        assertEquals("priscila@example.com", dto.getEmail());
        assertNotNull(dto.getFilmesFavoritos());
        assertEquals(1, dto.getFilmesFavoritos().size());
        assertEquals(100L, dto.getFilmesFavoritos().get(0).getId().longValue());
        assertEquals("Inception", dto.getFilmesFavoritos().get(0).getTitle());
        assertNotNull(dto.getSeriesFavoritas());
        assertEquals(1, dto.getSeriesFavoritas().size());
        assertEquals(200L, dto.getSeriesFavoritas().get(0).getId().longValue());
        assertEquals("Breaking Bad", dto.getSeriesFavoritas().get(0).getName());
    }
}

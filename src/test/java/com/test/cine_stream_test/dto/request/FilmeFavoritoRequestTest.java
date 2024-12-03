package com.test.cine_stream_test.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FilmeFavoritoRequestTest {

    @Test
    public void testDefaultConstructor() {
        // dado
        FilmeFavoritoRequest request = new FilmeFavoritoRequest();

        // entao
        assertNotNull(request);
    }

    @Test
    public void testParameterizedConstructor() {
        // dado
        Long idUsuario = 1L;
        Long idFilme = 100L;

        // quando
        FilmeFavoritoRequest request = new FilmeFavoritoRequest(idUsuario, idFilme);

        // entao
        assertNotNull(request);
        assertEquals(idUsuario, request.getIdUsuario());
        assertEquals(idFilme, request.getIdFilme());
    }

    @Test
    public void testGettersAndSetters() {
        // dado
        FilmeFavoritoRequest request = new FilmeFavoritoRequest();
        Long idUsuario = 1L;
        Long idFilme = 100L;

        // quando
        request.setIdUsuario(idUsuario);
        request.setIdFilme(idFilme);

        // entao
        assertEquals(idUsuario, request.getIdUsuario());
        assertEquals(idFilme, request.getIdFilme());
    }
}


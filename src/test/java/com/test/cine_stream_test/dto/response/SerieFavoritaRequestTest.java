package com.test.cine_stream_test.dto.response;

import com.test.cine_stream_test.dto.request.SerieFavoritaRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SerieFavoritaRequestTest {

    @Test
    public void testDefaultConstructor() {
        SerieFavoritaRequest request = new SerieFavoritaRequest();

        assertNotNull(request);
    }

    @Test
    public void testParameterizedConstructor() {
        Long idUsuario = 1L;
        Long idSerie = 67890L;


        SerieFavoritaRequest request = new SerieFavoritaRequest(idUsuario, idSerie);

        assertNotNull(request);
        assertEquals(idUsuario, request.getIdUsuario());
        assertEquals(idSerie, request.getIdSerie());
    }

    @Test
    public void testGettersAndSetters() {
        SerieFavoritaRequest request = new SerieFavoritaRequest();
        Long idUsuario = 1L;
        Long idSerie = 67890L;

        request.setIdUsuario(idUsuario);
        request.setIdSerie(idSerie);

        assertEquals(idUsuario, request.getIdUsuario());
        assertEquals(idSerie, request.getIdSerie());
    }
}


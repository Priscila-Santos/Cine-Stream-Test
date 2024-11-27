package com.test.cine_stream_test.service;

import com.test.cine_stream_test.dto.request.UsuarioRequest;
import com.test.cine_stream_test.dto.response.UsuarioResponse;
import com.test.cine_stream_test.exception.AlreadyExistsException;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.model.Usuario;
import com.test.cine_stream_test.repository.FilmeFavoritoRepository;
import com.test.cine_stream_test.repository.SerieFavoritaRepository;
import com.test.cine_stream_test.repository.UsuarioRepository;
import com.test.cine_stream_test.tmdbapi.ApiClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private FilmeFavoritoRepository filmeFavoritoRepository;

    @Mock
    private SerieFavoritaRepository serieFavoritaRepository;

    @Mock
    private ApiClient apiClient;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarUsuario_deveSalvarUsuario() throws AlreadyExistsException {
        //dado
        UsuarioRequest request = new UsuarioRequest();
        request.setEmail("exemplo@email.com");
        when(usuarioRepository.findByEmail("exemplo@email.com")).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario());

        // quando
        UsuarioResponse response = usuarioService.criar(request);

        //então
        Assertions.assertNotNull(response);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));

    }

    @Test
    void criarUsuarioExistente_deveLancarAlreadyExistsException() {
        //dado
        UsuarioRequest request = new UsuarioRequest();
        request.setEmail("exemplo@email.com");
        when(usuarioRepository.findByEmail("exemplo@email.com")).thenReturn(Optional.of(new Usuario()));

        //então
        Assertions.assertThrows(AlreadyExistsException.class, () -> usuarioService.criar(request));

    }

    @Test
    void buscarEmail_deveRetornarUsuarioResponse() throws NotFoundException {
        //dado
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("exemplo@email.com");
        when(usuarioRepository.findByEmail("exemplo@email.com")).thenReturn(Optional.of(usuario));
        when(filmeFavoritoRepository.findByUsuarioId(1L)).thenReturn(List.of());
        when(serieFavoritaRepository.findByUsuarioId(1L)).thenReturn(List.of());


        // quando
        UsuarioResponse response = usuarioService.buscarPorEmail("exemplo@email.com");

        //então
        Assertions.assertNotNull(response);
        Assertions.assertEquals("exemplo@email.com", response.getEmail());
    }

    @Test
    void buscarPorEmailInexistente_deveLancarNotFoundException() {
        //dado
        when(usuarioRepository.findByEmail("exemplo@email.com")).thenReturn(Optional.empty());

        //então
        Assertions.assertThrows(NotFoundException.class, () -> usuarioService.buscarPorEmail("exemplo@email.com"));

    }

    @Test
    void buscarPorId_deveRetornarUsuario() throws NotFoundException {
        //dado
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // quando
        Usuario result = usuarioService.buscarPorId(1L);

        //então
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    void buscarPorId_deveLancarNotFoundException() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> usuarioService.buscarPorId(1L));
    }
}

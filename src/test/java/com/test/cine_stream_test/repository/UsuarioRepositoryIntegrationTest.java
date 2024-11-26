package com.test.cine_stream_test.repository;

import com.test.cine_stream_test.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setNome("Priscila");
        usuario.setEmail("priscila@email.com");

        usuarioRepository.save(usuario);
    }

    @AfterEach
    public void destroy() {
        usuarioRepository.deleteAll();
    }

    @Test
    public void usuario_jaCadastrado_deveTerId() {
        Assertions.assertNotNull(usuario.getId());
    }

    @Test
    public void usuarioJaCadastrado_pesquisoPorEmail_deveRetornar() {
        Optional<Usuario> foundUsuario = usuarioRepository.findByEmail("priscila@email.com");
        Assertions.assertTrue(foundUsuario.isPresent());
        Assertions.assertEquals(usuario.getId(), foundUsuario.get().getId());
    }

    @Test
    public void usuario_NaoCadastrado_pesquisoPorEmail_deveRetornarVazio() {
        Optional<Usuario> foundUsuario = usuarioRepository.findByEmail("naoexiste@email.com");
        Assertions.assertTrue(foundUsuario.isEmpty());
    }

    @Test
    public void usuarioJaCadastrado_pesquisoPorId_deveRetornar() {
        Optional<Usuario> foundUsuario = usuarioRepository.findById(usuario.getId());
        Assertions.assertTrue(foundUsuario.isPresent());
        Assertions.assertEquals(usuario.getId(), foundUsuario.get().getId());
    }

    @Test
    public void usuario_NaoCadastrado_pesquisoPorId_deveRetornarVazio() {
        Optional<Usuario> foundUsuario = usuarioRepository.findById(999L);
        Assertions.assertTrue(foundUsuario.isEmpty());
    }
}

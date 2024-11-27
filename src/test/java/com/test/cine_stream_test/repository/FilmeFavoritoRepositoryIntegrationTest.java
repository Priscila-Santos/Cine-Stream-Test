package com.test.cine_stream_test.repository;

import com.test.cine_stream_test.model.FilmeFavorito;
import com.test.cine_stream_test.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class FilmeFavoritoRepositoryIntegrationTest {

     @Autowired
    private FilmeFavoritoRepository filmeFavoritoRepository;

     private FilmeFavorito filmeFavorito;

     private Usuario usuario;

     @Autowired
     private TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {
         usuario = new Usuario();
         usuario.setNome("Usuario");
         usuario.setEmail("usuario@email.com");
         usuario = testEntityManager.persistAndFlush(usuario);

         filmeFavorito = new FilmeFavorito();
         filmeFavorito.setUsuario(usuario);
         filmeFavorito.setTmdbId(101L);
         filmeFavorito = filmeFavoritoRepository.save(filmeFavorito);
    }

     @AfterEach
    public void destroy() {
         filmeFavoritoRepository.deleteAll();
    }

     @Test
    public void filmeFavorito_jaCadastrado_deveTerId() {
         Assertions.assertNotNull(filmeFavorito.getId());
    }



     @Test
    public void filmeFavoritoJaCadastrado_pesquisoPorUsuarioId_deveRetornarFilmesFavoritos() {
         List<FilmeFavorito> foundFilmes = filmeFavoritoRepository.findByUsuarioId(usuario.getId());

         Assertions.assertNotNull(foundFilmes);
         Assertions.assertFalse(foundFilmes.isEmpty());
         Assertions.assertEquals(filmeFavorito.getId(), foundFilmes.get(0).getId());
    }

     @Test
    public void filmeFavoritoNaoCadastrado_pesquisoPorUsuarioId_deveRetornarVazio() {
         List<FilmeFavorito> foundFilmes = filmeFavoritoRepository.findByUsuarioId(999L);

         Assertions.assertNotNull(foundFilmes); Assertions.assertTrue(foundFilmes.isEmpty());
    }

     @Test
    public void filmeFavoritoJaCadastrado_pesquisoPorId_deveRetornar() {
         Optional<FilmeFavorito> foundFilme = filmeFavoritoRepository.findById(filmeFavorito.getId());

         Assertions.assertTrue(foundFilme.isPresent());
         Assertions.assertEquals(filmeFavorito.getId(), foundFilme.get().getId());
    }

     @Test
    public void filmeFavoritoNaoCadastrado_pesquisoPorId_deveRetornarVazio() {
         Optional<FilmeFavorito> foundFilme = filmeFavoritoRepository.findById(999L);
         Assertions.assertTrue(foundFilme.isEmpty());
    }

}
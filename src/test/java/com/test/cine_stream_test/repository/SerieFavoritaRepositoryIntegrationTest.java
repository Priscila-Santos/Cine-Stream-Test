package com.test.cine_stream_test.repository;

import com.test.cine_stream_test.model.SerieFavorita;
import com.test.cine_stream_test.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class SerieFavoritaRepositoryIntegrationTest {

    @Autowired
    private SerieFavoritaRepository serieFavoritaRepository;

    private SerieFavorita serieFavorita;

    private Usuario usuario;
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setNome("Priscila");
        usuario.setEmail("priscila@email.com");
        usuario = testEntityManager.persistAndFlush(usuario);

        serieFavorita = new SerieFavorita();
        serieFavorita.setUsuario(usuario);
        serieFavorita.setTmdbId(101L);
        serieFavorita = serieFavoritaRepository.save(serieFavorita);
    }

    @AfterEach
    public void destroy() {
        serieFavoritaRepository.deleteAll();
    }

    @Test
    public void serieFavorita_jaCadastrada_deveTerId() {
        Assertions.assertNotNull(serieFavorita.getSerieId());
    }

    @Test
    public void serieFavorita_jaCadastrada_pesquisoPorUsuarioId_deveRetornarSerieFavorita() {
        List<SerieFavorita> foundSeries = serieFavoritaRepository.findByUsuarioId(usuario.getId());
        Assertions.assertNotNull(foundSeries);
        Assertions.assertFalse(foundSeries.isEmpty());
        Assertions.assertEquals(serieFavorita.getSerieId(), foundSeries.get(0).getSerieId());
    }

    @Test
    public void serieFavorita_naoCadastrada_pesquisoPorUsuarioId_deveRetornarSerieVazio() {
        List<SerieFavorita> foundSeries = serieFavoritaRepository.findByUsuarioId(999L);
        Assertions.assertNotNull(foundSeries);
        Assertions.assertTrue(foundSeries.isEmpty());
    }
}

package com.test.cine_stream_test.repository;

import com.test.cine_stream_test.model.FilmeFavorito;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FilmeFavoritoRepositoryIntegrationTest {

    @Autowired
    private FilmeFavoritoRepository filmeFavoritoRepository;

    @BeforeEach
    public void setUp() {
        filmeFavoritoRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        filmeFavoritoRepository.deleteAll();
    }

    @Test
    public void quandoSalvarFilmeFavorito_entaoDeveRetornarFilmeFavoritoSalvo() {
        // Dado
        FilmeFavorito filmeFavorito = new FilmeFavorito();
        filmeFavorito.setId(1L);
        filmeFavorito.setTmdbId(1L);

        // Quando
        filmeFavoritoRepository.save(filmeFavorito);

        // Então
        Optional<FilmeFavorito> foundFilmeFavorito = filmeFavoritoRepository.findById(filmeFavorito.getId());
        assertThat(foundFilmeFavorito.isPresent()).isTrue();
        assertThat(foundFilmeFavorito.get().getId()).isEqualTo(1L);
        assertThat(foundFilmeFavorito.get().getTmdbId()).isEqualTo(1L);
    }

    @Test
    public void quandoBuscarPorUsuarioId_entaoDeveRetornarFilmesFavoritos() {
        // Dado
        FilmeFavorito filmeFavorito1 = new FilmeFavorito();
        filmeFavorito1.setId(1L);
        filmeFavorito1.setTmdbId(1L);
        filmeFavoritoRepository.save(filmeFavorito1);

        FilmeFavorito filmeFavorito2 = new FilmeFavorito();
        filmeFavorito2.setId(1L);
        filmeFavorito2.setTmdbId(2L);
        filmeFavoritoRepository.save(filmeFavorito2);

        // Quando
        List<FilmeFavorito> foundFilmesFavoritos = filmeFavoritoRepository.findByUsuarioId(1L);

        // Então
        assertThat(foundFilmesFavoritos).hasSize(2);
        assertThat(foundFilmesFavoritos.get(0).getId()).isEqualTo(1L);
        assertThat(foundFilmesFavoritos.get(0).getTmdbId()).isEqualTo(1L);
        assertThat(foundFilmesFavoritos.get(1).getId()).isEqualTo(1L);
        assertThat(foundFilmesFavoritos.get(1).getTmdbId()).isEqualTo(2L);
    }

    @Test
    public void quandoDeletarFilmeFavorito_entaoFilmeFavoritoNaoDeveExistir() {
        // Dado
        FilmeFavorito filmeFavorito = new FilmeFavorito();
        filmeFavorito.setId(1L);
        filmeFavorito.setTmdbId(1L);
        filmeFavoritoRepository.save(filmeFavorito);

        // Quando
        filmeFavoritoRepository.delete(filmeFavorito);

        // Então
        Optional<FilmeFavorito> foundFilmeFavorito = filmeFavoritoRepository.findById(filmeFavorito.getId());
        assertThat(foundFilmeFavorito.isPresent()).isFalse();
    }
}

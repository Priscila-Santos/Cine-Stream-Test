package com.test.cine_stream_test.repository;

import com.test.cine_stream_test.model.FilmeFavorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeFavoritoRepository extends JpaRepository<FilmeFavorito, Long> {

    List<FilmeFavorito> findByUsuarioId(Long id);
}

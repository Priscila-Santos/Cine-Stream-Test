package com.test.cine_stream_test.repository;

import com.test.cine_stream_test.model.SerieFavorita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieFavoritaRepository extends JpaRepository<SerieFavorita, Long> {

    List<SerieFavorita> findByUsuarioId(Long id);
}

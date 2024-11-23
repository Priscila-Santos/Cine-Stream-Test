package com.test.cine_stream_test.repository;

import com.test.cine_stream_test.model.GeneroFavorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroFavoritoRepository extends JpaRepository<GeneroFavorito, Long> {
}

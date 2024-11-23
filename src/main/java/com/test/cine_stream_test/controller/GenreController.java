package com.test.cine_stream_test.controller;//package com.tech.ada.spring_cinestream.controller;
//
//import com.tech.ada.spring_cinestream.client.tmdbapi.dto.response.TmdbListaGeneros;
//import com.tech.ada.spring_cinestream.service.GenreService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("api/genres")
//public class GenreController {
//
//    @Autowired
//    private GenreService genreService;
//
//    @GetMapping
//    public ResponseEntity<TmdbListaGeneros> getGenres() {
//        TmdbListaGeneros generos = genreService.buscarGeneros();
//        return ResponseEntity.ok(generos);
//    }
//}

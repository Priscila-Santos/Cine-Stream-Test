package com.test.cine_stream_test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cine_stream_test.dto.request.UsuarioRequest;
import com.test.cine_stream_test.dto.response.FilmeFavoritoResponse;
import com.test.cine_stream_test.dto.response.SerieFavoritaResponse;
import com.test.cine_stream_test.dto.response.UsuarioResponse;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UsuarioControllerComponentTest {

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void criarUsuario_deveRetornarUsuarioCriado() throws Exception {
        // Dado
        var usuarioRequest = new UsuarioRequest();
        usuarioRequest.setEmail("teste@teste.com");
        usuarioRequest.setNome("Teste");

        var usuarioResponse = new UsuarioResponse();
        usuarioResponse.setEmail("teste@teste.com");
        usuarioResponse.setNome("Teste");

        when(usuarioService.criar(any(UsuarioRequest.class))).thenReturn(usuarioResponse);

        // Quando
        mockMvc.perform(
                        post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(usuarioRequest))
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("teste@teste.com"))
                .andExpect(jsonPath("$.nome").value("Teste"));

        // Verificar que o método criar foi chamado com os parâmetros corretos
        verify(usuarioService).criar(any(UsuarioRequest.class));
    }

    @Test
    public void buscarUsuarioPorEmail_deveRetornarUsuario() throws Exception {
        // Dado
        var usuarioResponse = new UsuarioResponse();
        usuarioResponse.setEmail("teste@teste.com");
        usuarioResponse.setNome("Teste");

        when(usuarioService.buscarPorEmail("teste@teste.com")).thenReturn(usuarioResponse);

        // Quando
        mockMvc.perform(
                        get("/usuarios")
                                .param("email", "teste@teste.com")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("teste@teste.com"))
                .andExpect(jsonPath("$.nome").value("Teste"));

        // Verificar que o método buscarPorEmail foi chamado com os parâmetros corretos
        verify(usuarioService).buscarPorEmail("teste@teste.com");
    }


    @Test
    public void getFilmesFavoritos_deveRetornarListaDeFilmes() throws Exception {
        // Dado
        List<FilmeFavoritoResponse> filmesFavoritos = Arrays.asList(
                new FilmeFavoritoResponse(1L, 1001L, "Filme 1" ),
                new FilmeFavoritoResponse(2L, 1002L,"Filme 2")
        );

        when(usuarioService.buscarFilmesFavoritos(1L)).thenReturn(filmesFavoritos);

        // Quando
        mockMvc.perform(
                        get("/usuarios/1/filmes-favoritos")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].titulo").value("Filme 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].titulo").value("Filme 2"));

        // Verificar que o método buscarFilmesFavoritos foi chamado com os parâmetros corretos
        verify(usuarioService).buscarFilmesFavoritos(1L);
    }

    @Test
    public void getSeriesFavoritas_deveRetornarListaDeSeries() throws Exception {
        // Dado
        List<SerieFavoritaResponse> seriesFavoritas = Arrays.asList(
                new SerieFavoritaResponse(1L,2001L , "Serie 1"),
                new SerieFavoritaResponse(2L, 2002L, "Serie 2")
        );

        when(usuarioService.buscarSeriesFavoritas(1L)).thenReturn(seriesFavoritas);

        // Quando
        mockMvc.perform(
                        get("/usuarios/1/series-favoritos")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].titulo").value("Serie 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].titulo").value("Serie 2"));

        // Verificar que o método buscarSeriesFavoritas foi chamado com os parâmetros corretos
        verify(usuarioService).buscarSeriesFavoritas(1L);
    }

    @Test
    public void buscarUsuarioPorEmail_usuarioNaoEncontrado_deveRetornarNotFound() throws Exception {
        // Dado
        when(usuarioService.buscarPorEmail("naoexiste@teste.com")).thenThrow(new NotFoundException("Usuário não encontrado"));

        // Quando
        mockMvc.perform(
                        get("/usuarios")
                                .param("email", "naoexiste@teste.com")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Usuário não encontrado"));

        // Verificar que o método buscarPorEmail foi chamado com os parâmetros corretos
        verify(usuarioService).buscarPorEmail("naoexiste@teste.com");
    }
}

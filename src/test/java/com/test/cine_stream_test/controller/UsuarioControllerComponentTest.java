package com.test.cine_stream_test.controller;

import com.test.cine_stream_test.dto.request.UsuarioRequest;
import com.test.cine_stream_test.dto.response.UsuarioResponse;
import com.test.cine_stream_test.exception.AlreadyExistsException;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UsuarioControllerComponentTest {

    @Mock
    private UsuarioService usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UsuarioController usuarioController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void criarUsuario_deveRetornarUsuarioCriado() throws Exception {
        // Dado
        var usuarioRequest = new UsuarioRequest();
        usuarioRequest.setEmail("teste@teste.com");
        usuarioRequest.setNome("Teste");

        var usuarioResponse = new UsuarioResponse();
        usuarioResponse.setEmail("teste@teste.com");
        usuarioResponse.setNome("Teste");

        Mockito.when(usuarioService.criar(usuarioRequest)).thenReturn(usuarioResponse);

        // Quando
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(usuarioRequest))
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("teste@teste.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Teste"));
    }

    @Test
    public void buscarUsuarioPorEmail_deveRetornarUsuario() throws Exception {
        // Dado
        var usuarioResponse = new UsuarioResponse();
        usuarioResponse.setEmail("teste@teste.com");
        usuarioResponse.setNome("Teste");

        Mockito.when(usuarioService.buscarPorEmail("teste@teste.com")).thenReturn(usuarioResponse);

        // Quando
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/usuarios")
                                .param("email", "teste@teste.com")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("teste@teste.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Teste"));
    }

    @Test
    public void criarUsuario_semInformarEmail_deveRetornarBadRequest() throws Exception {
        // Dado
        var usuarioRequest = new UsuarioRequest();
        usuarioRequest.setNome("Teste");

        // Quando
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(usuarioRequest))
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Então
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

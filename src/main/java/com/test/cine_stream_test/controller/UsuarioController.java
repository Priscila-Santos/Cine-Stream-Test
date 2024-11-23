package com.test.cine_stream_test.controller;

import com.test.cine_stream_test.dto.request.UsuarioRequest;
import com.test.cine_stream_test.dto.response.UsuarioResponse;
import com.test.cine_stream_test.exception.AlreadyExistsException;
import com.test.cine_stream_test.exception.NotFoundException;
import com.test.cine_stream_test.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody UsuarioRequest usuario) throws AlreadyExistsException {
        UsuarioResponse novoUsuario =  usuarioService.criar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorEmail(@RequestParam String email) throws NotFoundException {
        UsuarioResponse emailUsuario = usuarioService.buscarPorEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(emailUsuario);
    }
}

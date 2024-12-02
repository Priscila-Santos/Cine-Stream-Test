package com.test.cine_stream_test.dto.request;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.context.properties.bind.Name;

public class UsuarioRequest {

    @NotNull
    private String nome;

    private String nickname;

    @NotNull
    private String email;

    public UsuarioRequest() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

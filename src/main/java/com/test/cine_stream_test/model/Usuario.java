package com.test.cine_stream_test.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nickName;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<FilmeFavorito> filmesFavoritos;

    @OneToMany(mappedBy = "usuario")
    private List<SerieFavorita> seriesFavoritas;

    // Construtores, getters e setters
    public Usuario() {
    }

    public Usuario(Long id, String nome, String nickName, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.nickName = nickName;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<FilmeFavorito> getFilmesFavoritos() {
        return filmesFavoritos;
    }

    public void setFilmesFavoritos(List<FilmeFavorito> filmesFavoritos) {
        this.filmesFavoritos = filmesFavoritos;
    }

    public List<SerieFavorita> getSeriesFavoritas() {
        return seriesFavoritas;
    }

    public void setSeriesFavoritas(List<SerieFavorita> seriesFavoritas) {
        this.seriesFavoritas = seriesFavoritas;
    }
}

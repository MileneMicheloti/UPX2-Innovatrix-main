
package com.mycompany.doaki.modelo.dominio;

import java.util.Objects;

public class Usuario {
    private Long id;
    private String nome;
    private String senha;
    private String usuario;
    private Perfil perfil;
    private boolean estado;

    public Usuario() { // por padrão ao criar um usuário o estado é true
        this.estado = true;
    }

    public Usuario(Long id, String nome, String senha, String usuario, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.usuario = usuario;
        this.perfil = perfil;
        this.estado = true;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }

    // quando clicarmos no estado automaticamente ele recebe o true
    public void reset() {
        this.estado = true;
    }

    // ao clicar para mudar o estado, se ele estiver falso vai para verdadeiro e
    // vice versa
    public void mudarEstado() {
        this.estado = !this.estado;
    }

}

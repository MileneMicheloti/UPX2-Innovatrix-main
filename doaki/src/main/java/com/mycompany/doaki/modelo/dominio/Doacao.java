/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.doaki.modelo.dominio;

import java.time.LocalDateTime;

/**
 *
 * @author bre-r
 */
public class Doacao {
    private Long id;
    private Doador doador;
    private Usuario usuario;
    private LocalDateTime data_hora_doado;

    public Doacao() {
    }

    public Doacao(Long id, Doador doador, Usuario usuario, LocalDateTime data_hora_doado) {
        this.id = id;
        this.doador = doador;
        this.usuario = usuario;
        this.data_hora_doado = data_hora_doado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getData_hora_doado() {
        return data_hora_doado;
    }

    public void setData_hora_doado(LocalDateTime data_hora_doado) {
        this.data_hora_doado = data_hora_doado;
    }
    
    
}

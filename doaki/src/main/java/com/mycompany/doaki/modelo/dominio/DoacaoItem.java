/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.doaki.modelo.dominio;

/**
 *
 * @author bre-r
 */
public class DoacaoItem {

    private Doacao doacao; //no dele Venda venda creio que seja = venda_id
    private Item item; //no dele produto produto creio que seja igual o produto_id;
    private Integer quantidade;

    public DoacaoItem() {
    }

    public DoacaoItem(Doacao doacao, Item item, Integer quantidade) {
        this.doacao = doacao;
        this.item = item;
        this.quantidade = quantidade;
    }

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    
}

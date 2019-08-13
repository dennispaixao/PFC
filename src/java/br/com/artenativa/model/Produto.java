/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.model;

/**
 *
 * @author Dennis
 */
public class Produto {
    private int id;
    private String dataCadastro;
    private String nome;
    private String descricao;
    private double preco;
    private double peso;
    private String fornecedor;
    private double largura;
    private double altura;
    private double espessura;
    private float qtEstoque;
    private float estoqueRaz;//quantidade razoavel no estoque

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Produto() {
    }


    public Produto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getEspessura() {
        return espessura;
    }

    public void setEspessura(double espessura) {
        this.espessura = espessura;
    }

    public float getQtEstoque() {
        return qtEstoque;
    }

    public void setQtEstoque(float qtEstoque) {
        this.qtEstoque = qtEstoque;
    }

    public float getEstoqueRaz() {
        return estoqueRaz;
    }

    public void setEstoqueRaz(float estoqueRaz) {
        this.estoqueRaz = estoqueRaz;
    }
    
    
    
    
    
    
    
}

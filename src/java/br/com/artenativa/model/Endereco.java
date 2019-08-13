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
public class Endereco {
    private String pais;
    private String UF;
    private String cidade;
    private String cep;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento; 
    private String pontoReferencia; 

    public Endereco(String pais, String UF, String cidade, String cep, String bairro, String logradouro, String numeroResidencia, String complemento, String pontoReferencia) {
        this.pais = pais;
        this.UF = UF;
        this.cidade = cidade;
        this.cep = cep;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numeroResidencia;
        this.complemento = complemento;
        this.pontoReferencia = pontoReferencia;
    }

    public Endereco() {
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numeroResidencia) {
        this.numero= numeroResidencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }
    
    
}

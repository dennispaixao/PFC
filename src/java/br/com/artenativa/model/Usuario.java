/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.model;

import br.com.artenativa.AutorizacaoDeAcesso.PerfilDeAcesso;


public class Usuario {
    private int id;  
    private String nome;
    private String senha;
    private PerfilDeAcesso  nivel;

    public Usuario(String nome, String senha, PerfilDeAcesso nivel) {
        
        this.nome = nome;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario() {
    }

     public Usuario(int id) {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        
      this.senha=  senha;
        
        
    }

    public PerfilDeAcesso getNivel() {
        return nivel;
    }

    public void setNivel(PerfilDeAcesso nivel) {
        this.nivel = nivel;
    }



  
    
}

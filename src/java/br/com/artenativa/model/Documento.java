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
public class Documento {
    private int id;
    private String numero;
    
    
    public Documento(String numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String tipo;


    public Documento() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
}

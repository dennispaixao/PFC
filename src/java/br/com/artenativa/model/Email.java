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
public class Email {
    private String email;

    public Email(String email) {
        this.email = email;
    }

    public Email() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

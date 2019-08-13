/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.model.Pessoa;
import br.com.artenativa.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Dennis
 */
public interface UsuarioDAOInterface {
    
    public boolean inserir(Usuario u);
    public boolean alterar(Usuario u);
    public boolean excluir(Usuario u);
    public  ArrayList<Usuario> listar();
    public Usuario buscar(Usuario u);
    public Usuario autenticar(Usuario u);//retorno sem senha
    
    
}

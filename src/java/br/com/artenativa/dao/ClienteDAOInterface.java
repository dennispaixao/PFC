/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.model.Cliente;
import java.util.ArrayList;

/**
 *
 * @author Dennis
 */
public interface ClienteDAOInterface {
    
    public int selectCurr();
    public boolean inserir(Cliente c);
    public boolean alterar(Cliente c);
    public boolean excluir(Cliente c);
    public  ArrayList<Cliente> listar();
    public Cliente buscar(Cliente p);
    
    
}

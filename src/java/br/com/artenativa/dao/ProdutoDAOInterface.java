/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.model.Produto;
import java.util.ArrayList;

/**
 *
 * @author Dennis
 */
public interface ProdutoDAOInterface {
        
    public int selectCurr();
    public boolean inserir(Produto f);
    public boolean alterar(Produto f);
    public boolean excluir(Produto f);
    public  ArrayList<Produto> listar();
    public  ArrayList<Produto> listar(String nome);
    public Produto buscar(Produto f);
  
    
}

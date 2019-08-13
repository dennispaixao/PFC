/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.model.Fornecedor;
import java.util.ArrayList;

/**
 *
 * @author Dennis
 */
public interface FornecedorDAOInterface {
        
    public int selectCurr();
    public boolean inserir(Fornecedor f);
    public boolean alterar(Fornecedor f);
    public boolean excluir(Fornecedor f);
    public  ArrayList<Fornecedor> listar();
    public Fornecedor buscar(Fornecedor f);
    
}

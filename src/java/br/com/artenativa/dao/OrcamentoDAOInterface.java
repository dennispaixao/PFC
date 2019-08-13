/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template oile, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.model.Orcamento;
import java.util.ArrayList;

/**
 *
 * @author Dennis
 */
public interface OrcamentoDAOInterface {
        
    public int selectCurr();
    public int inserir(Orcamento o);
    public boolean alterar(Orcamento o);
    public boolean excluir(Orcamento o);
    public  ArrayList<Orcamento> listar();
    public  ArrayList<Orcamento> listar(int situacao);
    public Orcamento buscar(Orcamento o);
    
}

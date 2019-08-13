/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template iile, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.model.ItemOrcamento;
import java.util.ArrayList;

/**
 *
 * @author Dennis
 */
public interface ItemOrcamentoDAOInterface {
        
    public int selectCurr();
    public boolean inserir(ItemOrcamento i);
    public boolean alterar(ItemOrcamento i);
    public boolean excluir(ItemOrcamento i);
    public  ArrayList<ItemOrcamento> listar();
    public ItemOrcamento buscar(ItemOrcamento i);
    
}

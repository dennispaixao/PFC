/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.model.Funcionario;
import java.util.ArrayList;

/**
 *
 * @author Dennis
 */
public interface FuncionarioDAOInterface {
        
    public int selectCurr();
    public boolean inserir(Funcionario f);
    public boolean alterar(Funcionario f);
    public boolean excluir(Funcionario f);
    public  ArrayList<Funcionario> listar();
    public Funcionario buscar(Funcionario f);
    
}

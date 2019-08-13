/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.FuncionarioDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class ListarFuncionarioAction implements ICommand{
   
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
       
        FuncionarioDAO cdao = null;
        request.setAttribute("pageRedirect", "funcionarioListar.jsp");
        
        try {
   
         cdao = new FuncionarioDAO();
         
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListarFuncionarioAction.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("erro", "ex");
            
        }
       
        request.setAttribute("ListaFuncionarios", cdao.listar());
        
        
        return request;
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.FornecedorDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class ListarFornecedorAction implements ICommand{
   
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
       
        FornecedorDAO cdao = null;
        request.setAttribute("pageRedirect", "fornecedorListar.jsp");
        
        try {
   
         cdao = new FornecedorDAO();
         
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListarFornecedorAction.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("erro", "ex");
            
        }
       
        request.setAttribute("ListaFornecedores", cdao.listar());
        
        
        return request;
    } 
}

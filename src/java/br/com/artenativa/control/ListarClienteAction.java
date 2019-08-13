/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.ClienteDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class ListarClienteAction implements ICommand{
   
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
       
        ClienteDAO cdao = null;
        request.setAttribute("pageRedirect", "clienteListar.jsp");
        
        try {
   
            cdao = new ClienteDAO();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListarClienteAction.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("erro", "ex");
            
        }
       
        request.setAttribute("ListaCliente", cdao.listar());
        
        
        return request;
    } 
}

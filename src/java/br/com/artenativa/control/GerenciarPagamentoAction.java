/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.ClienteDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.Cliente;
import br.com.artenativa.model.Orcamento;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class GerenciarPagamentoAction implements ICommand{
    
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        
        try {
            Orcamento o = new Orcamento(id);
            o =  new OrcamentoDAO().buscar(o);
            
            request.setAttribute("orcamento", o );
            request.setAttribute("pageRedirect", "orcamentoGerenciarPagamento.jsp");

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GerenciarPagamentoAction.class.getName()).log(Level.SEVERE, null, ex);
     
        }
       
      
       
       return request;
    }
    
}

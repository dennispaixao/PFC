/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.FornecedorDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.Fornecedor;
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
public class LiberarParaProducaoAction implements ICommand{
    
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response){
        
       int id = Integer.parseInt(request.getParameter("id"));
        try {
            
            OrcamentoDAO odao = new OrcamentoDAO();
            Orcamento o = new Orcamento(id);
            o = odao.buscar(o);
            
            o.setEstado(2); //produçao
            odao.alterar(o);
            
            request.setAttribute("orcamento", o);
            
            request.setAttribute("pageRedirect", ".jsp"); 

            
        } catch (SQLException | ClassNotFoundException ex){
            Logger.getLogger(LiberarParaProducaoAction.class.getName()).log(Level.SEVERE, null, ex);
            
            request.setAttribute("pageRedirect", "fornecedorEditar.jsp"); 
            
        }
       


        return request;
    }
    
}

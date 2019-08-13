/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.ClienteDAO;
import br.com.artenativa.dao.ItemOrcamentoDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.Cliente;
import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */

public class VizualizarOrcamentoFinalizadoAction implements ICommand{
    
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response){
        
       int idOrc = Integer.parseInt(request.getParameter("id"));
       try {
            request.setAttribute("pageRedirect", "orcamentoVizualizarFinalizados.jsp");
            Orcamento o = new Orcamento(idOrc);
            OrcamentoDAO odao = new OrcamentoDAO();
            o = odao.buscar(o);
            ItemOrcamentoDAO idao = new ItemOrcamentoDAO();
            ArrayList<ItemOrcamento> itens = idao.listar(o);
            
            request.setAttribute("itens", itens);
            request.setAttribute("orcamento", o);

             } catch (SQLException | ClassNotFoundException ex) {
                 request.setAttribute("pageRedirect", "erro.jsp");  
             }
        
        return request;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.ajax;
import br.com.artenativa.dao.ItemOrcamentoDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class LiberarEntregaOrcamentoAjax extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              int idOrc = Integer.parseInt(request.getParameter("idOrc"));
              Orcamento o = new Orcamento(idOrc); 
              String a = "";
        try {
            
            OrcamentoDAO odao= new OrcamentoDAO();
            o = odao.buscar(o);
            o.setEstado(3);
            odao.alterar(o); 
            ItemOrcamentoDAO idao = new ItemOrcamentoDAO();
            ArrayList<ItemOrcamento> itens = idao.listar(o);
            for(ItemOrcamento i : itens){
               i.setStatus(3);
               idao.alterarItem(i);
               a += i.getStatus();
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LiberarEntregaOrcamentoAjax.class.getName()).log(Level.SEVERE, null, ex);
        }
              
              response.getWriter().write(a + "");     
              
    }

}

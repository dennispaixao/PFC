/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.ajax;

import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.Orcamento;
import java.io.IOException;
import java.sql.SQLException;
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
public class FinalizarOrcamentoAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException, ClassNotFoundException {
              response.setContentType("text/html;charset=UTF-8");
              int idOrc = Integer.parseInt(request.getParameter("idOrc"));
              String msg= "para finalizar o orçamento é preciso quitar o débito";
              try{
                  Orcamento o = new Orcamento(idOrc);
                  OrcamentoDAO odao = new OrcamentoDAO();
                  o = odao.buscar(o);           
                  if(o.getTotalPago() >= o.getValor()){
                      o.setEstado(4);
                      odao.alterar(o);
                      msg= "ok";
                  }
              }catch(ClassNotFoundException | SQLException e){}
              response.getWriter().write(msg);
   
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FinalizarOrcamentoAjax.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

}

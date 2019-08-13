/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.artenativa.ajax;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.model.mock.ItemOrcamentoMock;
import br.com.artenativa.util.ParseJson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InserirPagamentoAjax extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException, ClassNotFoundException {
             response.setContentType("text/html;charset=UTF-8");
             int idOrc = Integer.parseInt(request.getParameter("id")); //id do orçamento recebido como param
             double valor;
             try{
              valor= Double.parseDouble(request.getParameter("valor")); //quantia paga do orçamento recebido como param
             }catch(NumberFormatException e){
              valor = 0;    
             }
             
             //se valor + qtPaga > Total então qtPaga = Total;senão :  qtPaga = qtPaga + valo
             OrcamentoDAO odao = new OrcamentoDAO();
             Orcamento o = odao.buscar(new Orcamento(idOrc));

             double troco = 0;
             if(valor + o.getTotalPago() > o.getValor()){
                 troco = (valor + o.getTotalPago()) - o.getValor();
                 
                 o.setTotalPago(o.getValor());

             }else{
                 o.setTotalPago(o.getTotalPago()+valor);
             }

             odao.alterar(o);
             o = odao.buscar(o);
             
             response.getWriter().write(o.getTotalPago() +";"+ troco);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InserirPagamentoAjax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

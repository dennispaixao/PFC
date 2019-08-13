/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.ajax;
import br.com.artenativa.dao.ItemOrcamentoDAO;
import br.com.artenativa.model.ItemOrcamento;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterarStatusItemOrcamentoAjax extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
            int idItem =Integer.parseInt(request.getParameter("id"));
            try{
                ItemOrcamentoDAO idao = new ItemOrcamentoDAO();
                ItemOrcamento i = idao.buscar(new ItemOrcamento(idItem));
                switch (i.getStatus()) {
                    case 1:
                        i.setStatus(2);
                        break;
                    case 2:
                        i.setStatus(1);
                        break;
                    case 3:
                        i.setStatus(4);   
                        break;
                    case 4:
                        i.setStatus(3);
                        break;
                    default:
                        break;
                }
                      
                  idao.alterarItem(i);
                  response.getWriter().write(i.getStatus()+""); 
                  }catch(ClassNotFoundException | SQLException e){
                  response.getWriter().write(e.getMessage());
            }      
    }
}

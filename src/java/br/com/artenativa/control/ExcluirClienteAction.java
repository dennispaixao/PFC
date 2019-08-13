/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.ClienteDAO;
import br.com.artenativa.model.Cliente;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class ExcluirClienteAction implements ICommand{

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
       int id = Integer.parseInt(request.getParameter("id"));
       Cliente p = new Cliente(id);
       request.setAttribute("pageRedirect", "clienteListar.jsp");
       try {
       ClienteDAO cdao = new ClienteDAO();
       ClienteDAO pdao = new ClienteDAO();
       
            cdao.excluir(p);
            request.setAttribute("msg", "Cliente "+p.getId()+" excluído com sucesso!");
            request.setAttribute("ListaCliente", pdao.listar());
   
      
            
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("", "Não foi possível excluir o cliente, contate o administrador!");
            return request;
        }
        return request;
        
    }
    
}

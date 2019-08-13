/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.FornecedorDAO;
import br.com.artenativa.model.Fornecedor;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class AlterarFornecedorAction implements ICommand{
    
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response){
        
       int id = Integer.parseInt(request.getParameter("id"));
       Fornecedor p = new Fornecedor(id);
       request.setAttribute("pageRedirect", "fornecedorEditar.jsp");
       
       try {
          
       FornecedorDAO cdao = new FornecedorDAO();

       request.setAttribute("fornecedor", cdao.buscar(p));

        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("erro", "Não foi possível alterar o cliente, contate o administrador!");
            return request;
        }
        
        return request;
    }
    
}

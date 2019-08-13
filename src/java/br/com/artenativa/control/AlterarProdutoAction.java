/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.ProdutoDAO;
import br.com.artenativa.model.Produto;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class AlterarProdutoAction implements ICommand{
    
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response){
        
       int id = Integer.parseInt(request.getParameter("id"));
       Produto p = new Produto(id);
       request.setAttribute("pageRedirect", "produtoEditar.jsp");
       
       try {
          
       ProdutoDAO cdao = new ProdutoDAO();

       request.setAttribute("produto", cdao.buscar(p));

        } catch (SQLException | ClassNotFoundException ex){
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("erro", "Não foi possível alterar o produto, contate o administrador!");
            return request;
        }
       
        return request;
        
    }
    
}

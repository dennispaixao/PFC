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
public class ExcluirProdutoAction implements ICommand{

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
      
       int id = Integer.parseInt(request.getParameter("id"));
       Produto p = new Produto(id);
       request.setAttribute("pageRedirect", "produtoListar.jsp");
      
       try {
       ProdutoDAO cdao = new ProdutoDAO();
       ProdutoDAO pdao = new ProdutoDAO();
       
            cdao.excluir(p);
            request.setAttribute("msg", "Produto "+p.getId()+" excluído com sucesso!");
            request.setAttribute("ListaProdutos", pdao.listar());
   
      
            
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("", "Não foi possível excluir o Produto, contate o administrador!");
            return request;
        }
        return request;
        
    }
    
}

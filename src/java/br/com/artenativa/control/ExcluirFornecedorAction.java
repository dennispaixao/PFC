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
public class ExcluirFornecedorAction implements ICommand{

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
       int id = Integer.parseInt(request.getParameter("id"));
       Fornecedor p = new Fornecedor(id);
       request.setAttribute("pageRedirect", "fornecedorListar.jsp");
       try {
       FornecedorDAO cdao = new FornecedorDAO();
       FornecedorDAO pdao = new FornecedorDAO();
       
            cdao.excluir(p);
            request.setAttribute("msg", "Fornecedor "+p.getId()+" excluído com sucesso!");
            request.setAttribute("ListaFornecedores", pdao.listar());
   
      
            
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("", "Não foi possível excluir o cliente, contate o administrador!");
            return request;
        }
        return request;
        
    }
    
}

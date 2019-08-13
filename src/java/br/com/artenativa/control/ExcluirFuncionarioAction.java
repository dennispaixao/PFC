/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;


import br.com.artenativa.dao.FornecedorDAO;
import br.com.artenativa.dao.FuncionarioDAO;
import br.com.artenativa.model.Fornecedor;
import br.com.artenativa.model.Funcionario;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class ExcluirFuncionarioAction implements ICommand{

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
       int id = Integer.parseInt(request.getParameter("id"));
       Funcionario p = new Funcionario(id);
       request.setAttribute("pageRedirect", "funcionarioListar.jsp");
       try {
       FuncionarioDAO cdao = new FuncionarioDAO();
       FuncionarioDAO pdao = new FuncionarioDAO();
       
            cdao.excluir(p);
            request.setAttribute("msg", "Fornecedor "+p.getId()+" excluído com sucesso!");
            request.setAttribute("ListaFuncionarios", pdao.listar());
   
      
            
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("", "Não foi possível excluir o funcionario, contate o administrador!");
            return request;
        }
        return request;
        
    }
    
}

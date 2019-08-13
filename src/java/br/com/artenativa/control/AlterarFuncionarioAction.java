/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.FuncionarioDAO;
import br.com.artenativa.model.Funcionario;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class AlterarFuncionarioAction implements ICommand{
    
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response){
        
       int id = Integer.parseInt(request.getParameter("id"));
       Funcionario p = new Funcionario(id);
       request.setAttribute("pageRedirect", "funcionarioEditar.jsp");
       
       try {
          
       FuncionarioDAO cdao = new FuncionarioDAO();

       request.setAttribute("funcionario", cdao.buscar(p));

        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("erro", "Não foi possível alterar o funcionario, contate o administrador!");
            return request;
        }
        
        return request;
    }
    
}

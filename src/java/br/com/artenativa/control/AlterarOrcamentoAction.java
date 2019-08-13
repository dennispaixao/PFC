/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;


import br.com.artenativa.dao.ItemOrcamentoDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class AlterarOrcamentoAction implements ICommand{
    
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response){
        
       int id = Integer.parseInt(request.getParameter("id"));

       request.setAttribute("pageRedirect", "orcamentoEditar.jsp");
       
       try {
             Orcamento o = new Orcamento(id);
             OrcamentoDAO  odao= new OrcamentoDAO();   
             o = odao.buscar(o);       
             ArrayList<ItemOrcamento> itens;      
             ItemOrcamentoDAO idao = new ItemOrcamentoDAO();  
             itens = idao.listar(o);
             o.setItens(itens);  
             request.setAttribute("orcamento" , o);

        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("pageRedirect", "erro.jsp");
            request.setAttribute("erro", "Não foi possível alterar o funcionario, contate o administrador!");
            return request;
        }
        
        return request;
    }
    
}

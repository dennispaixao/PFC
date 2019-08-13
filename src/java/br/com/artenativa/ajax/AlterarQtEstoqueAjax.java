/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.ajax;


import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.dao.ProdutoDAO;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.model.Produto;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Dennis
 */
public class AlterarQtEstoqueAjax extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String retorno="";
        
            String receive = request.getParameter("id");
            String[] paramAlt = receive.split("-");   
            
            int idProd = Integer.parseInt(paramAlt[0]);
            Float qt = Float.parseFloat(paramAlt[1]);
            
            try{
                Produto p = new Produto(idProd);
                ProdutoDAO pdao= new ProdutoDAO();               
                p = pdao.buscar(p);
                p.setQtEstoque(qt);
                pdao.alterar(p);
                retorno = "A quantidade do produto "+p.getNome()+" foi alterada";
                

            }catch(ClassNotFoundException | SQLException e){
            
                retorno = "Não foi possível alterar a quantidade do produto";
                
                
            }
            
            response.getWriter().write(retorno);

    }
    


  
}

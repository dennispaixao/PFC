/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.artenativa.ajax;
import br.com.artenativa.dao.ItemOrcamentoDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.model.mock.ItemOrcamentoMock;
import br.com.artenativa.util.ParseJson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecuperarItensOrcamentoAjax extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException, ClassNotFoundException {
             response.setContentType("text/html;charset=UTF-8");
 
             ArrayList<ItemOrcamento> itens;
             int id = Integer.parseInt(request.getParameter("id")); //id do orçamento recebido como param
             Orcamento o = new Orcamento(id);
             ItemOrcamentoDAO idao = new ItemOrcamentoDAO();
             itens = idao.listar(o);      
             //objeto "Mascara"para converter converter o objeto em objeto javascript 
             ArrayList<ItemOrcamentoMock> itensMock = new ArrayList();
             itens.stream().map((i) -> {
                 ItemOrcamentoMock imock = new ItemOrcamentoMock();
                 imock.setId(i.getId());
                 imock.setNomeProduto(i.getProduto().getNome());
                 imock.setIdProduto(i.getProduto().getId());
                 imock.setPrecoProduto(i.getProduto().getPreco());
                 imock.setQuantidade(i.getQuantidade());
                 imock.setId(i.getId());
            return imock;
        }).forEachOrdered((imock) -> {
            itensMock.add(imock);
        });

             
             response.getWriter().write(new ParseJson().parseJson((ArrayList)itensMock));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RecuperarItensOrcamentoAjax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

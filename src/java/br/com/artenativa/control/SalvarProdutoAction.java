/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.ProdutoDAO;
import br.com.artenativa.model.Produto;
import br.com.artenativa.util.Validator;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class SalvarProdutoAction implements ICommand {

    String msg = null;

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        Double preco = Double.parseDouble(request.getParameter("preco"));   
        Double peso = Double.parseDouble(request.getParameter("peso")) ; 
        Double largura = Double.parseDouble(request.getParameter("largura"));
        Double altura = Double.parseDouble(request.getParameter("altura")) ;
        Double espessura =Double.parseDouble(request.getParameter("espessura"));

        Produto p = new Produto(id);
        
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setPreco(preco);
        p.setPeso(peso); 
        p.setLargura(largura);
        p.setAltura(altura);
        p.setEspessura(espessura);

        //validação se tudo ok msg == null
        Validator vl = new Validator();
        //nos metodos é ideal inverter a ordem de verificação para mensagens obedecerem uma ordem

        msg = vl.validaString(nome, "Nome", 2, 40, false); //ultima validação feita ex: nome não pode ficar em branco

        if (msg == null) {
            try {
                ProdutoDAO cdao = new ProdutoDAO();
                cdao.alterar(p);
         
                msg = "Produto " + nome + " alterado com sucesso";
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadastrarProdutoAction.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        
        request.setAttribute("msg", msg);
        ProdutoDAO cdao;
        try {
            cdao = new ProdutoDAO();
            request.setAttribute("ListaProdutos", cdao.listar());
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SalvarProdutoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        request.setAttribute("pageRedirect", "produtoListar.jsp");
        return request;
    }

    public static boolean isNumeric(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}

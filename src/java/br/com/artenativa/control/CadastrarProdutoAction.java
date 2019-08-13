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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class CadastrarProdutoAction implements ICommand {

    String msg;

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        Double preco = Double.parseDouble(request.getParameter("preco"));   
        Double peso = Double.parseDouble(request.getParameter("peso")) ; 
        Double largura = Double.parseDouble(request.getParameter("largura"));
        Double altura = Double.parseDouble(request.getParameter("altura")) ;
        Double espessura =Double.parseDouble(request.getParameter("espessura"));

        Produto p = new Produto();

        p.setNome(nome);
        p.setDescricao(descricao);
        String dataCad = ""+Calendar.getInstance().getTime().getTime();
        p.setDataCadastro(dataCad.substring(0, dataCad.length()-3));      
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
                cdao.inserir(p);
        
                     msg = "Produto " + nome + " cadastrado com sucesso";
       
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadastrarProdutoAction.class.getName()).log(Level.SEVERE, null, ex);
               
            }
        }
        request.setAttribute("msg", msg);
        request.setAttribute("pageRedirect", "produto.jsp");
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

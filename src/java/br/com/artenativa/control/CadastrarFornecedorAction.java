/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.FornecedorDAO;
import br.com.artenativa.model.Fornecedor;
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
public class CadastrarFornecedorAction implements ICommand {

    String msg;

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
        
        String nome = request.getParameter("nome");
        String cnpj = request.getParameter("cnpj"); 
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String celular = request.getParameter("celular");

        Fornecedor p = new Fornecedor();

        p.setNome(nome);
        String dataCad = ""+Calendar.getInstance().getTime().getTime();
        p.setDataCadastro(dataCad.substring(0, dataCad.length()-3));  
        p.setCnpj(cnpj);
        p.setEmail(email);
        p.setTelefone(telefone);
        p.setCelular(celular);

        //validação se tudo ok msg == null
      
        Validator vl = new Validator();
        
        //nos metodos é ideal inverter a ordem de verificação para mensagens obedecerem uma ordem
      
        msg = vl.validaString(nome, "Nome", 2, 40, false); //ultima validação feita ex: nome não pode ficar em branco
       
        
  
        
        if (msg == null) {
            try {
                FornecedorDAO cdao = new FornecedorDAO();
                cdao.inserir(p);

                    msg = "Fornecedor " + nome + " cadastrado com sucesso";
             
                
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadastrarFornecedorAction.class.getName()).log(Level.SEVERE, null, ex);
               
            }
        }
        request.setAttribute("msg", msg);
        request.setAttribute("pageRedirect", "fornecedor.jsp");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.FuncionarioDAO;
import br.com.artenativa.model.Funcionario;
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
public class SalvarFuncionarioAction implements ICommand {

    String msg = null;

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String sexo = request.getParameter("sexo");
        String rg = request.getParameter("rg");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String celular = request.getParameter("celular");

        Funcionario p = new Funcionario(id);
        p.setNome(nome);
        p.setSobrenome(sobrenome);
        p.setSexo(sexo);
        p.setRg(rg);
        p.setCpf(cpf);
        p.setEmail(email);
        p.setTelefone(telefone);
        p.setCelular(celular);
        

        //validação se tudo ok msg == null
        Validator vl = new Validator();
        //nos metodos é ideal inverter a ordem de verificação para mensagens obedecerem uma ordem
        char[] CharsSexo = new char[2];
        CharsSexo[0] = 'm';
        CharsSexo[1] = 'f';
        msg = vl.validaCharset(p.getSexo(), CharsSexo, "Sexo");

        msg = vl.validaString(sobrenome, "Sobrenome", 2, 80, false);

        msg = vl.validaString(nome, "Nome", 2, 40, false); //ultima validação feita ex: nome não pode ficar em branco

        if (msg == null) {
            try {
                FuncionarioDAO cdao = new FuncionarioDAO();
                cdao.alterar(p);
                if ("f".equals(sexo)) {
                    msg = "Funcionaria " + nome + " " + sobrenome + " alterada com sucesso";
                } else {
                    msg = "Funcionario " + nome + " " + sobrenome + " alterado com sucesso";
                }
                
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadastrarFuncionarioAction.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        
        request.setAttribute("msg", msg);
        FuncionarioDAO cdao;
        try {
            cdao = new FuncionarioDAO();
            request.setAttribute("ListaFuncionarios", cdao.listar());
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SalvarFuncionarioAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        request.setAttribute("pageRedirect", "funcionarioListar.jsp");
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

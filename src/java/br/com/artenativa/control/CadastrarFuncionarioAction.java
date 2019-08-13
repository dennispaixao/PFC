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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class CadastrarFuncionarioAction implements ICommand {

    String msg;

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
        
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String rg= request.getParameter("rg");   
        String cpf = request.getParameter("cpf"); 
        String sexo = request.getParameter("sexo");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String celular = request.getParameter("celular");

        Funcionario p = new Funcionario();

        p.setNome(nome);
        p.setSobrenome(sobrenome);
        //dataCad = now unix time
        String dataCad = ""+Calendar.getInstance().getTime().getTime();
        //retirar os milisegundos do unix time
        p.setDataCadastro(dataCad.substring(0, dataCad.length()-3));      
        p.setRg(rg);
        p.setCpf(cpf); 
        p.setEmail(email);
        p.setTelefone(telefone);
        p.setCelular(celular);
        p.setSexo(sexo);

        //validação se tudo ok msg == null
      
        Validator vl = new Validator();
        
        //nos metodos é ideal inverter a ordem de verificação para mensagens obedecerem uma ordem
       
        char[] CharsSexo = new char[2];
        CharsSexo[0] = 'm';
        CharsSexo[1] = 'f';
        msg = vl.validaCharset(p.getSexo(),CharsSexo,"Sexo");
        // msg = vl.validaRg(rg);
        // msg = vl.validaCPF(cpf);
        msg = vl.validaString(sobrenome, "Sobrenome", 2, 80, false); 
        msg = vl.validaString(nome, "Nome", 2, 40, false); //ultima validação feita ex: nome não pode ficar em branco
       
        
  
        
        if (msg == null) {
            try {
                FuncionarioDAO cdao = new FuncionarioDAO();
                cdao.inserir(p);
                

                if("f".equals(sexo))
                    msg = "Funcionaria " + nome + " " + sobrenome + " cadastrada com sucesso";
                else    
                    msg = "Funcionario " + nome + " " + sobrenome + " cadastrado com sucesso";
                
                
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadastrarFuncionarioAction.class.getName()).log(Level.SEVERE, null, ex);
               
            }
        }
        request.setAttribute("msg", msg);
        request.setAttribute("pageRedirect", "funcionario.jsp");
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

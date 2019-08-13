    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.control;

import br.com.artenativa.dao.UsuarioDAO;
import br.com.artenativa.model.Usuario;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dennis
 */
public class LogarUsuarioAction implements ICommand {

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");        
        Usuario u = new Usuario();
        u.setNome(usuario);
        u.setSenha(senha);
        
       try{
         UsuarioDAO udao = new UsuarioDAO();         
         Usuario autenticado = udao.autenticar(u);
         
         if(autenticado!= null){
             HttpSession sessao = request.getSession();
             sessao.setAttribute("usuario",autenticado);
             request.setAttribute("pageRedirect", "index.jsp");
         }else{
               request.setAttribute("pageRedirect", "login.jsp");
               request.setAttribute("msg", "Não foi possível autenticar o usuário");
         }
       
         
        
    }
    catch (SQLException | ClassNotFoundException ex ) {
        request.setAttribute("pageRedirect", "erro.jsp");
        request.setAttribute("erro", "Não foi possível autenticar o usuário");
        return request;
    }

    return request ;
}

}

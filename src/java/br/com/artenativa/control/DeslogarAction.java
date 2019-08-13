package br.com.artenativa.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeslogarAction implements ICommand {

    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
          
            HttpSession sessao = request.getSession();
            sessao.removeAttribute("usuario");
            sessao.invalidate();
            request.setAttribute("pageRedirect", "login.jsp");
            return request ;
            
    }

}

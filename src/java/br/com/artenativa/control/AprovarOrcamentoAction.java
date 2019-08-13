package br.com.artenativa.control;

import br.com.artenativa.dao.FornecedorDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.Fornecedor;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.util.Validator;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AprovarOrcamentoAction implements ICommand {
    @Override
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response) {
       
      /*  try {
            int id =  Integer.parseInt(request.getParameter("idOrc"));
            Orcamento o = new Orcamento(id);
            OrcamentoDAO odao= new OrcamentoDAO();
            o = odao.buscar(o);
            o.setEstado(2);
            odao.alterar(o);
        

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AprovarOrcamentoAction.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("pageRedirect", "erro.jsp");
 
        }*/
       request.setAttribute("pageRedirect", "orcamentoListar.jsp?status=2");
       return request;
    }


}

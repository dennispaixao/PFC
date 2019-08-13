/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.ajax;

import br.com.artenativa.dao.ClienteDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.model.mock.OrcamentoMock;
import br.com.artenativa.util.ParseDates;
import br.com.artenativa.util.ParseJson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */
public class BuscarListaOrcamentosAjax extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              response.setContentType("text/html;charset=UTF-8");
              
              int sit = Integer.parseInt(request.getParameter("situacao")); 
              String retorno; 
              try{  
                   OrcamentoDAO odao = new OrcamentoDAO();
                   ArrayList<Orcamento> orcamentos = odao.listar(sit);
                   ArrayList<OrcamentoMock> omocks = new ArrayList();    
                   orcamentos.forEach((_o)->{
                       try {
                           OrcamentoMock omock = new OrcamentoMock(_o.getId());
                           omock.setNomeCliente(new ClienteDAO().buscar(_o.getCliente()).getNome());
                           omock.setDataInsercao(ParseDates.formatUnixToDisplay(_o.getDataInsercao()));
                           omock.setEstado(_o.getEstado());
                           omocks.add(omock);     
                       } catch (SQLException | ClassNotFoundException ex) {
                           Logger.getLogger(BuscarListaOrcamentosAjax.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   });
                   ParseJson pj = new ParseJson();
                   retorno = pj.parseJson((ArrayList)omocks);
                   response.getWriter().write(retorno); 
              }catch(IOException | ClassNotFoundException | SQLException e){
                        response.getWriter().write("Nenhum produto encontrado");
              }
             
            
             
             
    }
    
 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

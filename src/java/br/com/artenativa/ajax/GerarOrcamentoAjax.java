/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.ajax;

import br.com.artenativa.dao.ClienteDAO;
import br.com.artenativa.dao.ItemOrcamentoDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.Cliente;
import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.model.Produto;
import br.com.artenativa.model.Usuario;
import br.com.artenativa.util.ParseDates;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dennis
 */
public class GerarOrcamentoAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException, ClassNotFoundException {
        
              response.setContentType("text/html;charset=UTF-8");
              
              //mensagem de erro é setada em null e alterada caso ocorra algum erro
              //no fim só será efetuado o processo caso erro permaneça null
              String erro = null;
              //string de resposta para a página solicitante
              String retorno = "";
              
              //recebe-se uma string com os parametros do orçamento
              String itens = request.getParameter("itens");  
              //separa-se cada parametro em um indice do array
              String[] campos  = itens.split(","); 
              
              //separa-se cada campo em uma variável
              String its = campos[0]; //ids dos produtos e quantidades de cada produto separado por 'id : qt'
              int idCliente = Integer.parseInt(campos[1]);  //id do cliente
              String dataInicio = ParseDates.parseUnix(campos[2]).toString(); 
              String dataPrevista = ParseDates.parseUnix(campos[3]).toString();  
              String descricao = campos[4];  
              
              Cliente cli = new Cliente(idCliente);
                    
              try {
                  Cliente checado = new Cliente();
                  ClienteDAO cliDAO = new ClienteDAO();
                  checado = cliDAO.buscar(cli);
                  erro = checado.getNome()==null?"Número do cliente não foi encontrado":null;
                  
              }catch(ClassNotFoundException | SQLException e){
                  erro="Número do cliente não foi encontrado";
              }


              if (erro == null ){
                  HttpSession sessao = request.getSession();//get responsavel
                  Usuario responsavel = (Usuario) sessao.getAttribute("usuario");
                  //get data atual em String "" unix
                  String dataCad = ""+Calendar.getInstance().getTime().getTime();
                  dataCad = dataCad.substring(0, dataCad.length()-3);

                  Orcamento orc = new Orcamento(); 
                  orc.setCliente(cli); 
                  orc.setResponsavel(responsavel); 
                  orc.setDataInsercao(dataCad);
                  orc.setDataInicio(dataInicio);
                  orc.setDataPrevista(dataPrevista);
                  orc.setRelatorio(descricao);
                  
                  try{
                      OrcamentoDAO orcDAO = new OrcamentoDAO(); 
                      int idOrcInserido = orcDAO.inserir(orc);
                      String[] itensOrc = its.split(";");   
                      for(int i = 0 ;i < itensOrc.length ; i++){  
                          String[] item = itensOrc[i].split(":");   
                          int idProduto  = Integer.parseInt(item[0]);
                          Float quantidade = Float.parseFloat(item[1]);
                          ItemOrcamento iOrc = new ItemOrcamento();
                          iOrc.setProduto(new Produto(idProduto));
                          iOrc.setOrcamento(new Orcamento(idOrcInserido));
                          iOrc.setQuantidade(quantidade);
                          ItemOrcamentoDAO idao = new ItemOrcamentoDAO();                          
                          idao.inserir(iOrc); 
                          
                      }
                      
                      retorno = "O orçamento foi criado!" ;  
                      
                  }catch(ClassNotFoundException | SQLException e){
                      retorno = "Erro ao cadastrar"; 
                 
                  }        
              }else{
                      retorno = erro;  
              }
              
              
              response.getWriter().write(retorno);
              
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GerarOrcamentoAjax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
  
}

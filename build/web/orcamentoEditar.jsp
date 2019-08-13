<%-- 
    Document   : verOrcamento.jsp
    Created on : 24/05/2019, 20:24:53
    Author     : Dennis
--%>

<%@page import="br.com.artenativa.util.ParseDates"%>
<%@page import="br.com.artenativa.model.Orcamento"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
        <link href="src/css/tabela.css" rel="stylesheet" type="text/css"/>
        <link href="src/css/cliente.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="/menu.jsp" %>    
        
      <%  
          Orcamento o = (Orcamento) request.getAttribute("orcamento");
      %>
        
        <div id="container">
            <div id="box-form" >
                <h1> Orçamento</h1>
                <h4>Orçamento: <%=o.getId()%></h4>
                <h4>Cliente: <%= o.getCliente().getNome()%></h4>
                <form action="ControllerFactory" method="post">

                    Id Cliente : <input type="text" id="cliente" value="<%= o.getCliente().getId() %>"><br>
                    Data início : <input type="date" id="datainicio" value="<%= ParseDates.formatUnixToBrowser(o.getDataInicio())%>"><br>
                    Data prevista : <input type="date" id="dataprevista" value="<%= ParseDates.formatUnixToBrowser(o.getDataPrevista())%>"><br>
                    Descrição : <textarea id="descricao"><%= o.getRelatorio() %></textarea><br>


                    <div style="float:left; text-align: left;"> 
                        Inserir Item:<br> <input type="text" id="q"><br>
                        <div id="itens">
                            <table id="table"  border="1" >  
                                <tbody id="tbody">
                                </tbody>
                            </table>
                        </div> 
                    </div>

                    <br> &nbsp; <br>  <br>  
                    <div id="tabela-orcamento" style="margin-top:20px;">

                        <table id="table"  border="1"  >  
                            <thead>
                            <th>id</th>
                            <th>nome</th>
                            <th>preco</th>
                            <th>qt</th>
                            <th>total</th>
                            <th>opções</th>
                            </thead>     
                            <tbody id="itens-orcamento">
                            <input id="btn-alterar-orcamento" style="background: #ffcc66; padding: 5px" type="button" name="acao" value="Continuar Orcamento">

                            </tbody>
                        </table>
                        <br>
                        Total  R$<input id="total-orcamento" readonly>
                    </div>  
                </form>
            </div>
        </div>      

        <script src="src/js/OrcamentoAlterar.js"></script>     
    </body>
</html>

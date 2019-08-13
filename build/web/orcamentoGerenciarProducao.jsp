<%-- 
    Document   : OrcamentoListar
    Created on : 23/05/2019, 16:27:03
    Author     : Dennis
--%>



<%@page import="br.com.artenativa.util.ParseDates"%>
<%@page import="br.com.artenativa.model.Orcamento"%>
<%@page import="java.util.List"%>
<%@page import="br.com.artenativa.model.ItemOrcamento"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Orçamentos</title>
        <link href="src/css/cliente.css" rel="stylesheet" type="text/css"/>
        <link href="src/css/tabela.css" rel="stylesheet" type="text/css"/>
      
        
    </head>
    <body>
        <%@include file="/menu.jsp" %>    
        <div id="container">

        <h1 id="listarh1"></h1> 

        <% List<ItemOrcamento> itens = (ArrayList<ItemOrcamento>) request.getAttribute("itens"); %>
        <% Orcamento o = (Orcamento) request.getAttribute("orcamento"); 
            String inicio = ParseDates.formatUnixToDisplay(o.getDataInicio());
            String prevista= ParseDates.formatUnixToDisplay(o.getDataPrevista());
        %> 
        
        <input type="hidden" value="<%=o.getId()%>" id="idOrc">
        <div>
               <h1> Orcamento : <%= o.getId()%> </h1> 
               <h3> Cliente: <%= o.getCliente().getNome()%></h3>
               <h5> Início previsto: <%=inicio%></h3>
               <h5> Termino previsto: <%=prevista%></h3>
        </div>    <br>
        
             <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Item</th>
                        <th>Qt</th>
                        <th>Situacao</th>
                        <th>Ferramentas</th>
                    </tr>
                    
                </thead>
                    <% for(ItemOrcamento i : itens){ %>  
    
                    <tbody>
                        <input type="hidden" value="<%=i.getStatus()%>" id="st">
                        
                        <td id="idItem"><%=i.getId()%> </td> 
                        <td><%= i.getProduto().getNome() %> </td> 
                        <td><%= i.getQuantidade() %> </td>   
                        <td id="tdSit"></td>      
                        <td> <input type="button" id="finalizarItem" > </td>  
                   </tbody>   
                   <% } %>
                </table>
             
           <input type="button" id="IniciarEntrega" value="Iniciar Entrega">
    </div>       
    <script src="src/js/orcamentoGerenciarProducao.js"> </script>    
    </body>
</html>

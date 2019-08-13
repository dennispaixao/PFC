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
            <% List<ItemOrcamento> itens = (ArrayList<ItemOrcamento>) request.getAttribute("itens"); %>
            <% Orcamento o = (Orcamento) request.getAttribute("orcamento"); %>
            <div style="text-align: right; padding:50px">
                    <h1 id="listarh1">Orçamento <%=o.getId()%></h1> 
                    <h2>Cliente: <%=o.getCliente().getNome()%> </h2>   
                    <H2>Responsavel: <%=o.getResponsavel().getNome()%> </H2>   
                    <H2>Data de insercao: <%=o.getResponsavel().getNome()%> </H2>   
                    <H2>Data de inicio: <%=ParseDates.formatUnixToDisplay(o.getDataInicio())%> </H2>   
                    <H2>Data de prevista: <%=ParseDates.formatUnixToDisplay(o.getDataPrevista())%> </H2>   
                    <H2>Data do fim: <%=ParseDates.formatUnixToDisplay(o.getDataFim())%> </H2>
                      <H2>Valor: <%=o.getValor()%> </H2>
                    <textArea>Relatorio: <%=o.getRelatorio()%> </textArea>
                  
               </div> 
                <table border="l">     
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Item</th>
                            <th>Quantidade</th>

                        </tr>
                    </thead>
                      <% for(ItemOrcamento i : itens){ %>  
    
                    <tbody>
                        <input type="hidden" value="<%=i.getStatus()%>" id="st">
                        
                        <td id="idItem"><%=i.getId()%> </td> 
                        <td><%= i.getProduto().getNome() %> </td> 
                        <td><%= i.getQuantidade() %> </td>   

                   </tbody>   
                   <% } %>
                </table>


        </div>  


    </body>
</html>

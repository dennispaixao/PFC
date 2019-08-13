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
        <%  
            Orcamento o = (Orcamento) request.getAttribute("orcamento"); 
            String inicio = ParseDates.formatUnixToDisplay(o.getDataInicio());
            String prevista= ParseDates.formatUnixToDisplay(o.getDataPrevista());
        %> 
    </div>    
    <script src="src/js/orcamentoGerenciarProducao.js"> </script>    
    </body>
</html>

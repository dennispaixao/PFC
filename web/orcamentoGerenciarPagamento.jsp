<%-- 
    Document   : orcamentoPagar
    Created on : 01/06/2019, 02:39:07
    Author     : Dennis
--%>

<%@page import="br.com.artenativa.util.ParseDates"%>
<%@page import="br.com.artenativa.model.Orcamento"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="src/css/cliente.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/menu.jsp" %>     
        <%  Orcamento orc = (Orcamento) request.getAttribute("orcamento");
            double total = orc.getValor();
            String totalFormatado = String.format("%.2f", total);
            double p = orc.getTotalPago();
            String pago = String.format("%.2f", p);  %>

        <div id="box-form">
            
            <div style="float:left; width:400px; text-align: left;">
                <h1> Orcamento:<%= orc.getId()%> </h1>
                <h4> Cliente:<%= orc.getCliente().getNome()%> </h4>
                <h4> Responsavel:<%= orc.getResponsavel().getNome()%> </h4>
                <h4> DataCadastro:<%= ParseDates.formatUnixToDisplay(orc.getDataInicio())%> </h4>
            </div> 
            
            <form action="ControllerFactory" method="POST">
                <br>
                <input type="button" id="produzir" value="Aprovar Orcamento">
                <input type="hidden" id="idOrc" value="<%= orc.getId()%>" readonly ><br>
                <input type="text" id="total" value="Total R$:<%=totalFormatado%>" readonly ><br>
                <input type="text" id="qtpaga" value="Pago R$:<%=pago%>" readonly><br><br>
                R$<input type="text" id="campoPagar"  style="width:100px; margin-right: 20px;">
                <input type="button" id="pagar" style="background:lightgreen" value="Inserir Pagamento"><br><br>
                Troco: <input  type="text" id="troco" style="width:110px" >
                
            </form>
                
        </div>

       <script src="src/js/GerenciarPagamentoOrcamento.js">  </script> 
       <script src="src/js/AprovarOrcamento.js">  </script> 
    </body>
</html>

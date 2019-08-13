<%-- 
    Document   : orcamento
    Created on : 04/04/2019, 16:02:23
    Author     : Dennis
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="src/css/cliente.css" rel="stylesheet" type="text/css"/>
        <link href="src/css/tabela.css" rel="stylesheet" type="text/css"/>
        <title>Orçamento</title>
    </head>

    <body>
        <%@include file="/menu.jsp" %>    
        <div id="container">

            <div id="box-form" >
                <h1> Orçamento </h1> 
                <form action="ControllerFactory" method="post">

                    Id Cliente : <input type="text" id="cliente"><br>
                    Data início : <input type="date" id="datainicio"><br>
                    Data prevista : <input type="date" id="dataprevista"><br>
                    Descrição<textarea id="descricao"> </textarea><br>
                    
                    
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
                            <input id="btn-gerar-orcamento" style="background: #FF3; padding: 5px" type="button" name="acao" value="gerar orcamento">
                            </tbody>
                        </table>
                        <br>
                        Total  R$<input id="total-orcamento" readonly>
                    </div>  
                </form>

                <div id="mensagem">
                    <% if (request.getAttribute("msg")!= null) {%>
                    <h2><%=request.getAttribute("msg") %> </h2>
                    <% }%>
                </div>

            </div>

        </div>      

        <script src="src/js/GerarOrcamento.js"></script>  


    </body>      
</html>

<%-- 
    Document   : cliente
    Created on : 19/11/2018, 23:33:13
    Author     : Dennis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        
    <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="src/css/cliente.css" rel="stylesheet" type="text/css"/>

    </head>
    
    <body>
       <%@include file="/menu.jsp" %>     
      
       
       <div id="box-form" >
            <h1> Produto </h1>
           <form action="ControllerFactory" method="post">
               
               Nome: <input type="text" name="nome"><br>
               descricao: <input type="text" name="descricao"><br>
               preco: <input type="text" name="preco"><br>
               peso: <input type="text" name="peso"><br>
               largura: <input type="text" name="largura"><br>
               altura: <input type="text" name="altura"><br>
               espessura: <input type="text" name="espessura"><br>
                            
               <input type="submit" name="acao" value="Cadastrar Produto">
               <input type="submit" name="acao" value="Listar Produto">
               
           </form>
           
            <div id="mensagem">
                <% if (request.getAttribute("msg")!= null) {%>
                    <h2><%=request.getAttribute("msg") %> </h2>
                <% }%>
            </div>
            
       </div>
            
    </body>      
</html>


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
             <h1> Funcionario </h1>
            <form action="ControllerFactory" method="post">

               Nome: <input type="text" name="nome"><br>
               Sobrenome: <input type="text" name="sobrenome"><br><br>
               Sexo:  
               Feminino<input type="radio" name="sexo" value="f">
               Masculino<input type="radio" name="sexo" value="m" checked="checked"><br>
               Rg: <input type="text" name="rg"><br>
               Cpf: <input type="text" name="cpf"><br>
               Email: <input type="text" name="email"><br>
               Telefone: <input type="text" name="telefone"><br>
               Celular: <input type="text" name="celular"><br>
 
               
               <input type="submit" name="acao" value="Cadastrar Funcionario">
               <input type="submit" name="acao" value="Listar Funcionario">
               
           </form>
           
            <div id="mensagem">
                <% if (request.getAttribute("msg")!= null) {%>
                    <h2><%=request.getAttribute("msg") %> </h2>
                <% }%>
            </div>
            
       </div>
            
    </body>      
</html>


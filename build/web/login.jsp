<%-- 
    Document   : index.jsp
    Created on : 06/03/2019, 13:32:51
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
       
       <div id="box-form" >
            <h1> Arte Nativa </h1>
       <form action="ControllerFactory" method="post">
           
           Usuário: <input type="text" name="usuario"><br>
           Senha: <input type="password" name="senha"><br><br>
           <input type="submit" name="acao" value="Logar Usuario">
          
       </form>
            <div id="mensagem">
                <%  if (request.getAttribute("msg")!= null) {
                      out.print("<h2>"+request.getAttribute("msg")+"</h2>");

                    }
                
                %>
            </div>
            
            
            
       </div>
    </body>      
</html>


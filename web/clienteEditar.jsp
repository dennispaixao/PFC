<%-- 
    Document   : clienteEditar
    Created on : 21/11/2018, 13:23:46
    Author     : Dennis
--%>

<%@page import="br.com.artenativa.model.Pessoa"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
        <link href="src/css/cliente.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
                 <%@include file="/menu.jsp" %>    

    
       <% 
           Pessoa p = (Pessoa) request.getAttribute("cliente");
     
       
       
       %>
    

       <div id="box-form" >
             <h1>Editar cadastro de cliente</h1> 
        <form action="ControllerFactory" method="post">
            <input type="hidden" name="id" value="<%=p.getId()%>">
             Nome: <input type="text" name="nome" value="<%=p.getNome()%> "><br>
             Sobrenome: <input type="text" name="sobrenome" value="<%=p.getSobrenome()%>"><br><br>
            
             Sexo:  
             Feminino<input type="radio" name="sexo" value="f" <% if("f".equals(p.getSexo())){ out.println("checked='checked'");} %>>
             Masculino<input type="radio" name="sexo" value="m" <% if("m".equals(p.getSexo())){ out.println("checked='checked'");} %>><br>
             Rg: <input type="text" value="<%= p.getRg() %>"  name="rg"><br>
             Cpf: <input type="text" value="<%= p.getCpf() %>" name="cpf"><br>
             Email: <input type="text" value="<%= p.getEmail() %>"name="email"><br>
             Telefone: <input type="text" value="<%= p.getTelefone() %>" name="telefone"><br>
             Celular: <input type="text" value="<%= p.getCelular() %>" name="celular"><br>
             
             
             <a href="ControllerFactory?acao=ListarCliente" style="margin-right: 20px"  > voltar </a>
            <input type="submit" name="acao" value="Salvar Cliente">
         </form>

              <div id="mensagem">
                  <% if (request.getAttribute("msg")!= null) {%>
                      <h2><%=request.getAttribute("msg") %> </h2>
                  <% }%>
              </div>

       </div>
    </body>
</html>

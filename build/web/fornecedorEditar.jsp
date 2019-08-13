<%-- 
    Document   : clienteEditar
    Created on : 21/11/2018, 13:23:46
    Author     : Dennis
--%>

<%@page import="br.com.artenativa.model.Fornecedor"%>
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
           Fornecedor p = (Fornecedor) request.getAttribute("fornecedor");
    
       
       
       %>

       <div id="box-form" >
             <h1>Editar cadastro de fornecedor</h1>
        <form action="ControllerFactory" method="post">
            <input type="hidden" name="id" value="<%=p.getId()%>">
             Nome: <input type="text" name="nome" value="<%=p.getNome()%> "><br>
             Cnpj: <input type="text" name="cnpj" value="<%=p.getCnpj()%>"><br>
             Email: <input type="text" name="email" value="<%= p.getEmail() %>"name="email"><br>
             Telefone: <input type="text" name="telefone" value="<%= p.getTelefone() %>" name="telefone"><br>
             Celular: <input type="text" name="celular" value="<%= p.getCelular() %>" name="celular"><br>
             
             
             <a href="ControllerFactory?acao=ListarFornecedor" style="margin-right: 20px"  > voltar </a>
            <input type="submit" name="acao" value="Salvar Fornecedor">
         </form>

              <div id="mensagem">
                  <% if (request.getAttribute("msg")!= null) {%>
                      <h2><%=request.getAttribute("msg") %> </h2>
                  <% }%>
              </div>

       </div>
    </body>
</html>

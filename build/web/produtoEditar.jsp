<%-- 
    Document   : clienteEditar
    Created on : 21/11/2018, 13:23:46
    Author     : Dennis
--%>

<%@page import="br.com.artenativa.model.Produto"%>
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
       <h1>Editar cadastro de produto</h1>
       <% 
           Produto p = (Produto) request.getAttribute("produto");
    
       
       
       %>

       <div id="box-form" >
           
        <form action="ControllerFactory" method="post">
            <input type="hidden" name="id" value="<%=p.getId()%>">
               Nome: <input type="text" name="nome" value="<%=p.getNome()%>"><br>
               descricao: <input type="text" name="descricao" value="<%=p.getDescricao()%>"><br>
               preco: <input type="text" name="preco" value="<%=p.getPreco()%>"><br>
               peso: <input type="text" name="peso" value="<%=p.getPeso()%>"><br>
               largura: <input type="text" name="largura" value="<%=p.getLargura()%>"><br>
               altura: <input type="text" name="altura" value="<%=p.getAltura()%>"><br>
               espessura: <input type="text" name="espessura" value="<%=p.getEspessura()%>"><br>
             
             
             <a href="ControllerFactory?acao=ListarProduto" style="margin-right: 20px"  > voltar </a>
            <input type="submit" name="acao" value="Salvar Produto">
         </form>

              <div id="mensagem">
                  <% if (request.getAttribute("msg")!= null) {%>
                      <h2><%=request.getAttribute("msg") %> </h2>
                  <% }%>
              </div>

       </div>
    </body>
</html>

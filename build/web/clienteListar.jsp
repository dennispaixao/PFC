<%-- 
    Document   : verClientes
    Created on : 20/11/2018, 13:10:03
    Author     : Dennis
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="br.com.artenativa.model.Cliente"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
       <link href="src/css/tabela.css" rel="stylesheet" type="text/css"/> 
         <link href="src/css/cliente.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
              <%@include file="/menu.jsp" %>    
        <div id="container">
        <h1 id="listarh1">Clientes</h1> 

      
            <%  //recuperar a lista
                List<Cliente> clientes = (List<Cliente>) request.getAttribute("ListaCliente");
            %>
            <table border="l">     
                <thead>
                   
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Sobrenome</th>
                        <th>Cadastro</th>
                        <th>Ferramentas</th>

                    </tr>
                </thead>
                <tbody>
          
                  
              
                     
                   <%  
        
                    
                    
                    for(Cliente p : clientes ){
                       request.setAttribute("id", p.getId());
                       
                       String num = p.getDataCadastro();
                       int unixSeconds;
                       try {
                          unixSeconds = Integer.parseInt(num);
                        } catch (Exception ex1) {
                              unixSeconds =0;
                        }
                       
    
                       Date date = new java.util.Date(unixSeconds*1000L); 
                       SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
                       sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-3"));
                       String dtCad = sdf.format(date);
                        
                %>
                <tr>
                  
                    <th><%= p.getId() %></th>
                    <td><%= p.getNome()	%> </td>
                    <td><%= p.getSobrenome()%> </td>
                    <td><%= dtCad %> </td>
               
                <td><a href="ControllerFactory?acao=ExcluirCliente&id=<%= p.getId() %>">
                        <img src="img/excluir.png" alt="excluir" title="excluir"/>
                    </a>
                    <a href="ControllerFactory?acao=AlterarCliente&id=<%= p.getId() %>">
                        <img src="img/editar.png" alt="editar" title="editar"/>
                    </a>
                </td> 

                </tr>
                 <%
                     }
                %>
                </tbody>
            </table>
                <br><br>
            
                <% if(request.getAttribute("msg")!= null){ %>
                <script> alert(" <%= request.getAttribute("msg") %> ");</script>
                <% } %>
                
            
    </div>
    </body>
</html>

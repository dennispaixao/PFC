<%-- 
    Document   : estoque.jsp
    Created on : 23/05/2019, 21:43:30
    Author     : Dennis
--%>

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

         

            <link href="src/css/menu-orcamento.css" rel="stylesheet" type="text/css"/>
            <style> 

                #menu-estoque{background:blue; margin-top:30px; text-align: center;}
                #menu-estoque ul{width:800px; margin:0 auto; max-width: 100%; text-align:left}
                #menu-estoque ul li {display: inline; font-size:25px; margin-left:20px;}
                #menu-estoque ul li:hover{cursor:pointer}
                @media only screen and (max-width:600px){#menu-estoque{margin-top:120px;}}


            </style>
             <h1 id="listarh1">Estoque</h1> 
                    <nav id="menu-estoque">   
                            <ul>        
                                <a href="orcamentoListar.jsp?status=1"><li id="iniciados"> Todos</li></a>
                                <a href="orcamentoListar.jsp?status=2"><li id="producao"> Razoável</li></a>
                                <a href="orcamentoListar.jsp?status=3"><li id="entrega"> Abaixo</li> </a>
                            </ul>    
                        </nav>
            <div id="box-form" >
                  
                <form>
                       
                        Pequisar por nome: <input type="text" id="q"><br>
                        
               </form>   
                         </div> 
                        <table border="l">    

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Produto</th>
                                    <th>Fornecedor</th>
                                    <th>Quantidade</th>
                                    <th>Padrao</th>
                                </tr>
                            </thead>
                            <tbody id="tbodyOrcamentos">
                            </tbody>
                        </table>
        
      
            
        </div>  

        <script src="src/js/ListarEstoque.js"></script>    

    </body>
</html>

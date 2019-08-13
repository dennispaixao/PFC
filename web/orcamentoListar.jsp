<%-- 
    Document   : OrcamentoListar
    Created on : 23/05/2019, 16:27:03
    Author     : Dennis
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Orçamentos</title>
        <link href="src/css/cliente.css" rel="stylesheet" type="text/css"/>
        <link href="src/css/tabela.css" rel="stylesheet" type="text/css"/>
      
        
    </head>
    <body>
        <%@include file="/menu.jsp" %>    
        <div id="container">
        <h1 id="listarh1">Orçamentos</h1> 
           
            <link href="src/css/menu-orcamento.css" rel="stylesheet" type="text/css"/>
            <style> 
                
                #menu-orcamento{background:red; margin-top:30px; text-align: center;}
                #menu-orcamento ul{width:800px; margin:0 auto; max-width: 100%; text-align:left}
                #menu-orcamento ul li {display: inline; font-size:25px; margin-left:20px;}
                #menu-orcamento ul li:hover{cursor:pointer}
                @media only screen and (max-width:600px){#menu-orcamento{margin-top:120px;}}
      

            </style>
            <nav id="menu-orcamento">   
                <ul>        
                    <a href="orcamentoListar.jsp?status=1"><li id="iniciados"> Iniciados</li></a>
                    <a href="orcamentoListar.jsp?status=2"><li id="producao"> Produção</li></a>
                    <a href="orcamentoListar.jsp?status=3"><li id="entrega"> Entrega</li> </a>
                    <a href="orcamentoListar.jsp?status=4"><li id="finalizados"> Finalizados </li></a>
                </ul>    
            </nav>
        
        <table border="l">     
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Cadastro</th>
                        <th>Situacao</th>
                        <th>Ferramentas</th>
                    </tr>
                </thead>
                <tbody id="tbodyOrcamentos">
                </tbody>
        </table>
            
             
    </div>  
        
    <script src="src/js/ListarOrcamentos.js"></script>    
    

    </body>
</html>

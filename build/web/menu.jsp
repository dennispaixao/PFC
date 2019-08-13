<%-- 
    Document   : menu.jsp
    Created on : 03/04/2019, 14:05:02
    Author     : Dennis
--%>


<%@page import="br.com.artenativa.model.Usuario"%>
<link href="src/css/menu.css" type="text/css" rel="stylesheet">

<%try {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        if (usuario.getNome() != null) {
            sessao.setMaxInactiveInterval(10000);
%>


<nav id="menu-principal">

    <div>
        <ul>
            <a href="index.jsp"> <li style="text-align: right"> 
                    <div id="logo-menu" 
                         style="  
                         width:40px;
                         height:40px;
                         background-image: url('logoMenu.png');
                         background-size: 80%;
                         background-repeat: no-repeat;
                         background-position: center;
                         position: absolute;
                         transition:2s;
                         top:-10px;
                         ">

                    </div></a>
            </li>
            <li>Cadastrar
                <ul>
                    <a href="cliente.jsp"><li>Cliente</li></a>
                    <a href="funcionario.jsp"><li>Funcionario</li></a>
                    <a href="fornecedor.jsp"><li>Fornecedor</li></a>
                    <a href="produto.jsp"><li>Produto</li></a>
                    <a href="orcamento.jsp"><li>Orçamento</li></a>
                </ul>
            </li>
            <li>Listar
                <ul>
                    <a href="ControllerFactory?acao=ListarCliente"><li>Clientes</li></a>
                    <a href="ControllerFactory?acao=ListarFuncionario"><li>Funcionarios</li></a>
                    <a href="ControllerFactory?acao=ListarFornecedor"><li>Fornecedores</li></a>
                    <a href="ControllerFactory?acao=ListarProduto"><li>Produtos</li></a>
                    <a href="orcamentoListar.jsp"><li>Orçamentos</li></a>
                </ul>
            </li>

            <li>Relatórios
                <ul>
                    <a href="estoque.jsp"><li>Estoque</li></a>
                    <a href="#"><li>Orçamentos</li></a>
                </ul>
            </li>

            <li>
                <form method="post" action="ControllerFactory">
                    <input type="submit" name="acao" value="Deslogar">
                </form>
            </li>    
        </ul>  

    </div>            
</nav>
<p id="saudacao">
    <%=usuario.getNivel()%><br>
    <%=usuario.getId()%>:
    <%=usuario.getNome()%> <br>
</p>

<%} else {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            request.setAttribute("msg", "Não foi possível autenticar o usuário");
            rd.forward(request, response);
        }
    } catch (Exception e) {
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        request.setAttribute("msg", "Não foi possível autenticar o usuário");
        rd.forward(request, response);
            }%>            


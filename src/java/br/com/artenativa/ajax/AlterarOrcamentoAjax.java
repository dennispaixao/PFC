/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.ajax;

import br.com.artenativa.dao.ClienteDAO;
import br.com.artenativa.dao.ItemOrcamentoDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.Cliente;
import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.model.Produto;
import br.com.artenativa.model.Usuario;
import br.com.artenativa.util.ParseDates;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dennis
 */
public class AlterarOrcamentoAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        response.setContentType("text/html;charset=UTF-8");
        //mensagem de erro é setada em null e alterada caso ocorra algum erro
        //no fim só será efetuado o processo caso erro permaneça null
        String erro = null;
        //string de resposta para a página solicitante
        String retorno = "";
        //recebe-se uma string com os parametros do orçamento
        String itens = request.getParameter("itens");
        //separa-se cada parametro em um indice do array
        String[] campos = itens.split(",");
        //separa-se cada campo em uma variável
        String its = campos[0]; //ids dos produtos e quantidades de cada produto separado por 'id : qt'
        int idCliente = Integer.parseInt(campos[1]);  //id do cliente
        String dataInicio = ParseDates.parseUnix(campos[2]).toString();
        String dataPrevista = ParseDates.parseUnix(campos[3]).toString();
        String descricao = campos[4];
        int idOrcamento = Integer.parseInt(campos[5]);
        Cliente cli = new Cliente(idCliente);
        try {
            ClienteDAO cliDAO = new ClienteDAO();
            Cliente checado = cliDAO.buscar(cli);
            erro = checado.getNome() == null ? "Número do cliente não foi encontrado" : null;
        } catch (ClassNotFoundException | SQLException e) {
            erro = "Número do cliente não foi encontrado";
        }
        if (erro == null) {
            HttpSession sessao = request.getSession();//get responsavel
            Usuario responsavel = (Usuario) sessao.getAttribute("usuario");
            Orcamento orc = new OrcamentoDAO().buscar(new Orcamento(idOrcamento));
            orc.setId(idOrcamento);
            orc.setCliente(cli);
            orc.setResponsavel(responsavel);
            orc.setDataInicio(dataInicio);
            orc.setDataPrevista(dataPrevista);
            orc.setRelatorio(descricao);

            try {
                OrcamentoDAO orcDAO = new OrcamentoDAO();
                orcDAO.alterar(orc);
                String[] itensOrc = its.split(";");
                ArrayList<ItemOrcamento> itensOld = new ItemOrcamentoDAO().listar(orc);

                ItemOrcamentoDAO idao = new ItemOrcamentoDAO();

                //verifica se item do orçamento no bd está na nova tabela (p/ alteraçao) se sim altera a quantidade para a nova senão o exclui
                for (ItemOrcamento iOld : itensOld) {
                    boolean tem = false; //não está (default)
                    for (int i = 0; i < itensOrc.length; i++) {
                        String[] item = itensOrc[i].split(":");
                        int idProduto = Integer.parseInt(item[0]);
                        float quantidade = Float.parseFloat(item[1]);
                        //se tiver produto na nova lista recebida
                        if (iOld.getProduto().getId() == idProduto) {
                            iOld.setQuantidade(quantidade);
                            idao.alterarItem(iOld);
                            tem = true;//está
                        }
                    }
                    if (tem != true) {//não está
                        idao.excluir(iOld);//exclui
                    }
                }
                //verifica se item da nova tabela não existe no orcamento senão o insere
                for (int i = 0; i < itensOrc.length; i++) {
                    String[] item = itensOrc[i].split(":");
                    int idProduto = Integer.parseInt(item[0]);
                    Float quantidade = Float.parseFloat(item[1]);
                    ItemOrcamento iNovo = new ItemOrcamento();
                    iNovo.setProduto(new Produto(idProduto));
                    iNovo.setOrcamento(orc);
                    iNovo.setQuantidade(quantidade);
                    ItemOrcamento temmesmo = idao.temItemNoOrcamento(orc, iNovo.getProduto());
                    if (temmesmo == null) {
                        idao.inserir(iNovo);
                    }
                }

                double total = 0;
                for (ItemOrcamento i : new ItemOrcamentoDAO().listar(orc)) {
                    total += i.getQuantidade() * i.getProduto().getPreco();
                }
                Orcamento orcamento = new OrcamentoDAO().buscar(orc);
                orcamento.setValor(total);
                new OrcamentoDAO().alterar(orcamento);
                retorno = "O orçamento foi alterado!";
            } catch (ClassNotFoundException | SQLException e) {
                retorno = "Erro ao cadastrar";
            }
        } else {
            retorno = erro;
        }
        response.getWriter().write(retorno);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AlterarOrcamentoAjax.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

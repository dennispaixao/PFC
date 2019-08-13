/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;


import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.model.Produto;
import br.com.artenativa.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dennis
 */
public class ItemOrcamentoDAO {

    Connection connection;

    public ItemOrcamentoDAO() throws SQLException, ClassNotFoundException {
        //cria-se o construtor e faz a conexão com o banco aqui e seta o connection
    }  
    
    public boolean inserir(ItemOrcamento i) throws SQLException, ClassNotFoundException {
       connection = ConnectionFactory.getConexao();
        final String INSERT = "insert into itemOrcamento(idOrcamento, idProduto, quantidade, status) values(?,?,?,1)";
        try {
            PreparedStatement ps = connection.prepareCall(INSERT); 
            ps.setInt(1, i.getOrcamento().getId());
            ps.setInt(2, i.getProduto().getId());
            ps.setFloat(3, i.getQuantidade());
            //execute Update retorna um inteiro diferente do executeQuery que retorna um resultSet(dados da consulta)
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemOrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ItemOrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true; 
    }
    
    public ItemOrcamento temItemNoOrcamento(Orcamento o,Produto p) throws SQLException, ClassNotFoundException{
       final String CONSULTA = "Select * from itemOrcamento where idproduto = ? and idorcamento =?";
       connection = ConnectionFactory.getConexao();
       PreparedStatement ps = connection.prepareCall(CONSULTA);
       ps.setInt(1, p.getId());
       ps.setInt(2, o.getId());
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           ItemOrcamento i = new ItemOrcamento();
           i.setId(rs.getInt("id"));
           i.setOrcamento(new OrcamentoDAO().buscar(new Orcamento(rs.getInt("idOrcamento"))));
           i.setProduto(new ProdutoDAO().buscar(new Produto(rs.getInt("idProduto"))));
           i.setQuantidade(rs.getFloat("quantidade"));
           return i;
       }else{
           return null;
       }
    }

    public boolean alterarItem(ItemOrcamento i) throws SQLException, ClassNotFoundException{
       final String ALTERAR = "update itemorcamento set idorcamento = ?, idproduto =?, quantidade=?, dataInicio=?, datafim=?, status =? where id = ?";
       try{  
         connection = ConnectionFactory.getConexao();
         PreparedStatement ps = connection.prepareCall(ALTERAR);
         ps.setInt(1, i.getOrcamento().getId());
         ps.setInt(2, i.getProduto().getId());
         ps.setFloat(3, i.getQuantidade());
         ps.setString(4, i.getDataInicio());
         ps.setString(5, i.getDataFim());
         ps.setInt(6, i.getStatus());
         ps.setInt(7, i.getId());
         if(ps.executeUpdate()>0)
             return true;
         return false;
       }catch(ClassNotFoundException | SQLException e){
          return false;
       }finally{
         connection.close();
       }
    }

    public boolean excluirTodos(Orcamento o ) throws SQLException, ClassNotFoundException {
        connection = ConnectionFactory.getConexao();
        final String EXCLUIR_TODOS = "delete from itemorcamento where idorcamento = ?";
        try {
            PreparedStatement ps = connection.prepareCall(EXCLUIR_TODOS);
            //execute Update retorna um inteiro diferente do executeQuery que retorna um resultSet(dados da consulta)
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemOrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ItemOrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    } 
    
    public boolean excluir(ItemOrcamento i) throws SQLException, ClassNotFoundException {
        connection = ConnectionFactory.getConexao();
        final String EXCLUIR_TODOS = "delete from itemorcamento where id = ?";
        try {
            PreparedStatement ps = connection.prepareCall(EXCLUIR_TODOS);
            //execute Update retorna um inteiro diferente do executeQuery que retorna um resultSet(dados da consulta)
            ps.setInt(1, i.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemOrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ItemOrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }
    
    public ArrayList<ItemOrcamento> listar(Orcamento o) throws ClassNotFoundException, SQLException {
        connection = ConnectionFactory.getConexao();
        ArrayList<ItemOrcamento> itensOrcamento = new ArrayList<>();
        try {
            final String SELECT_ALL = "select * from itemorcamento where idorcamento = ?";
            PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL);
            pstmt.setInt(1, o.getId());
            try (
                ResultSet rs = pstmt.executeQuery()) {       
                while (rs.next()) {
                    ItemOrcamento i = new ItemOrcamento();
                    i.setId(rs.getInt("id"));
                    i.setProduto(new ProdutoDAO().buscar( new Produto((rs.getInt("idproduto")))));
                    i.setOrcamento(new OrcamentoDAO().buscar( new Orcamento((rs.getInt("idorcamento")))));
                    i.setQuantidade(rs.getFloat("quantidade"));
                    i.setStatus(rs.getInt("status"));
                    itensOrcamento.add(i);  
                }
               connection.close();
            }
        }catch(SQLException e){}
        return itensOrcamento;
    }
    
    public ItemOrcamento buscar(ItemOrcamento i) throws ClassNotFoundException, SQLException {
        final String BUSCAR = "SELECT * FROM itemorcamento WHERE ID = ?";
        try {
            connection = ConnectionFactory.getConexao();
            PreparedStatement pstmt = connection.prepareStatement(BUSCAR);
            pstmt.setInt(1, i.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();
            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();
            i.setId(rs.getInt("id"));
            i.setOrcamento( new OrcamentoDAO().buscar(new Orcamento(rs.getInt("idOrcamento"))));
            i.setProduto(new ProdutoDAO().buscar(new Produto(rs.getInt("idProduto"))));
            i.setQuantidade(rs.getFloat("quantidade"));
            i.setDataInicio(rs.getString("datainicio"));
            i.setDataFim(rs.getString("datafim"));
            i.setStatus(rs.getInt("status"));
            
        }catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connection.close();
        }
        return i;
    }

}
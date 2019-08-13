/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

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
public class ProdutoDAO implements ProdutoDAOInterface {

    Connection connection;

    public ProdutoDAO() throws SQLException, ClassNotFoundException {
        //cria-se o construtor e faz a conexão com o banco aqui e seta o connection
      
    }

    @Override
    public int selectCurr(){
        final String CURRSEQ = "SELECT CURRVAL ('id_produto_sequence')";
        
        PreparedStatement ps;  
        try {
            
            ps = connection.prepareCall(CURRSEQ);
            ResultSet rs = ps.executeQuery();
            return rs.getInt("currval");
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return 0;
    };
          
    
    @Override
    public boolean inserir(Produto p) {
      
        final String INSERT = "insert into produto (nome, descricao, preco , datacadastro, situacao,"
                + " peso , largura, altura, espessura)"
                + " values (?,?,?,?,1, ?,?,?,?);";

        try {
            connection = ConnectionFactory.getConexao();
            PreparedStatement ps = connection.prepareCall(INSERT);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescricao());
            ps.setDouble(3, p.getPreco());
            ps.setString(4, p.getDataCadastro());
            ps.setDouble(5, p.getPeso());
            ps.setDouble(6, p.getLargura());
            ps.setDouble(7, p.getAltura());
            ps.setDouble(8, p.getEspessura());
         
            
            //execute Update retorna um inteiro diferente do executeQuery que retorna um resultSet(dados da consulta)
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
        
    }

    @Override
    public boolean alterar(Produto p) {

        final String ALTERAR = "update produto set "
              + " nome = ?,"
              + " descricao = ?," 
              + " preco = ?,"
              + " situacao = ?, "
              + " peso = ?, "
              + " largura = ?, "
              + " altura = ?,"
              + " espessura = ?,"
              + " qtestoque = ?,"
              + " estoqueraz = ?"                
              + " where id = ?";

        try {
            connection = ConnectionFactory.getConexao();
            PreparedStatement ps = connection.prepareCall(ALTERAR);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescricao());
            ps.setDouble(3, p.getPreco());
            ps.setInt(4, 1);
            ps.setDouble(5, p.getPeso());
            ps.setDouble(6, p.getLargura());
            ps.setDouble(7, p.getAltura());
            ps.setDouble(8, p.getEspessura());
            ps.setFloat(9, p.getQtEstoque());
            ps.setFloat(10, p.getEstoqueRaz());
            ps.setInt(11, p.getId());    
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
        
        
    }

    @Override
    public boolean excluir(Produto p) {

        final String DELETE = "update produto set situacao = 0 where id = ?";
        try {
                connection = ConnectionFactory.getConexao();
            PreparedStatement ps = connection.prepareCall(DELETE);
            //execute Update retorna um inteiro diferente do executeQuery que retorna um resultSet(dados da consulta)
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public ArrayList<Produto> listar() {
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
                connection = ConnectionFactory.getConexao();
            final String SELECT_ALL = "SELECT id, nome, datacadastro FROM produto WHERE situacao <> 0 order by id desc";
            PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL);
            try ( //executa
                    
                ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {

                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setDataCadastro(rs.getString("datacadastro"));
                
                    //add na lista
                    produtos.add(p);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
    }
    

    @Override
    public Produto buscar(Produto p) {
        
        final String BUSCAR = "SELECT * FROM produto WHERE ID = ?";

        try {
                connection = ConnectionFactory.getConexao();
            PreparedStatement pstmt = connection.prepareStatement(BUSCAR);

            pstmt.setInt(1, p.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

      
            p.setNome(rs.getString("nome"));
            p.setDescricao(rs.getString("descricao"));
            p.setDataCadastro(rs.getString("datacadastro"));
            p.setPreco(rs.getDouble("preco"));
            p.setPeso(rs.getDouble("peso"));
            p.setLargura(rs.getDouble("largura"));
            p.setAltura(rs.getDouble("altura"));
            p.setEspessura(rs.getDouble("espessura"));
            p.setQtEstoque(rs.getFloat("qtestoque"));
            p.setEstoqueRaz(rs.getFloat("estoqueraz"));
  
          //  p.getEndereco().setPais(rs.getString("pais"));
          //  p.getEndereco().setUF(rs.getString("estado"));
          //  p.getEndereco().setCidade(rs.getString("cidade"));
          //  p.getEndereco().setLogradouro(rs.getString("rua"));
          //  p.getEndereco().setNumero(rs.getString("numero"));
          //  p.getEndereco().setCep(rs.getString("cep"));    
         //   p.getEndereco().setComplemento(rs.getString("complemento"));
      
            
        }catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return p;
    }

    @Override
    public ArrayList<Produto> listar(String nome) {
         ArrayList<Produto> produtos = new ArrayList<>();
        try {
                connection = ConnectionFactory.getConexao();
            final String SELECT_ALL = "SELECT id, nome,preco,fornecedor,qtestoque, estoqueraz,  datacadastro FROM produto WHERE situacao <> 0 and nome ilike ? order by id desc";
            PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL);
             pstmt.setString(1, "%"+nome+"%");
            try ( //executa
                ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {

                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setDataCadastro(rs.getString("datacadastro"));
                    p.setEstoqueRaz(rs.getFloat("estoqueraz"));
                    p.setQtEstoque(rs.getFloat("qtestoque"));
                
                    //add na lista
                    produtos.add(p);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
        
        
    }

 




}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.model.Fornecedor;
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
public class FornecedorDAO implements FornecedorDAOInterface {
    
    Connection connection;


    public FornecedorDAO() throws SQLException, ClassNotFoundException {
        this.connection = ConnectionFactory.getConexao();
    }
    

    @Override
    public int selectCurr(){
        final String CURRSEQ = "SELECT CURRVAL ('id_fornecedor_sequence')";
        
        PreparedStatement ps;  
        try {
            
            ps = connection.prepareCall(CURRSEQ);
            ResultSet rs = ps.executeQuery();
            return rs.getInt("currval");
            
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return 0;
    };
          
    
    @Override
    public boolean inserir(Fornecedor p) {
        
        final String INSERT = "insert into fornecedor "
                + "(datacadastro, nome, situacao,"
                + " cnpj, email, telefone, celular)"
              //  + " pais,"
              //  + " estado, cidade, bairro, rua, numero, cep)"
                +"values(?, ?, 1, ?, ?, ?, ?);";//??????



        try {
            PreparedStatement ps = connection.prepareCall(INSERT);
            ps.setString(1, p.getDataCadastro());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getCnpj());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getTelefone());
            ps.setString(6, p.getCelular());
       /*   ps.setString(7, p.getEndereco().getPais());
            ps.setString(8, p.getEndereco().getUF());
            ps.setString(9, p.getEndereco().getCidade());
            ps.setString(10, p.getEndereco().getBairro());
            ps.setString(11, p.getEndereco().getLogradouro());
            ps.setString(12, p.getEndereco().getNumero());
            ps.setString(13, p.getEndereco().getCep());*/
        
            //execute Update retorna um inteiro diferente do executeQuery que retorna um resultSet(dados da consulta)
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
        
    }

    @Override
    public boolean alterar(Fornecedor p) {

        final String ALTERAR = "update fornecedor set nome = ?,  cnpj = ?, email = ?, telefone = ?,"
                + " celular = ?  where id = ?";

        try {

            PreparedStatement ps = connection.prepareCall(ALTERAR);
            
            ps.setString(1, p.getNome());
            ps.setString(2, p.getCnpj());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getTelefone());
            ps.setString(5, p.getCelular());
            ps.setInt(6, p.getId());
            
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
        
        
    }

    @Override
    public boolean excluir(Fornecedor p) {

        final String DELETE = "update fornecedor set situacao = 0 where id = ?";

        try {

            PreparedStatement ps = connection.prepareCall(DELETE);
            //execute Update retorna um inteiro diferente do executeQuery que retorna um resultSet(dados da consulta)
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    @Override
    public ArrayList<Fornecedor> listar() {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        try {
            final String SELECT_ALL = "SELECT id, nome,  datacadastro FROM fornecedor WHERE situacao <> 0 order by id desc";
            //cria uma array de obJ Fornecedor

            //cria comando SQL
            PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL);
            try ( //executa
                ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {

                    Fornecedor p = new Fornecedor();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setDataCadastro(rs.getString("dataCadastro"));
                
                    //add na lista
                    fornecedores.add(p);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fornecedores;
    }
    

    @Override
    public Fornecedor buscar(Fornecedor p) {
        
        final String BUSCAR = "SELECT * FROM fornecedor WHERE ID = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(BUSCAR);

            pstmt.setInt(1, p.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

      
            p.setNome(rs.getString("nome"));
            p.setCnpj(rs.getString("cnpj"));
            p.setEmail(rs.getString("email"));
            p.setTelefone(rs.getString("telefone"));
            p.setCelular(rs.getString("celular"));
  
          //  p.getEndereco().setPais(rs.getString("pais"));
          //  p.getEndereco().setUF(rs.getString("estado"));
          //  p.getEndereco().setCidade(rs.getString("cidade"));
          //  p.getEndereco().setLogradouro(rs.getString("rua"));
          //  p.getEndereco().setNumero(rs.getString("numero"));
          //  p.getEndereco().setCep(rs.getString("cep"));    
         //   p.getEndereco().setComplemento(rs.getString("complemento"));
      
            
        }catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return p;
    }


    
    
}

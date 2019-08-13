/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.model.Cliente;
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
public class ClienteDAO implements ClienteDAOInterface {

    Connection connection;

    public ClienteDAO() throws SQLException, ClassNotFoundException {
        //cria-se o construtor e faz a conexão com o banco aqui e seta o connection
        connection = ConnectionFactory.getConexao();
    }

    @Override
    public int selectCurr(){
        final String CURRSEQ = "SELECT CURRVAL ('id_cliente_sequence')";
        
        PreparedStatement ps;  
        try {
            
            ps = connection.prepareCall(CURRSEQ);
            ResultSet rs = ps.executeQuery();
            return rs.getInt("currval");
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return 0;
    };
          
    
    @Override
    public boolean inserir(Cliente p) {
        
        final String INSERT = "insert into cliente (nome, sobrenome, sexo, datacadastro, situacao, rg,cpf, email, telefone, celular)"
                + "  values (?,?,?,?,1, ?,?,?,?,?);";

        try {
            PreparedStatement ps = connection.prepareCall(INSERT);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getSobrenome());
            ps.setString(3, p.getSexo());
            ps.setString(4, p.getDataCadastro());
            ps.setString(5, p.getRg());
            ps.setString(6, p.getCpf());
            ps.setString(7, p.getEmail());
            ps.setString(8, p.getTelefone());
            ps.setString(9, p.getCelular());
         
            
            //execute Update retorna um inteiro diferente do executeQuery que retorna um resultSet(dados da consulta)
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
        
    }

    @Override
    public boolean alterar(Cliente p) {

        final String ALTERAR = "update cliente set nome = ?,  sobrenome = ?, sexo = ?,"
                + " situacao = ?, rg = ?,cpf = ?, email = ?, telefone = ?, celular = ? where id = ?";

        try {

            PreparedStatement ps = connection.prepareCall(ALTERAR);
            
            ps.setString(1, p.getNome());
            ps.setString(2, p.getSobrenome());
            ps.setString(3, p.getSexo());
            ps.setInt(4, 1);
            ps.setString(5, p.getRg());
            ps.setString(6, p.getCpf());
            ps.setString(7, p.getEmail());
            ps.setString(8, p.getTelefone());
            ps.setString(9, p.getCelular());
            ps.setInt(10, p.getId());
            
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
        
        
    }

    @Override
    public boolean excluir(Cliente p) {

        final String DELETE = "update cliente set situacao = 0 where id = ?";

        try {

            PreparedStatement ps = connection.prepareCall(DELETE);
            //execute Update retorna um inteiro diferente do executeQuery que retorna um resultSet(dados da consulta)
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return true;
    }

    @Override
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            final String SELECT_ALL = "SELECT id, nome, sobrenome, datacadastro FROM cliente WHERE situacao <> 0 order by id desc";
            //cria uma array de obJ Cliente

            //cria comando SQL
            PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL);
            try ( //executa
                ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {

                    Cliente p = new Cliente();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setSobrenome(rs.getString("sobrenome"));
                    p.setDataCadastro(rs.getString("dataCadastro"));
                
                    //add na lista
                    clientes.add(p);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
    

    @Override
    public Cliente buscar(Cliente p) {
        
        final String BUSCAR = "SELECT * FROM cliente WHERE ID = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(BUSCAR);

            pstmt.setInt(1, p.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

      
            p.setNome(rs.getString("nome"));
            p.setSobrenome(rs.getString("sobrenome"));
            p.setRg(rs.getString("rg"));
            p.setCpf(rs.getString("cpf"));
            p.setSexo(rs.getString("sexo"));
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return p;
    }

   



}
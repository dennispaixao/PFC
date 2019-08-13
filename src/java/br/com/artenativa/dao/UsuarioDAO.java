/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.AutorizacaoDeAcesso.PerfilDeAcesso;
import br.com.artenativa.model.Usuario;
import br.com.artenativa.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static br.com.artenativa.util.MD5.md5;


/**
 *
 * @author Dennis
 */
public class UsuarioDAO implements UsuarioDAOInterface{
    Connection connection;
  
    
    
    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        //cria-se o construtor e faz a conexão com o banco aqui e seta o connection
        connection =  ConnectionFactory.getConexao();
    }
    
    
    

    public boolean inserir(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    @Override
    public boolean alterar(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario buscar(Usuario u) {
        final String BUSCAR = "SELECT * FROM usuario where id = ?";
        try{
            
            PreparedStatement pstmt = connection.prepareStatement(BUSCAR);
            pstmt.setInt(1, u.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setNivel(PerfilDeAcesso.valueOf(rs.getString("nivel")));
               
            }
            return u;
        }catch(SQLException e){
            return null;
        }
        
    }

    
    
    @Override
    public Usuario autenticar(Usuario u) {
        final String AUTENTICAR = "SELECT * FROM usuario WHERE nome = ? and senha = ?";
            try{   
            PreparedStatement pstmt = connection.prepareStatement(AUTENTICAR);
            pstmt.setString(1, u.getNome());
            pstmt.setString(2, md5(u.getSenha()));
            //executa
            ResultSet rs = pstmt.executeQuery();          
            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setNivel(PerfilDeAcesso.valueOf(rs.getString("nivel")));
                return u;
            }else{
            return null;    
            }
            
        }catch(SQLException e){
            return null;
        }
    }
    
}

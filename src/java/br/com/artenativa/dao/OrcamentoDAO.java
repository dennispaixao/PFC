/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.dao;

import br.com.artenativa.model.Cliente;
import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.model.Usuario;
import br.com.artenativa.util.ConnectionFactory;
import br.com.artenativa.util.ParseDates;
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
public class OrcamentoDAO {

    Connection connection;

    public OrcamentoDAO() throws SQLException, ClassNotFoundException {
        //cria-se o construtor e faz a conexão com o banco aqui e seta o connection
  
    }

    public int inserir(Orcamento o) throws SQLException, ClassNotFoundException {
        connection = ConnectionFactory.getConexao();
        final String INSERIR = "insert into orcamento (idCliente, idResponsavel, dataInsercao, dataInicio, dataPrevista, relatorio, status)  values (?,?,?,?,?,?,1) returning id";
        PreparedStatement ps;
        ps = connection.prepareCall(INSERIR);
        ps.setInt(1, o.getCliente().getId());
        ps.setInt(2, o.getResponsavel().getId());
        ps.setString(3, ParseDates.getNowUnix().toString());
        ps.setString(4, o.getDataInicio());
        ps.setString(5, o.getDataPrevista());
        ps.setString(6, o.getRelatorio());
        ResultSet rs = ps.executeQuery();
        rs.next();
        connection.close();
        return rs.getInt("id");
    }

    public ArrayList<Orcamento> listar() throws SQLException, ClassNotFoundException {
              connection = ConnectionFactory.getConexao();
        ArrayList<Orcamento> orcamentos = new ArrayList<>();
        final String SELECT_ALL = "SELECT * from orcamento order by datainsercao";
        PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Orcamento o = new Orcamento();
            o.setId(rs.getInt("id"));
            o.setResponsavel(new Usuario(rs.getInt("idresponsavel")));
            o.setDataInsercao(rs.getString("datainsercao"));
            o.setDataInicio(rs.getString("datainicio"));
            o.setDataPrevista(rs.getString("dataprevista"));
            o.setRelatorio(rs.getString("relatorio"));
            o.setEstado(rs.getInt("status"));
            orcamentos.add(o);
        }
        connection.close();
        return orcamentos;
    }

    public ArrayList<Orcamento> listar(int i) throws SQLException, ClassNotFoundException {
              connection = ConnectionFactory.getConexao();
        ArrayList<Orcamento> orcamentos = new ArrayList<>();
        final String SELECT_ALL = "SELECT * from orcamento where status= ? order by datainsercao desc";
        PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL);
        pstmt.setInt(1, i);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Orcamento o = new Orcamento();
            o.setId(rs.getInt("id"));
            o.setCliente(new Cliente(rs.getInt("idcliente")));
            o.setResponsavel(new Usuario(rs.getInt("idresponsavel")));
            o.setDataInsercao(rs.getString("datainsercao"));
            o.setDataInicio(rs.getString("datainicio"));
            o.setDataPrevista(rs.getString("dataprevista"));
            o.setRelatorio(rs.getString("relatorio"));
            o.setEstado(rs.getInt("status"));
            orcamentos.add(o);
        }
        connection.close();
        return orcamentos;
    }
    
    
    public boolean excluir(Orcamento o) throws SQLException, ClassNotFoundException {
              connection = ConnectionFactory.getConexao();
        final String DELETE = "update orcamento set status =0 where id =?";
        PreparedStatement pstmt = connection.prepareStatement(DELETE);
        pstmt.setInt(1, o.getId());
        int rs = pstmt.executeUpdate();
        if(rs>0){
            connection.close();
            return true;
        }else{
            connection.close();
            return false;
        }
  
    }
    
       public Orcamento buscar(Orcamento o) throws SQLException, ClassNotFoundException {
        connection = ConnectionFactory.getConexao();
     
        final String BUSCAR = "SELECT * from orcamento where id = ?";
        PreparedStatement pstmt = connection.prepareStatement(BUSCAR);
        pstmt.setInt(1, o.getId());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
            o.setId(rs.getInt("id"));
            Cliente cli = new Cliente(rs.getInt("idcliente"));
            o.setCliente(new  ClienteDAO().buscar(cli));
            o.setResponsavel(new UsuarioDAO().buscar(new Usuario(rs.getInt("idresponsavel"))));
            o.setRelatorio(rs.getString("relatorio"));
            o.setDataInsercao(rs.getString("datainsercao"));
            o.setDataInicio(rs.getString("datainicio"));
            o.setDataPrevista(rs.getString("dataprevista"));
            o.setDataFim(rs.getString("datafim"));
            o.setValor(rs.getFloat("total"));
            o.setTotalPago(rs.getFloat("totalpago"));
            o.setEstado(rs.getInt("status"));
        connection.close();
        return o;
    }
       
       
    public boolean alterar(Orcamento o) throws SQLException, ClassNotFoundException {
        connection = ConnectionFactory.getConexao();
        final String ALTERAR = "update orcamento set idcliente = ?, idresponsavel = ?, relatorio = ?,"
        +" datainsercao = ?, datainicio=?, dataprevista=?, datafim =?, total =?, totalpago =?, status =? where id = ?";
        try {
            PreparedStatement ps = connection.prepareCall(ALTERAR);
            //execute Update retorna um inteiro diferente do executeQuery que retorna um resultSet(dados da consulta)
            ps.setInt(1, o.getCliente().getId());
            ps.setInt(2, o.getResponsavel().getId());
            ps.setString(3, o.getRelatorio());
            ps.setString(4, o.getDataInsercao());
            ps.setString(5, o.getDataInicio());
            ps.setString(6, o.getDataPrevista());
            ps.setString(7, o.getDataFim());
            ps.setDouble(8, o.getValor());
            ps.setDouble(9, o.getTotalPago());
            ps.setInt(10, o.getEstado());
            ps.setInt(11, o.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return false;
    }
}
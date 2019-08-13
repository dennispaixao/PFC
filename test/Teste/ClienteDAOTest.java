/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;


import br.com.artenativa.dao.ClienteDAO;
import br.com.artenativa.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import br.com.artenativa.util.ConnectionFactory;

/**
 *
 * @author wolley
 */
public class ClienteDAOTest {
    
    private Connection conexao;
    private ClienteDAO dao,d, d1, d2;
    
    @Before
    public void inicializa() throws SQLException, ClassNotFoundException{
       zeraRegistrosDaTabelaCliente();
       dao = new ClienteDAO();
       d = new ClienteDAO();
       d1 = new ClienteDAO();
       d2 = new ClienteDAO();
    }

    @After
    public void finaliza() throws SQLException{
       zeraRegistrosDaTabelaCliente();
    }
   
    @Test
    public void deveCadastrarNovoCliente(){
        
        Cliente cliente = new Cliente();

        cliente.setNome("maria");
        cliente.setSobrenome("conceição");
        cliente.setSexo("f");
        cliente.setRg("415241521");
        cliente.setCpf("21245745485");
        cliente.setEmail("dennispaixao@handks.com");
        cliente.setTelefone("1154587548");
        cliente.setCelular("112521452");
        dao.inserir(cliente);
        
        Cliente clienteCadastrado =  d.buscar(new Cliente(1));
        
        assertEquals("maria", clienteCadastrado.getNome());
        assertEquals("conceição", clienteCadastrado.getSobrenome());
        assertEquals("f", clienteCadastrado.getSexo());
        assertEquals("415241521", clienteCadastrado.getRg());
        assertEquals("21245745485", clienteCadastrado.getCpf());
        assertEquals("dennispaixao@handks.com", clienteCadastrado.getEmail());
        assertEquals("1154587548", clienteCadastrado.getTelefone());
        assertEquals("112521452", clienteCadastrado.getCelular());
  

    }
 
    @Test
    public void deveAtualizarUmCliente(){
      
        Cliente cliente = new Cliente();
        cliente.setNome("maria");
        cliente.setSobrenome("conceição");
        cliente.setSexo("f");
        cliente.setRg("415241521");
        cliente.setCpf("21245745485");
        cliente.setEmail("dennispaixao@handks.com");
        cliente.setTelefone("1154587548");
        cliente.setCelular("112521452");

        
        dao.inserir(cliente);
        
        Cliente clienteParaAtualizar  = d.buscar(new Cliente(1));
        
        
        d1.alterar(clienteParaAtualizar); 
        Cliente clienteAtualizado = d2.buscar(new Cliente(1));
         
        assertEquals("maria", clienteAtualizado.getNome());
        assertEquals("conceição", clienteAtualizado.getSobrenome());
        assertEquals("f", clienteAtualizado.getSexo());
        assertEquals("415241521", clienteAtualizado.getRg());
        assertEquals("21245745485", clienteAtualizado.getCpf());
        assertEquals("dennispaixao@handks.com", clienteAtualizado.getEmail());
        assertEquals("1154587548", clienteAtualizado.getTelefone());
        assertEquals("112521452", clienteAtualizado.getCelular());
  
    }
          

    
   @Test
    public void  deveExcluirUmCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("maria");
        cliente.setSobrenome("conceição");
        cliente.setSexo("f");
        cliente.setRg("415241521");
        cliente.setCpf("21245745485");
        cliente.setEmail("dennispaixao@handks.com");
        cliente.setTelefone("1154587548");
        cliente.setCelular("112521452");

    
        dao.inserir(cliente);
        
        Cliente clienteParaExcluir = dao.buscar(new Cliente(1));
        dao.excluir(clienteParaExcluir);
        
        assertEquals(0, dao.listar().size());

    }

    private void zeraRegistrosDaTabelaCliente() {   
        try{
            
            conexao = ConnectionFactory.getConexao();
            
            String sql = "TRUNCATE table cliente";
            
            PreparedStatement pstmt = conexao.prepareStatement(sql);
   
            pstmt.execute();
            
            String ZeraSequence="ALTER SEQUENCE id_cliente_sequence RESTART 1;";
            
            pstmt = conexao.prepareStatement(ZeraSequence);
            pstmt.execute();
            
            conexao.close();
            
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
        
        
        
        
        
    }
    
    
}

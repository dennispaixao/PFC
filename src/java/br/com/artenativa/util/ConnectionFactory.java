/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dennis
 */
public class ConnectionFactory {
    //adiciona importação para java.sql.Connection
 public static Connection getConexao() throws SQLException, ClassNotFoundException {  
	     try {  
	             Class.forName("org.postgresql.Driver");  
	             return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbartenativa","postgres","postgres");  
	     }  
	     catch (SQLException e) {
                    throw new RuntimeException("Não foi possível conectar ao banco: Erro SQLException",e);
	     
             }  catch(ClassNotFoundException e){
                    throw new RuntimeException("Não foi possível conectar ao banco: Erro na Classe ConnectionFactory");
                 
             }
	} 
}

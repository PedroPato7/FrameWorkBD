package com.meu.framework.connection;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public interface Conexao {

	Connection connect() throws SQLException;
	void disconnect(Connection connection) throws SQLException;
	void executeUpdate(String query) throws SQLException;
	
	
	/*
	 
	 final static String NOME_DO_BANCO = "***";
    public static Connection conectar() {
    	try {
    		Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://localhost/" + NOME_DO_BANCO;  Local da area do arquivo
            return DriverManager.getConnection(url,"root","mysql");
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }
    
    */
}

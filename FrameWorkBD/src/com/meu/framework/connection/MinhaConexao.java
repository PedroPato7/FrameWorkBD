package com.meu.framework.connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class MinhaConexao implements Conexao {
	
	private String url;
	private String usuario;
	private String senha;
	
	public MinhaConexao(String url, String usuario, String senha) {
		this.url = url;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	// Faz a conexão
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(url, usuario, senha);
	}
	//Encerra a conexão
	public void disconnect(Connection connection) throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
	public void executeUpdate(String query) throws SQLException {
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }
	
}

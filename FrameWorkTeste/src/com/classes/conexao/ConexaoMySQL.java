package com.classes.conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoMySQL implements ConexaoBD {
		private String host;
	    private int port;
	    private String database;
	    private String usuario;
	    private String senha;

	 // Construtor para inicializar as variáveis de conexão
	    public ConexaoMySQL(String host, int port, String database, String usuario, String senha) {
	        this.host = host;
	        this.port = port;
	        this.database = database;
	        this.usuario = usuario;
	        this.senha = senha;
	    }
	    
	    public Connection conectar() throws SQLException {
	        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
	        return DriverManager.getConnection(url, usuario, senha);
	    }

	    public Connection conectarSemBD() throws SQLException {
	        String url = "jdbc:mysql://" + host + ":" + port;
	        return DriverManager.getConnection(url, usuario, senha);
	    }

	    public void desconectar(Connection connection) throws SQLException {
	        if (connection != null && !connection.isClosed()) {
	            connection.close();
	        }
	    }

	    public void executarAtualizacao(String sql) throws SQLException {
	        try (Connection connection = conectar(); Statement statement = connection.createStatement()) {
	            statement.executeUpdate(sql);
	        }
	    }

	    public void executarAtualizacaoSemBD(String sql) throws SQLException {
	        try (Connection connection = conectarSemBD(); Statement statement = connection.createStatement()) {
	            statement.executeUpdate(sql);
	        }
	    }

	    public void executarScript(String script) throws SQLException {
	        try (Connection connection = conectar(); Statement statement = connection.createStatement()) {
	            for (String sql : script.split(";")) {
	                if (!sql.trim().isEmpty()) {
	                    statement.execute(sql);
	                }
	            }
	        }
	    }
	}
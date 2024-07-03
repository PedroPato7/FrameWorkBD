package com.meu.framework.connection;

public class FabricaConexao {
	    public static Conexao criarConexao(String tipo, String url, String usuario, String senha) {
	        if ("MySQL".equalsIgnoreCase(tipo)) {
	            return new MinhaConexao(url, usuario, senha);
	        }
	        // Adicione outros tipos de banco de dados aqui, se necessário.
	        throw new IllegalArgumentException("Tipo de banco de dados desconhecido: " + tipo);
	    }
	}


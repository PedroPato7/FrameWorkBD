package com.classes.conexao;

public class FabricaConexao {
	 public static ConexaoBD criarConexao(String tipo, String host, int port, String nomeBanco, String usuario, String senha) {
	        if ("MySQL".equalsIgnoreCase(tipo)) {
	            return new ConexaoMySQL(host, port, nomeBanco, usuario, senha);
	        }
	        // Adicione outros tipos de banco de dados aqui, se necessário.
	        throw new IllegalArgumentException("Tipo de banco de dados desconhecido: " + tipo);
	    }
}
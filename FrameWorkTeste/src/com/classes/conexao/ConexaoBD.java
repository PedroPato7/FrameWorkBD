package com.classes.conexao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConexaoBD {

	 Connection conectar() throws SQLException;
	 	void desconectar(Connection conexao) throws SQLException;
	    void executarAtualizacao(String query) throws SQLException;
	    void executarScript(String script) throws SQLException;
}
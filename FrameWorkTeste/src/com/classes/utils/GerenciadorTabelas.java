package com.classes.utils;

import com.classes.conexao.ConexaoMySQL;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorTabelas {
	 private ConexaoMySQL conexao;

	    public GerenciadorTabelas(ConexaoMySQL conexao) {
	        this.conexao = conexao;
	    }

	    public void criarTabela(String nomeTabela, List<String> colunas, List<String> tipos, List<String> chavesEstrangeiras) throws SQLException {
	        // Monta a string de criação da tabela
	        String sql = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " (";
	        sql += colunas.stream()
	                      .map(coluna -> coluna + " " + tipos.get(colunas.indexOf(coluna)))
	                      .collect(Collectors.joining(", "));

	        // Adiciona chaves estrangeiras, se existirem
	        if (!chavesEstrangeiras.isEmpty()) {
	            sql += ", " + String.join(", ", chavesEstrangeiras);
	        }
	        sql += ")";

	        // Executa a atualização SQL para criar a tabela
	        conexao.executarAtualizacao(sql);
	    }

	    public void inserirDados(String nomeTabela, List<String> colunas, List<String> valores) throws SQLException {
	        // Monta a string de inserção de dados
	        String sql = "INSERT INTO " + nomeTabela + " (";
	        sql += String.join(", ", colunas) + ") VALUES ('";
	        sql += String.join("', '", valores) + "')";

	        // Executa a atualização SQL para inserir os dados
	        conexao.executarAtualizacao(sql);
	    }

	    public void atualizarDados(String nomeTabela, String setClause, String whereClause) throws SQLException {
	        // Monta a string de atualização de dados
	        String sql = "UPDATE " + nomeTabela + " SET " + setClause + " WHERE " + whereClause;

	        // Executa a atualização SQL para atualizar os dados
	        conexao.executarAtualizacao(sql);
	    }

	    public void deletarDados(String nomeTabela, String whereClause) throws SQLException {
	        // Monta a string de deleção de dados
	        String sql = "DELETE FROM " + nomeTabela + " WHERE " + whereClause;

	        // Executa a atualização SQL para deletar os dados
	        conexao.executarAtualizacao(sql);
	    }

	    public void excluirTabela(String nomeTabela) throws SQLException {
	        // Monta a string de exclusão da tabela
	        String sql = "DROP TABLE " + nomeTabela;

	        // Executa a atualização SQL para excluir a tabela
	        conexao.executarAtualizacao(sql);
	    }
}
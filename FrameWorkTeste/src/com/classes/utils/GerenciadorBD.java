package com.classes.utils;

import com.classes.conexao.ConexaoMySQL;

import java.sql.SQLException;

public class GerenciadorBD {
	 	private ConexaoMySQL conexao;

	    public GerenciadorBD(ConexaoMySQL conexao) {
	        this.conexao = conexao;
	    }

	    public void criarBancoDeDados(String nomeBanco) throws SQLException {
	        String sql = "CREATE DATABASE IF NOT EXISTS " + nomeBanco;
	        conexao.executarAtualizacaoSemBD(sql);
	    }

	    public void excluirBancoDeDados(String nomeBanco) throws SQLException {
	        String sql = "DROP DATABASE IF EXISTS " + nomeBanco;
	        conexao.executarAtualizacaoSemBD(sql);
	    }
	}
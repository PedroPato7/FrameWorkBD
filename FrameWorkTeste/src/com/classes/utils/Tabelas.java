package com.classes.utils;

import com.classes.conexao.ConexaoBD;

import java.sql.SQLException;

public class Tabelas {
    private ConexaoBD conexao;

    public Tabelas(ConexaoBD conexao) {
        this.conexao = conexao;
    }

    public void criarTabela(String criarTabelaSQL) throws SQLException {
        conexao.executarAtualizacao(criarTabelaSQL);
    }

    public void excluirTabela(String nomeTabela) throws SQLException {
        String excluirTabelaSQL = "DROP TABLE IF EXISTS " + nomeTabela;
        conexao.executarAtualizacao(excluirTabelaSQL);
    }

    public void criarTabelasComChavesEstrangeiras(String... criarTabelasSQL) throws SQLException {
        for (String criarTabelaSQL : criarTabelasSQL) {
            conexao.executarAtualizacao(criarTabelaSQL);
        }
    }
} 
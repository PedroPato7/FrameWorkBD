package com.meu.framework.utils;

import com.meu.framework.connection.Conexao;

import java.sql.SQLException;

public class Tabelas {
    private Conexao conexao;

    public Tabelas(Conexao conexao) {
        this.conexao = conexao;
    }

    public void criarTabelas(String createTableSQL) throws SQLException {
        conexao.executeUpdate(createTableSQL);
    }

    public void dropTable(String tableName) throws SQLException {
        String dropTableSQL = "DROP TABLE IF EXISTS " + tableName;
        conexao.executeUpdate(dropTableSQL);
    }
}


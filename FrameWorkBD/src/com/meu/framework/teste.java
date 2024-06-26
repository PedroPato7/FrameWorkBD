package com.meu.framework;

import java.sql.SQLException;

import com.meu.framework.connection.Conexao;
import com.meu.framework.connection.MinhaConexao;
import com.meu.framework.dao.Usuario;
import com.meu.framework.dao.UsuarioDAO;

public class teste {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/frameworkBD";
		String usuario = "admin";
		String senha = "admin123";
		
		Conexao conexao = new MinhaConexao(url, usuario, senha);
		UsuarioDAO userDAO = new UsuarioDAO(conexao);
		
		try {
			// Cria um novo usuario
			Usuario novoUsuario = new Usuario(0, "Pedro", "pedroo-phd@outlook.com");
			userDAO.create(novoUsuario);
			
			// Vai ler um usuario
			Usuario user = userDAO.read(1);
			System.out.println("Usuário lido: " + user.getNome() + " - " + user.getEmail());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}

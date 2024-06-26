package com.meu.framework.dao;

import com.meu.framework.connection.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements GenericDAO<Usuario> {

	private Conexao conexao;
	
	//Constructor que permite a interação com o banco.
	public UsuarioDAO(Conexao conexao) {
		this.conexao = conexao;
	}
	
	//Insere um novo usuario.
	public void create(Usuario user) throws SQLException{
		/*
		Está variavel 'query' é o começo da estrutura que eu quero para criar, atualizar, ler e etc.
		Os '?' são as partes que vão ser trocadas pelas informações inseridas.	
		*/
		String query = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
		try (Connection connection = conexao.connect();
				PreparedStatement statement = connection.prepareStatement(query)){
			// Aqui é onde o PreparedStatement troca e executa os dados.
			statement.setString(1, user.getNome());
			statement.setString(2, user.getEmail());
			statement.executeUpdate();
		}		
	}
	// Função para ler as informações de um usuario.
	public Usuario read(int id) throws SQLException {
		String query = "SELECT * FROM usuarios WHERE id = ?";
		try (Connection connection = conexao.connect();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return new Usuario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"));
			}
		}
		return null;
	}
	// Função para atualizar um usuario.
	public void update(Usuario user) throws SQLException {
		String query = "UPDATE usuarios SET nome = ?, email = ?, WHERE id = ?";
		try (Connection connection = conexao.connect();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1, user.getNome());
			statement.setString(2, user.getEmail());
			statement.setInt(3, user.getId());
			statement.executeUpdate();			
		}
	}
	// Função para deletar um usuario.
	public void delete(int id) throws SQLException {
		String query = "DELETE FROM usuarios WHERE id = ?";
		try (Connection connection = conexao.connect();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		}
	}
	
	// Função para ver todos os usuarios da tabela.
	@Override
	public List<Usuario> getALL() throws SQLException {
		List<Usuario> users = new ArrayList<>();
		String query = "SELECT * FROM users";
		try (Connection connection = conexao.connect();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)){
			while (resultSet.next()) {
				users.add(new Usuario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email")));
			}
		}
		return users;
	}	
}

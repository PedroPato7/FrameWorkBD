package com.meu.framework;

import java.sql.SQLException;

import com.meu.framework.connection.Conexao;
import com.meu.framework.connection.FabricaConexao;
import com.meu.framework.connection.MinhaConexao;
import com.meu.framework.dao.Usuario;
import com.meu.framework.dao.UsuarioDAO;
import com.meu.framework.utils.Tabelas;

public class teste {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/FrameWorkBD";
		String usuario = "root";
		String senha = "";
		
		// Cria a conexão com o banco de dados
        Conexao conexao = FabricaConexao.criarConexao("MySQL", url, usuario, senha);

        // Cria uma instância do TableManager
        Tabelas tabelas = new Tabelas(conexao);

        // Define as SQLs para criar as tabelas
        String criarTabelaUsuario = "CREATE TABLE usuarios (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100) NOT NULL)";

        String criarTabelaOrdem = "CREATE TABLE ordem (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "usuarios_id INT NOT NULL, " +
                "ordem_data DATE NOT NULL, " +
                "FOREIGN KEY (usuarios_id) REFERENCES usuarios(id))";

        try {
            // Cria as tabelas
            tabelas.criarTabelas(criarTabelaUsuario);
            System.out.println("Tabelas criadas com sucesso.");

            // Cria uma instância do UserDAO
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

            // Cria um novo usuário
            Usuario novoUsuario = new Usuario(0, "João Silva", "joao.silva@example.com");
            usuarioDAO.create(novoUsuario);

            // Lê um usuario
            Usuario user = usuarioDAO.read(1);
            System.out.println("Usuario lido: " + user.getNome() + " - " + user.getEmail());

            // Atualiza um usuário
            user.setNome("João Atualizado");
            usuarioDAO.update(user);

            // Deleta um usuário
            usuarioDAO.delete(1);

            // Lista todos os usuários
            for (Usuario u : usuarioDAO.getALL()) {
                System.out.println(u.getId() + ": " + u.getNome() + " - " + u.getEmail());
            }

            // Exclui as tabelas
            tabelas.dropTable("ordem");
            tabelas.dropTable("usuarios");
            System.out.println("Tabelas excluídas com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Desconecta do banco de dados
                conexao.disconnect(conexao.connect());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

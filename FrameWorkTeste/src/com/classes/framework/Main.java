package com.classes.framework;

import com.classes.conexao.ConexaoMySQL;
import com.classes.utils.GerenciadorBD;
import com.classes.utils.GerenciadorTabelas;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	 public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
		 	
		 	int port;
		 	String host;
	        String usuario = "root";
	        String senha = "";
	        
	        String resposta;
	        
	        // Solicita configurações do banco de dados ao usuário
	        System.out.print("Digite o host (localhost): "); // Entre () será mostrado informações que já estão setadas por padrão.
	        resposta = scanner.nextLine();
	        if (resposta == "") {
			 	host = "localhost";
	        } else {
	        	host = resposta;
	        }
	        
	        System.out.print("Digite a porta (3306): ");
	        resposta = scanner.nextLine();
	        if (resposta == "") {
	        	port = 3306;
	        } else {
	        	port = Integer.parseInt(resposta);
	        }
	        
	        System.out.print("Digite o nome do banco de dados: ");
	        String nomeBanco = scanner.nextLine();
	        
	        System.out.print("Digite o usuário do banco de dados (root): ");
	        resposta = scanner.nextLine();
	        if (resposta == "") {
	        	usuario = "root";
	        } else {
	        	usuario = resposta;
	        }
	        
	        System.out.print("Digite a senha do banco de dados (pode ser nulo): ");
	        resposta = scanner.nextLine();
	        if (resposta == "") {
	        	senha = "";
	        } else {
	        	senha = resposta;
	        }
	        
	     // Cria uma instância de conexão com o banco de dados
	        ConexaoMySQL conexaoBanco = new ConexaoMySQL(host, port, nomeBanco, usuario, senha);

	        // Cria gerenciadores para banco de dados e tabelas
	        GerenciadorBD gerenciadorBancoDeDados = new GerenciadorBD(conexaoBanco);
	        GerenciadorTabelas gerenciadorTabelas = new GerenciadorTabelas(conexaoBanco);

	        try {
	            // Cria o banco de dados (ou verifica se já existe)
	            gerenciadorBancoDeDados.criarBancoDeDados(nomeBanco);
	            System.out.println("Banco de dados criado ou já existente.");

	            boolean continuar = true;
	            while (continuar) {
	                // Menu de opções para o usuário
	                System.out.println("\nEscolha uma opção:");
	                System.out.println("1. Criar tabela");
	                System.out.println("2. Inserir dados na tabela");
	                System.out.println("3. Atualizar dados na tabela");
	                System.out.println("4. Deletar dados na tabela");
	                System.out.println("5. Deletar tabela");
	                System.out.println("6. Deletar banco de dados");
	                System.out.println("7. Sair");

	                int opcao = Integer.parseInt(scanner.nextLine());

	                switch (opcao) {
	                    case 1:
	                        criarTabela(scanner, gerenciadorTabelas);
	                        break;
	                    case 2:
	                        inserirDados(scanner, gerenciadorTabelas);
	                        break;
	                    case 3:
	                        atualizarDados(scanner, gerenciadorTabelas);
	                        break;
	                    case 4:
	                        deletarDados(scanner, gerenciadorTabelas);
	                        break;
	                    case 5:
	                        System.out.print("Digite o nome da tabela a ser deletada: ");
	                        String nomeTabela = scanner.nextLine();

	                        gerenciadorTabelas.excluirTabela(nomeTabela);
	                        System.out.println("Tabela deletada com sucesso.");
	                        break;
	                    case 6:
	                        System.out.print("Tem certeza de que deseja deletar o banco de dados? (s/n): ");
	                        if (scanner.nextLine().equalsIgnoreCase("s")) {
	                            gerenciadorBancoDeDados.excluirBancoDeDados(nomeBanco);
	                            System.out.println("Banco de dados deletado com sucesso.");
	                        }
	                        break;
	                    case 7:
	                        continuar = false;
	                        break;
	                    default:
	                        System.out.println("Opção inválida. Tente novamente.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void criarTabela(Scanner scanner, GerenciadorTabelas gerenciadorTabelas) throws SQLException {
	        System.out.print("Digite o nome da tabela: ");
	        String nomeTabela = scanner.nextLine();

	        List<String> colunas = new ArrayList<>();
	        List<String> tipos = new ArrayList<>();
	        List<String> chavesEstrangeiras = new ArrayList<>();
	        List<String> colunasFk = new ArrayList<>();

	        boolean adicionarColuna = true;
	        while (adicionarColuna) {
	            System.out.print("Digite o nome da coluna: ");
	            String nomeColuna = scanner.nextLine();
	            colunas.add(nomeColuna);
	            System.out.print("Digite o tipo da coluna (ex: INT, VARCHAR(100)): ");
	            tipos.add(scanner.nextLine());

	            System.out.print("Deseja adicionar outra coluna? (s/n): ");
	            adicionarColuna = scanner.nextLine().equalsIgnoreCase("s");
	        }

	        System.out.print("Deseja adicionar uma chave estrangeira? (s/n): ");
	        boolean adicionarChaveEstrangeira = scanner.nextLine().equalsIgnoreCase("s");
	        while (adicionarChaveEstrangeira) {
	            System.out.print("Digite o nome da coluna de chave estrangeira: ");
	            String colunaFk = scanner.nextLine();
	            if (!colunas.contains(colunaFk)) {
	                colunas.add(colunaFk);
	                System.out.print("Digite o tipo da coluna (ex: INT): ");
	                tipos.add(scanner.nextLine());
	            }
	            colunasFk.add(colunaFk);
	            System.out.print("Digite o nome da tabela referenciada: ");
	            String tabelaReferenciada = scanner.nextLine();
	            System.out.print("Digite o nome da coluna referenciada: ");
	            String colunaReferenciada = scanner.nextLine();

	            chavesEstrangeiras.add("FOREIGN KEY (" + colunaFk + ") REFERENCES " + tabelaReferenciada + "(" + colunaReferenciada + ")");

	            System.out.print("Deseja adicionar outra chave estrangeira? (s/n): ");
	            adicionarChaveEstrangeira = scanner.nextLine().equalsIgnoreCase("s");
	        }

	        gerenciadorTabelas.criarTabela(nomeTabela, colunas, tipos, chavesEstrangeiras);
	        System.out.println("Tabela criada com sucesso.");
	    }

	    private static void inserirDados(Scanner scanner, GerenciadorTabelas gerenciadorTabelas) throws SQLException {
	        System.out.print("Digite o nome da tabela: ");
	        String nomeTabela = scanner.nextLine();

	        List<String> colunas = new ArrayList<>();
	        List<String> valores = new ArrayList<>();

	        boolean adicionarValor = true;
	        while (adicionarValor) {
	            System.out.print("Digite o nome da coluna: ");
	            colunas.add(scanner.nextLine());
	            System.out.print("Digite o valor: ");
	            valores.add(scanner.nextLine());

	            System.out.print("Deseja adicionar outro valor? (s/n): ");
	            adicionarValor = scanner.nextLine().equalsIgnoreCase("s");
	        }

	        gerenciadorTabelas.inserirDados(nomeTabela, colunas, valores);
	        System.out.println("Dados inseridos com sucesso.");
	    }

	    private static void atualizarDados(Scanner scanner, GerenciadorTabelas gerenciadorTabelas) throws SQLException {
	        System.out.print("Digite o nome da tabela: ");
	        String nomeTabela = scanner.nextLine();
	        System.out.print("Digite a cláusula SET (ex: nome='Novo Nome'): ");
	        String setClause = scanner.nextLine();
	        System.out.print("Digite a cláusula WHERE (ex: id=1): ");
	        String whereClause = scanner.nextLine();

	        gerenciadorTabelas.atualizarDados(nomeTabela, setClause, whereClause);
	        System.out.println("Dados atualizados com sucesso.");
	    }

	    private static void deletarDados(Scanner scanner, GerenciadorTabelas gerenciadorTabelas) throws SQLException {
	        System.out.print("Digite o nome da tabela: ");
	        String nomeTabela = scanner.nextLine();
	        System.out.print("Digite a cláusula WHERE (ex: id=1): ");
	        String whereClause = scanner.nextLine();

	        gerenciadorTabelas.deletarDados(nomeTabela, whereClause);
	        System.out.println("Dados deletados com sucesso.");
	    }
	}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String URL = "jdbc:postgresql://localhost:5432/financas";
        String USER = "admin";
        String PASSWORD = "admin";

        System.out.println("Bem-vindo ao Sistema de Finanças Pessoais");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            boolean loggedIn = false;
            while (!loggedIn) {
                System.out.println("\nMenu:");
                System.out.println("1. Login");
                System.out.println("2. Criar conta");
                System.out.println("3. Sair");
                System.out.println("Escolha uma opção: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        loggedIn = login(conn);
                        if (loggedIn) {
                            menuUsuario(conn); // Chamar menu de usuário
                        }
                        break;
                    case 2:
                        System.out.println("Digite seu CPF: ");
                        String cpf = sc.nextLine();
                        criarConta(conn, cpf);
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } 
        } catch (SQLException e) {
                System.out.println("Erro na conexão: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

        // Método de login
        private static boolean login(Connection conn) {
            System.out.print("Digite seu CPF: ");
            String cpf = sc.nextLine();
    
            String sql = "SELECT * FROM usuario WHERE cpf = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cpf);
                ResultSet rs = pstmt.executeQuery();
    
                if (rs.next()) {
                    System.out.println("Login bem-sucedido!");
                    String nome = rs.getString("nome");
                    float saldo = rs.getFloat("saldo");
                    System.out.println("Bem vindo, " + nome + "!\nSaldo atual: R$ " + saldo);
                    return true; // Usuário logado com sucesso
                } else {
                    System.out.println("CPF não encontrado. Deseja criar uma conta? (s/n)");
                    String response = sc.nextLine();
                    if (response.equalsIgnoreCase("s")) {
                        criarConta(conn, cpf);
                        return false;
                    }
                    return false; // Login não bem-sucedido
                }
            } catch (SQLException e) {
                System.out.println("Erro ao verificar o login: " + e.getMessage());
                return false;
            }
        }

        // Método criar conta
        private static void criarConta(Connection conn, String cpf) {
            // Verificar se CPF já está cadastrado
            String checkSql = "SELECT * FROM usuario WHERE cpf = ?";
            try (PreparedStatement checkPstmt = conn.prepareStatement(checkSql)) {
                checkPstmt.setString(1, cpf);
                ResultSet rs = checkPstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("CPF já cadastrado. Retornando ao menu...");
                    return;
                }
            } catch (SQLException e) {
                System.out.println("Erro ao verificar CPF: " + e.getMessage());
            }

            // Criar conta caso CPF não esteja cadastrado
            System.out.print("Digite seu nome: ");
            String nome = sc.nextLine();
    
            String sql = "INSERT INTO usuario (nome, cpf) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nome);
                pstmt.setString(2, cpf);
                pstmt.executeUpdate();
                System.out.println("Conta criada com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao criar conta: " + e.getMessage());
            }
        }

        // Menu de usuário
        private static void menuUsuario(Connection conn) {
            boolean sair = false;
            while (!sair) {
                System.out.println("\nMenu de Usuário:");
                System.out.println("1. Inserir Movimentação");
                System.out.println("2. Exibir Saldo por Categoria");
                System.out.println("3. Gerar Relatório");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Em breve.");
                        break;
                    case 2:
                        System.out.println("Em breve.");
                        break;
                    case 3:
                        System.out.println("Em breve.");
                        break;
                    case 4:
                        System.out.println("Volte sempre!");
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
}
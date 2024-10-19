import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/financas";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void criaTabelas() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS movimentacao (" +
                         "id SERIAL PRIMARY KEY," +
                         "tipo VARCHAR(20)," +
                         "valor REAL," +
                         "data DATE)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    // Métodos para inserção
}
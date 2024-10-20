import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Relatorio {
    private static String cpfUsuario;

    public static void setCpfUsuario(String cpf) {
        cpfUsuario = cpf; // Configura o CPF do usuário logado
    }

    public static void gerarRelatorio(Connection conn, String dataInicio, String dataFim) {
        String sql = "SELECT m.tipo, m.valor, m.data FROM movimentacao m " +
                     "JOIN usuario u ON m.id = u.id " + // Supondo que você tenha um id que conecta com usuario
                     "WHERE u.cpf = ? AND m.data BETWEEN ? AND ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpfUsuario); // Usa o CPF do usuário logado
            pstmt.setDate(2, java.sql.Date.valueOf(dataInicio)); // Conversão de String para java.sql.Date
            pstmt.setDate(3, java.sql.Date.valueOf(dataFim));

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Movimentações de " + cpfUsuario + " entre " + dataInicio + " e " + dataFim + ":");
                do {
                    String tipo = rs.getString("tipo");
                    float valor = rs.getFloat("valor");
                    Date data = rs.getDate("data");
                    System.out.println("Tipo: " + tipo + ", Valor: R$ " + valor + ", Data: " + data);
                } while (rs.next());
            } else {
                System.out.println("Nenhuma movimentação encontrada para o CPF " + cpfUsuario + " nesse intervalo.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }
}

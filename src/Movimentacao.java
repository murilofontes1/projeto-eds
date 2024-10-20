import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


public class Movimentacao {
    private String tipo; // "receita" ou "despesa"
    private float valor;
    private Date data;

    public Movimentacao(String tipo, float valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = new Date();
    }

    public String getTipo() {
        return tipo;
    }

    public float getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    // Método para adicionar uma movimentação no banco de dados
    public void adicionarMovimentacao(Connection conn, String cpf) throws SQLException {
        String sql = "INSERT INTO movimentacao (tipo, valor, data, cpf) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.tipo);
            pstmt.setFloat(2, this.valor);
        
            // Converter java.util.Date para java.sql.Date antes de inserir
            pstmt.setDate(3, new java.sql.Date(this.data.getTime())); // Usando java.sql.Date para a inserção
            pstmt.setString(4, cpf);
            pstmt.executeUpdate();
            System.out.println("Movimentação adicionada com sucesso!");
        }
    }
}
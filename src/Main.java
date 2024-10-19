import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Simulando login
        Usuario usuario = new Usuario("João", "12345678900");
        System.out.println("Digite seu CPF para fazer login:");
        String cpfInput = sc.nextLine();
        if (usuario.login(cpfInput)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("CPF incorreto.");
            return;
        }

        // Criação da tabela de movimentações
        Database.criaTabelas();

        // Inserção de movimentações
        List<Movimentacao> movimentacoes = new ArrayList<>();
        System.out.println("Digite o tipo da movimentação (receita/despesa):");
        String tipo = sc.nextLine();
        System.out.println("Digite o valor:");
        float valor = sc.nextFloat();
        sc.nextLine(); // Consumir a quebra de linha
        System.out.println("Digite a data (YYYY-MM-DD):");
        String data = sc.nextLine();

        Movimentacao mov = new Movimentacao(tipo, valor, data);
        movimentacoes.add(mov);

        // Gerar relatório
        Relatorio.gerarRelatorio(movimentacoes);

        sc.close();
    }
}
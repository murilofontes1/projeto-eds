import java.util.List;

public class Relatorio {
    public static void gerarRelatorio(List<Movimentacao> movimentacoes) {
        System.out.println("Relatório de Movimentações:");
        for (Movimentacao mov : movimentacoes) {
            System.out.println(mov.getData() + " - " + mov.getTipo() + " - " + mov.getValor());
        }
    }
}
public class Movimentacao {
    private String tipo; // "receita" ou "despesa"
    private float valor;
    private String data;

    public Movimentacao(String tipo, float valor, String data) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public float getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }
}

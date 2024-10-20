public class Usuario {
    private String nome;
    private String cpf;
    private boolean estadoLogin;
    private float saldo;

    public Usuario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = 0.0f;
        this.estadoLogin = false;
    }

    public boolean login(String cpfInput) {
        if (this.cpf.equals(cpfInput)) {
            this.estadoLogin = true;
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        return this.estadoLogin;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
/**
 * Classe para representar Contas de Poupança.
 * 
 * Uma conta poupança possui uma taxa de rendimento e um método para aplicação
 * do rendimento.
 * 
 * @author Julio César Alves
 */
public class ContaPoupanca extends Conta {
    // taxa de rendimento da poupança
    private double taxaRendimento;

    /**
     * Cria uma conta de poupança com uma taxa de rendimento.
     * 
     * @param taxaRendimento Taxa de rendimento da poupança.
     */
    public ContaPoupanca(double taxaRendimento) {
        this(0.0, taxaRendimento);
    }

    /**
     * Cria uma conta de poupança com um saldo inicial e uma taxa de rendimento.
     * 
     * @param saldoInicial Saldo inicial da conta
     * @param taxaRendimento Taxa de rendimento da poupança.
     */
    public ContaPoupanca(double saldoInicial, double taxaRendimento) {
        super(saldoInicial);
        if (taxaRendimento <= 0) {
            throw new IllegalArgumentException("A taxa de rendimento deve ser positiva.");
        }
        this.taxaRendimento = taxaRendimento;
    }

    /**
     * Aplica a taxa de rendimento ao saldo atual da poupança
     */
    public void render() {
        // deposita na própria conta o valor do rendimento
        depositar(getSaldo()*taxaRendimento);
    }

    /**
     * Retorna a taxa de rendimento da poupança.
     * 
     * @return A taxa de rendimento.
     */
    public double getTaxaRendimento() {
        return taxaRendimento;
    }
}
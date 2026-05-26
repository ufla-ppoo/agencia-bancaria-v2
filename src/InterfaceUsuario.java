
import java.util.Scanner;

/**
 * Interface de usuário para o programa
 * 
 * @author Julio César Alves
 */
public class InterfaceUsuario {
    private Agencia agencia;
    private Scanner entrada;
    private final int opcaoSair = 6;

    public InterfaceUsuario(String nomeAgencia) {
        agencia = new Agencia(nomeAgencia);
        entrada = new Scanner(System.in);
    }
    
    public void exibir() {
        int opcao;
        do {
            opcao = exibirMenu();
            tratarOpcaoMenu(opcao);
        } while (opcao != opcaoSair);
    }

    private int exibirMenu() {
        System.out.println("Bem-vindo a agencia " + agencia.getNome() + "!\n");
        System.out.println(
                "1 - Criar conta\n" +
                "2 - Relatorio\n" +
                "3 - Deposito\n" +
                "4 - Saque\n" +
                "5 - Transferencia\n" +
                "6 - Sair\n" );
        return pedirInteiro("Digite sua opcao: ");
    }

    private void tratarOpcaoMenu(int opcao) {
        System.out.println();
        switch (opcao) {
            case 1:
                criarConta();
                break;
            case 2:
                exibirRelatorio();
                break;
            case 3:
                fazerDeposito();
                break;
            case 4:
                fazerSaque();
                break;
            case 5:
                fazerTransferencia();
                break;
            case 6:
                System.out.println("\nObrigado por usar os servicos da " + agencia.getNome() + "!\n");
                break;
            default:
                System.out.println("\nOpcao invalida!\n");
                break;
        }

        if (opcao != opcaoSair) {
            aguardarEnter();
        }
    }

    private void criarConta() {
        try {
            int nroConta = agencia.criarConta();
            System.out.println("Conta " + nroConta + " criada!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void exibirRelatorio() {
        System.out.println(agencia.gerarRelatorio());
    }

    private int pedirInteiro(String instrucao) {
        int valor = 0;
        boolean valorValido = false;
        do {
            try {
                System.out.println(instrucao);
                valor = Integer.parseInt(entrada.nextLine());
                valorValido = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Digite um valor inteiro valido!");
                aguardarEnter();
            }
        }
        while (!valorValido);
        return valor;
    }

    private int pedirConta() {
        return pedirInteiro("Digite o numero da conta: ");
        
    }

    private void aguardarEnter() {
        System.out.print("\n... pressione ENTER para continuar...");
        entrada.nextLine();
        System.out.println("\n");
    }

    private double pedirValor() {
        return pedirInteiro("Digite o valor: ");
    }

    private void fazerDeposito() {		
        try {
            agencia.depositar(pedirConta(), pedirValor());
            System.out.println("Deposito realizado com sucesso!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void fazerSaque() {	
        try {
            agencia.sacar(pedirConta(), pedirValor());
            System.out.println("Saque realizado com sucesso!");
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
            System.out.println("A conta tinha apenas " + e.getSaldo() + " de saldo!");
        } catch (Exception e) {
            System.out.println("Não foi possivel sacar!");
            System.out.println(e.getMessage());
        }
    }

    private void fazerTransferencia() {	
        try {
            agencia.transferir(pedirConta(), pedirConta(), pedirValor());
            System.out.println("Transferencia realizada com sucesso!");
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
            System.out.println("A conta tinha apenas " + e.getSaldo() + " de saldo!");
        } catch (Exception e) {
            System.out.println("Não foi possivel transferir!");
            System.out.println(e.getMessage());
        }
    }
}

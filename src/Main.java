import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    private static final String LINHA = "------------------------------";

    public static void exibirMenu() {
        System.out.println("Bem vindo ao teste do seu Banco Inicial!");
        System.out.println(LINHA);
        System.out.println("1 - Consultar Saldo");
        System.out.println("2 - Consultar Limite Cheque Especial");
        System.out.println("3 - Status do Cheque Especial");
        System.out.println("4 - Depositar Dinheiro");
        System.out.println("5 - Sacar Dinheiro");
        System.out.println("6 - Pagar Boleto");
        System.out.println("7 - Sair");
        System.out.println(LINHA);
    }

    private static int lerInteiro(Scanner scan, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scan.nextInt();
                scan.nextLine(); 
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scan.nextLine(); 
            }
        }
    }

    private static double lerDouble(Scanner scan, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = scan.nextDouble();
                scan.nextLine(); 
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número decimal.");
                scan.nextLine(); 
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ContaBancaria conta1 = new ContaBancaria(1000.0);

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro(scan, "Escolha uma opção: ");
            System.out.println(LINHA);

            switch (opcao) {
                case 1 -> conta1.consultarSaldo();
                case 2 -> conta1.consultarChequeEspecial();
                case 3 -> conta1.verificarChequeEspecial();
                case 4 -> conta1.depositar(lerDouble(scan, "Valor do Depósito: "));
                case 5 -> conta1.sacar(lerDouble(scan, "Valor do Saque: "));
                case 6 -> conta1.pagarBoleto(lerDouble(scan, "Valor do boleto: "));
                case 7 -> System.out.println("Sessão Finalizada!");
                default -> System.out.println("Opção inválida! Digite os números disponíveis no menu.");
            }

            if (opcao != 7) {
                System.out.println(LINHA);
                System.out.println("\nAperte ENTER para continuar...");
                scan.nextLine();
                System.out.println(LINHA);
            }

        } while (opcao != 7);

        System.out.println(LINHA);
        System.out.println("Fim da execução, obrigado por utilizar o programa!");

        scan.close();
    }
}

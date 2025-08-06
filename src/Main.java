import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    private static String LINHA = "------------------------------";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ContaBancaria conta1 = new ContaBancaria(1000.0);

        int opcao = 0;
        do {
            try {
                System.out.println("Bem vindo ao teste do seu Banco Inicial!");
                System.out.println(LINHA);
                System.out.println("Escolha uma opção: ");
                System.out.printf("1 - Consultar Saldo \n2 - Consultar Limite Cheque Especial \n3 - Status do Cheque Especial \n4 - Depositar Dinheiro \n5 - Sacar Dinheiro \n6 - Pagar Boleto\n7 - Sair \n");
                System.out.println(LINHA);
                System.out.print("Sua opção: ");
                opcao = scan.nextInt();
                System.out.println(LINHA);

                scan.nextLine();

                switch (opcao) {

                    case 1:
                        conta1.consultarSaldo();
                        break;
                            
                    case 2:
                        conta1.consultarChequeEspecial();
                        break;
                            
                    case 3:
                        conta1.verificarChequeEspecial();
                        break;
                            
                    case 4:
                        boolean valorValido = false;
                        do {
                            try {
                                System.out.print("Digite o valor a ser depositado: ");
                                double valorDeposito = scan.nextDouble();
                                if (valorDeposito > 0) {
                                    conta1.depositar(valorDeposito);
                                    scan.nextLine();
                                    valorValido = true;
                                } else {
                                    System.out.println("Valor de depósito inválido! Tente novamente.");
                                    scan.nextLine();
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada inválida! Por favor, insira um valor válido.");
                                scan.nextLine();
                            }
                        } while (!valorValido);
                        break;
                            
                    case 5:
                        boolean saqueValido = false;
                        do {
                            try {
                                System.out.print("Digite o valor a ser sacado: ");
                                double valorSaque = scan.nextDouble();
                                if (valorSaque > 0) {
                                    conta1.sacarDinheiro(valorSaque);
                                    saqueValido = true;
                                    scan.nextLine();
                                } else {
                                    System.out.println("Valor de saque inválido ou insuficiente! Tente novamente.");
                                    scan.nextLine();
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada inválida! Por favor, insira um valor válido.");
                                scan.nextLine();
                            }
                        } while (!saqueValido);
                        break;
                            
                    case 6:
                        boolean boletoValido = false;
                        do {
                            try {
                                System.out.print("Digite o valor do boleto: ");
                                double valorBoleto = scan.nextDouble();
                                if (valorBoleto > 0) {
                                    conta1.pagarBoleto(valorBoleto);
                                    scan.nextLine();
                                    boletoValido = true;
                                } else {
                                    System.out.println("Valor de pagamento inválido ou insuficiente! Tente novamente.");
                                    scan.nextLine();
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada inválida! Por favor, insira um valor válido.");
                                scan.nextLine();
                            }
                        } while (!boletoValido);
                        break;
                            
                    case 7:
                        break;
                            
                    default:
                        System.out.println("Opção inválida! Digite os números disponíveis no menu.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um NÚMERO inteiro.");
                scan.nextLine();
            }

            if (opcao != 7) {
                System.out.println(LINHA);
                System.out.println("\nAperte ENTER para continuar...");
                scan.nextLine();
            }

        } while (opcao != 7);

        System.out.println("Fim da execução, obrigado por utilizar o programa!");
        
        scan.close();
    }
}
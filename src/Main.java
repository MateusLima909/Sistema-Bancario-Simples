import java.util.Scanner;

import model.Cliente;
import model.Conta;

import service.ContaService;

public class Main {
  protected static String LINHA = "------------------------------";

  public static void exibirMenuInicial() {
    System.out.println(LINHA);
    System.out.println("Bem vindo ao teste do seu Banco versão 2.0!");
    System.out.println(LINHA);
    System.out.println("1 - Acessar Conta");
    System.out.println("2 - Criar Conta Corrente"); 
    System.out.println("3 - Criar Conta Poupança");
    System.out.println("4 - Sair");
    System.out.println(LINHA);
  }

  private static int lerInteiro(Scanner scan, String mensagem) {
    while(true) {
      try {
        System.out.printf(mensagem);
        int valor = scan.nextInt(); 
        scan.nextLine();
        System.out.println(LINHA);
        return valor;
      } catch (Exception e) {
        System.out.println(LINHA);
        System.out.println("Entrada inválida. Digite um número inteiro.");
        scan.nextLine();
      }
    }
  }

  private static double lerDouble(Scanner scan, String mensagem) {
    while(true) {
      try {
        System.out.printf(mensagem);
        double valor = scan.nextDouble(); 
        scan.nextLine();
        return valor;
      } catch (Exception e) {
        System.out.println(LINHA);
        System.out.println("Entrada inválida. Digite um número decimal.");
        scan.nextLine();
      }
    }
  }

  private static Cliente lerCLiente(Scanner scan) {
    
    System.out.printf("Digite seu nome: ");
    String nome = scan.nextLine();
    System.out.printf("Digite seu CPF: ");
    String cpf = scan.nextLine();

    Cliente novoCliente = new Cliente(nome, cpf);
    return novoCliente;
  }

  private static void criarNovaConta(Scanner scan, ContaService bancoService, String tipoConta) {
    Cliente novoCliente = lerCLiente(scan);
    double saldoInicial = lerDouble(scan, "Digite o valor do depósito inicial: R$");

    Conta contaCriada = null;
    
    if (tipoConta.equals("CORRENTE")) {
      contaCriada = bancoService.criarContaCorrente(novoCliente, saldoInicial);
    } else if (tipoConta.equals("POUPANCA")) {
        contaCriada = bancoService.criarContaPoupanca(novoCliente, saldoInicial);
    }

    if (contaCriada != null) {
      System.out.printf("\nConta %s criada com sucesso! \n", tipoConta.toLowerCase());
      System.out.printf("Número da sua nova conta: %d \nPressione ENTER para continuar.\n", contaCriada.getNumero());
      scan.nextLine();
    } else {
      System.out.println("Erro ao criar conta.");
    }
  }
  
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    ContaService bancoService = new ContaService();
    Conta contaAtiva = null;

    int opcao = 0;
    do {
      if (contaAtiva == null) {
        exibirMenuInicial();
        opcao = lerInteiro(scan, "Escolha uma opção: ");
      
        switch (opcao) {
          case 1: 
            System.out.println("     --- LOGIN ---");
            int numero = lerInteiro(scan, "Digite o número da Conta: ");
            contaAtiva = bancoService.buscarPorNumero(numero);

            if (contaAtiva == null) {
              System.out.println("Conta não encontrada. Verifique o número e tente novamente. \nPressione ENTER para continuar.");
            } else {
              System.out.print("Digite seu CPF (apenas números): ");
              String cpf = scan.nextLine();

              contaAtiva = bancoService.realizarLogin(numero, cpf);
              if (contaAtiva != null) {
                System.out.println("Acesso realizado com sucesso! Pressione ENTER para continuar.");
              } else {
                System.out.println("Conta não encontrada. Verifique o número do seu CPF e tente novamente. \nPressione ENTER para continuar.");
              }
            } 
            scan.nextLine();
          break;
            
          case 2: 
            System.out.println("--- CRIAR CONTA CORRENTE ---");
            criarNovaConta(scan, bancoService, "CORRENTE");
            
          break;

          case 3:
            System.out.println("--- CRIAR CONTA POUPANÇA ---");
            criarNovaConta(scan, bancoService, "POUPANCA");
          break;
            
          case 4:
            System.out.println("Obrigado por utilizar o Banco em Testes Iniciais!");
          break;
          default:
            System.out.println("Opção Inválida! Tente Novamente.");
        }
      } else {
        contaAtiva.iniciarOperacoesMenu(scan, bancoService);
        contaAtiva = null;
      }

    } while (opcao != 4);

    System.out.println(LINHA);
    System.out.println("Fim da execução!");
    
    scan.close();
  }
}
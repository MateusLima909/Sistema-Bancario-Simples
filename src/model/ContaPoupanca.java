package model;

import java.util.Scanner;

import service.ContaService;

public class ContaPoupanca extends Conta {
  private static final Double TAXA_RENDIMENTO = 0.02;// 2%

  // Construtor
  public ContaPoupanca(Cliente cliente, int numero, double saldoInicial) {
    super(cliente, numero);
    this.saldo = saldoInicial;
  }

  // Principais operações
  @Override
  public void depositar(double valor, String tipoDeposito) {
    if (!valorValido(valor, "Depósito")) {
      return;
    }
    
    this.saldo += valor;
    
    if (tipoDeposito.equals("Depósito")) {
      double rendimento = valor * TAXA_RENDIMENTO;
      this.saldo += rendimento;
      
      String descricao = String.format("Depósito em Poupança + R$ %.2f de rendimento", rendimento);
      adicionarTransacao("DEPÓSITO", valor, descricao);
    }
  }

  @Override
  public boolean sacar(double valor, String tipoTransacao) {
    if (!valorValido(valor, tipoTransacao))
      return false;
    return realizarTransacao(valor, tipoTransacao);
  }

  public boolean pagarBoleto(double valor) {
    if (!valorValido(valor, "Pagamento de Boleto"))
      return false;
    return realizarTransacao(valor, "Pagamento de boleto");
  }

  @Override
  public void iniciarOperacoesMenu(Scanner scan, ContaService bancoService) {
    int opcao;
    do {
      System.out.println("Bem vindo ao teste do seu Banco versão 2.0!");
      System.out.println("\n--- CONTA POUPANÇA: " + this.getCliente().getNome() + " | Saldo: R$ "
          + String.format("%.2f", this.getSaldo()) + " ---");
      System.out.println("------------------------------");
      System.out.println("1 - Depositar");
      System.out.println("2 - Sacar");
      System.out.println("3 - Pagar Boleto");
      System.out.println("4 - Transferir");
      System.out.println("5 - Pagamento PIX");
      System.out.println("6 - Ver Extrato (Histórico)");
      System.out.println("7 - Sair da conta (Logout)");
      System.out.println("------------------------------");
      opcao = lerInteiro(scan, "Escolha uma operação: ");
      System.out.println("------------------------------");

      switch (opcao) {
        case 1:
          System.out.println("--- REALIZAR DEPÓSITO ---");
          double valorDeposito = (lerValorMonetario(scan, "Valor do Depósito: R$"));
          this.depositar(valorDeposito, "Depósito");
          break;

        case 2:
          System.out.println("--- REALIZAR SAQUE ---");

          double valorSaque = lerValorMonetario(scan, "Valor do Saque: R$");
          this.sacar(valorSaque, "Saque");
          break;

        case 3:
          System.out.println("--- REALIZAR PAGAMENTO DE BOLETO ---");

          double valorBoleto = lerValorMonetario(scan, "Valor do Boleto: R$");
          this.pagarBoleto(valorBoleto);
          break;
        case 4:
          System.out.println("--- TRANSFERÊNCIA ---");

          int numeroDestino = lerInteiro(scan, "Número da Conta Destino: ");

          double valorTransferencia = lerValorMonetario(scan, "Valor para Transferência: R$");
          bancoService.realizarTransferencia(this.getNumero(), numeroDestino, valorTransferencia);
          break;

        case 5:
          System.out.println("--- PAGAMENTO PIX ---");

          int numeroDestinoPix = lerInteiro(scan, "Número da Conta Destino: ");

          double valorPix = lerValorMonetario(scan, "Valor do PIX: R$");
          bancoService.realizarPix(this.getNumero(), numeroDestinoPix, valorPix);
          break;

        case 6:
          this.imprimirHistorico();
          break;

        case 7:
          System.out.println("Sessão Finalizada!");
          break;
        default:
          System.out.println("Opção inválida!");
      }

      if (opcao != 7) {
        System.out.println("\nAperte ENTER para continuar...");
        scan.nextLine();
      }

    } while (opcao != 7);
  }

  // Métodos Privados Auxiliares
  private boolean realizarTransacao(double valor, String tipoTransacao) {
    if (valor <= saldo) {
      saldo -= valor;
      if (tipoTransacao.equals("Saque") || tipoTransacao.equals("Pagamento de boleto")) {
        adicionarTransacao(formatar(tipoTransacao).toUpperCase(), valor, "Normal");
      }
      return true;
    } else {

      return false;
    }
  }
}
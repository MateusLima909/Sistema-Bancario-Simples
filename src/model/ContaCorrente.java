package model;

import java.util.Scanner;

import service.ContaService;

public class ContaCorrente extends Conta{
  private static final double TAXA_CHEQUE_ESPECIAL = 0.20; //20%
  private static final double LIMITE_FIXO = 50.0;

  private double limiteChequeEspecial;
  private double valorUsadoChequeEspecial = 0.0;

  //Construtor
  public ContaCorrente (Cliente cliente, int numero, double saldoInicial) {
    super(cliente, numero);

    this.saldo = saldoInicial;
    
    this.limiteChequeEspecial = (saldoInicial > 0 && saldoInicial <= 500) ? LIMITE_FIXO : saldoInicial / 2;
  }

  //Getters
  public double getLimiteChequeEspecial() { return limiteChequeEspecial; }
  public double getValorUsadoChequeEspecial() { return valorUsadoChequeEspecial; }

  //Principais operações
  @Override
  public boolean sacar(double valor, String tipoTransacao) {
    if (!valorValido(valor, tipoTransacao)) return false;
    return realizarTransacao(valor, tipoTransacao);
  }

  @Override
  public void depositar(double valor, String tipoDeposito) {
      if (!valorValido(valor, "Depósito")) {
          return;
      }

      double valorDeposito = valor;

      if (valorUsadoChequeEspecial > 0) {
          double abaterValor = Math.min(valorUsadoChequeEspecial, valor);
          valorUsadoChequeEspecial -= abaterValor;
          saldo += abaterValor;
          valor -= abaterValor;

          double taxa = abaterValor * TAXA_CHEQUE_ESPECIAL;
          saldo -= taxa;
          valorUsadoChequeEspecial += taxa;

      }
      saldo += valor;
      if (tipoDeposito.equals("Depósito")) { 
        adicionarTransacao("DEPÓSITO", valorDeposito, "Depósito em Conta Corrente");
      }
  }

  public boolean pagarBoleto(double valor) {
    if (!valorValido(valor, "Pagamento de Boleto")) return false;
    return realizarTransacao(valor, "Pagamento de boleto");
  }

  @Override
    public void iniciarOperacoesMenu(Scanner scan, ContaService bancoService) {
      int opcao;
      do {
        System.out.println("Bem vindo ao teste do seu Banco versão 2.0!");
        System.out.println("\n--- CONTA CORRENTE: " + this.getCliente().getNome() + " | Saldo: R$ " + String.format("%.2f", this.getSaldo()) + " ---");
        System.out.println("------------------------------");
        System.out.println("1 - Depositar"); 
        System.out.println("2 - Sacar");
        System.out.println("3 - Pagar Boleto");
        System.out.println("4 - Transferir");
        System.out.println("5 - Pagamento PIX");
        System.out.println("6 - Ver Extrato (Histórico)");
        System.out.println("7 - Consultar Cheque Especial");
        System.out.println("8 - Verificar Situação do Cheque Especial");
        System.out.println("9 - Sair da conta (Logout)");
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
            String info = getInfoChequeEspecial();
            System.out.println(info);
          break;

          case 8:
            this.verificarChequeEspecial();
          break;

          case 9:
            System.out.println("Sessão Finalizada!");
          break;
          default: 
            System.out.println("Opção inválida!");
        }

        if (opcao != 9) {
          System.out.println("\nAperte ENTER para continuar...");
          scan.nextLine();   
        }

      } while (opcao != 9);
    }
    
  //Consultas
  public boolean verificarChequeEspecial() {
    boolean ativo = valorUsadoChequeEspecial < limiteChequeEspecial;
    System.out.println("Cheque especial: " + ((ativo) ? "Ativo" : "Inativo"));
    return ativo;
  }

    
  public String getInfoChequeEspecial() {
      return String.format("Limite do Cheque Especial: R$%.2f | Utilizado: R$%.2f\n", this.limiteChequeEspecial, this.valorUsadoChequeEspecial); 
  }

  //Métodos privados auxiliares
  private boolean realizarTransacao(double valor, String tipoTransacao) {
      if (valor <= saldo) {
        saldo -= valor;

        if (tipoTransacao.equals("Saque")) {
          adicionarTransacao(tipoTransacao.toUpperCase(), valor, formatar(tipoTransacao) + " Normal");
          System.out.printf("%s realizado com sucesso!\n", tipoTransacao);
        }
        
        return true;
      } else if (valor <= saldo + limiteChequeEspecial) {
          double usoCheque = (saldo > 0) ? valor - saldo : valor;

          saldo -= valor; 
          valorUsadoChequeEspecial += usoCheque;

          System.out.printf("%s realizado com sucesso com o uso de cheque especial!\n", tipoTransacao);
          adicionarTransacao(tipoTransacao.toUpperCase(), valor, formatar(tipoTransacao) + " com uso de cheque especial");
          return true;
      } else {
          System.out.println("Saldo e limite insuficientes.");
          return false;
      }
  }
}
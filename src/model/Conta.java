package model;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import service.ContaService;

public abstract class Conta {
  protected List<Transacao> historico;
  protected double saldo;
  private Cliente cliente;
  private int numero;

  //Construct
  public Conta(Cliente cliente, int numero){
    this.historico = new ArrayList<Transacao>();
    this.cliente = cliente;
    this.numero = numero;
    this.saldo = 0.0;
  }

  //Getters
  public Cliente getCliente() { return cliente; }
  public double getSaldo() { return saldo; }
  public int getNumero() { return numero; }

  //Operações Principais
  public abstract void iniciarOperacoesMenu(Scanner scan, ContaService bancoService);
  public abstract void depositar(double valor, String tipoDeposito);
  public abstract boolean sacar(double valor, String tipoTransacao);
  
  public void realizarTransferencia(double valor, Conta contaDestino) {
    boolean sucesso = this.sacar(valor, "Transferência");

    if(sucesso) {
      contaDestino.depositar(valor, "Transferência");

      this.adicionarTransacao("TRANSFERÊNCIA ENVIADA", valor, "Para conta: " + contaDestino.getNumero());
      contaDestino.adicionarTransacao("TRANSFERÊNCIA RECEBIDA", valor, "Da conta: " + this.getNumero());
      System.out.println("Transferência realizada com sucesso!");
    }
  }

  public void realizarPix(double valor, Conta contaDestino) {
    boolean sucesso = this.sacar(valor, "Pix");

    if(sucesso) {
      contaDestino.depositar(valor, "Transferência");

      this.adicionarTransacao("PIX ENVIADO", valor, "Para conta: " + contaDestino.getNumero());
      contaDestino.adicionarTransacao("PIX RECEBIDO", valor, "Da conta: " + this.getNumero());
      System.out.println("Pix realizado com sucesso!");
    }
  }

  //Consultas
  public void imprimirHistorico() {
    System.out.printf("\n--- Histórico de Transações da Conta %d ---\n", this.numero);

    if(this.historico.isEmpty()) {
      System.out.println("Nenhuma transação encontrada!");
      return;
    } 

    for (Transacao transacao : this.historico) {
      System.out.println(transacao);
    }

    System.out.println("------------------------------");
  }

  //Métodos protegidos auxiliares
  protected double lerValorMonetario(Scanner scan, String mensagem) {
    while(true) {
        try {
          System.out.printf(mensagem);
          double valor = scan.nextDouble(); 
          scan.nextLine();
          if (valor > 0) { 
            return valor;
          } else {
            System.out.println("Entrada inválida. Por favor, digite um número (ex: 150.75).");
          }
        } catch (Exception e) {
          System.out.println("Entrada inválida. Digite um número decimal.");
          scan.nextLine();
        }
    }   
  }

  protected static int lerInteiro (Scanner scan, String mensagem) {
    while(true) {
      try {
        System.out.printf(mensagem);
        int valor = scan.nextInt(); 
        scan.nextLine();
        return valor;
      } catch (Exception e) {
        System.out.println("Entrada inválida. Digite um número inteiro.");
        scan.nextLine();
      }
    }
  }
  
  protected void adicionarTransacao (String tipoDeTransacao, double valor, String descricao) {
    Transacao novaTransacao = new Transacao(tipoDeTransacao, valor, descricao);
    this.historico.add(novaTransacao);
  }

  protected boolean transferir(double valor, Conta contaDestino, String mensagem) {
    if (sacar(valor, mensagem)) {
      contaDestino.depositar(valor, mensagem);
      return true;
    }

    System.out.printf("\nNão foi possível realizar %s\n", mensagem);
    return false;
  }

  protected boolean valorValido(double valor, String tipoOperacao){
    if (valor <= 0) {
      System.out.printf("Valor inválido para %s.%n", tipoOperacao);
      return false;
    }
    return true;
  } 

  protected String formatar(String texto) {
    return texto.substring(0, 1).toUpperCase() + texto.substring(1);
  }
  
  @Override
  public String toString() {
      return "Conta [Número=" + numero + ", Cliente=" + cliente.getNome() + "]";
  }
}
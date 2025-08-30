package service;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Cliente;

import repository.ContaRepository;

public class ContaService {
  private ContaRepository contaRepository;

  //Construtor
  public ContaService () {
    this.contaRepository = new ContaRepository();
  }
  
  //Principais Operações
  public Conta realizarLogin(int numero, String cpf) {
    Conta contaEncontrada = this.buscarPorNumero(numero);

    if(contaEncontrada != null && contaEncontrada.getCliente().getCpf().equals(cpf)) {
      return contaEncontrada;
    }

    return null;
  }
  
  public Conta criarContaCorrente(Cliente cliente, double saldoInicial) {
    int novoNumero = contaRepository.gerarNumeroConta();

    ContaCorrente novaConta = new ContaCorrente(cliente, novoNumero, saldoInicial);
    contaRepository.salvar(novaConta);
    return novaConta;
  }
  
  public Conta criarContaPoupanca(Cliente cliente, double saldoInicial) {
    int novoNumero = contaRepository.gerarNumeroConta();

    ContaPoupanca novaConta = new ContaPoupanca(cliente, novoNumero, saldoInicial);
    contaRepository.salvar(novaConta);
    return novaConta;
  }

  public Conta buscarPorNumero(int numero){
    return contaRepository.buscarPorNumero(numero);
  }

  //Realizar depósito de uma conta para outra
  public void realizarDeposito(int numeroConta, double valor) { 
    Conta contaEncontrada = contaRepository.buscarPorNumero(numeroConta);
    
    if (!validarConta(contaEncontrada)) return;
    contaEncontrada.depositar(valor, "Depósito");
  }
  
  public void realizarSaque(int numeroConta, double valor) {
    Conta contaEncontrada = contaRepository.buscarPorNumero(numeroConta);
    
    if (!validarConta(contaEncontrada)) return;
    contaEncontrada.sacar(valor, "Saque");
  }

  public void realizarTransferencia(int numeroOrigem, int numeroDestino, double valor) {
    Conta contaDestinoEncontrada = contaRepository.buscarPorNumero(numeroDestino);
    Conta contaOrigemEncontrada = contaRepository.buscarPorNumero(numeroOrigem);
    
    if (!validarConta(contaDestinoEncontrada) || !validarConta(contaOrigemEncontrada)) return;

    contaOrigemEncontrada.realizarTransferencia(valor, contaDestinoEncontrada);
  }

  public void realizarPix(int numeroOrigem, int numeroDestino, double valor) {
    Conta contaDestinoEncontrada = contaRepository.buscarPorNumero(numeroDestino);
    Conta contaOrigemEncontrada = contaRepository.buscarPorNumero(numeroOrigem);

    if (!validarConta(contaDestinoEncontrada) || !validarConta(contaOrigemEncontrada)) return;
    
    contaOrigemEncontrada.realizarPix(valor, contaDestinoEncontrada);
  }
  
  //Método privado auxiliar
  private boolean validarConta(Conta contaEncontrada) {
    if (contaEncontrada == null) {
      System.out.println("Erro: Conta não encontrada.");
      return false;
    }
    return true;
  }
}
package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Conta;

public class ContaRepository {
  private Map<Integer, Conta> bancoDeDados  = new HashMap<Integer, Conta>();
  private int sequenciaConta = 100; //Geração de números de contas "aleatórias".

  //Principais Operações
  public void salvar(Conta conta) {
    bancoDeDados.put(conta.getNumero(), conta);
  }

  public Conta buscarPorNumero (int numero) {
    return bancoDeDados.get(numero);
  } 
  
  public List<Conta> buscarTodos() {
    return new ArrayList<Conta>(bancoDeDados.values());
  }

  public void removerConta(int numero) {
    bancoDeDados.remove(numero);
  }

  public int gerarNumeroConta() {
    return sequenciaConta++;
  }
}
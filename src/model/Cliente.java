package model;

public class Cliente {
   private String nome;
   private String cpf;

  //Construtor
  public Cliente(String nomeCadastrado, String cpfCadastrado) {
    this.nome = nomeCadastrado;
    this.cpf = cpfCadastrado;
  }

  //Getters
  public String getNome() { return nome; }
  public String getCpf() { return cpf; }
}
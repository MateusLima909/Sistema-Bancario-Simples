package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
  private String tipoDeTransacao; //"Depósito", "Saque", "Trasferência enviada", "PIX"
  private String descricao;
  private double valor;
  private LocalDateTime dataHora;

  //Construtor
  public Transacao (String tipo, double valor, String descricao){
    this.tipoDeTransacao = tipo;
    this.descricao = descricao;
    this.valor = valor;
    this.dataHora = LocalDateTime.now();
  }

  //Getters
  public String getTipoDeTransacao() { return tipoDeTransacao; }
  public String getDescricao() { return descricao; }
  public double getValor() { return valor; }
  public LocalDateTime getDataHora() { return dataHora; }

  @Override
  public String toString() {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");
    return String.format("[%s] %-21s | R$ %.2f | %s", dataHora.format(formato), tipoDeTransacao.toUpperCase(), valor, descricao);
  }
}
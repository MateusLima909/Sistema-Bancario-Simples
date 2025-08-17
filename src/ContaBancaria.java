public class ContaBancaria {
    private static final double LIMITE_FIXO = 50.0;
    private static final double TAXA_CHEQUE_ESPECIAL = 0.20;

    private double saldo;
    private double limiteChequeEspecial;
    private double valorUsadoChequeEspecial = 0.0;

    // Construtor
    public ContaBancaria(double saldoInicial) {
        if (saldoInicial < 0) {
            System.out.println("Erro: Valor inicial inválido! Conta criada com saldo zero.");
            saldoInicial = 0.0;
        }
        this.saldo = saldoInicial;
        this.limiteChequeEspecial = (saldoInicial > 0 && saldoInicial <= 500) ? LIMITE_FIXO : saldoInicial / 2;
    }

    // Getters
    public double getSaldo() { return saldo; }
    public double getLimiteChequeEspecial() { return limiteChequeEspecial; }
    public double getValorUsadoChequeEspecial() { return valorUsadoChequeEspecial; }

    // Operações Principais
    public void depositar(double valor) {
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

            System.out.printf("\nAVISO: Taxa aplicada devido ao Cheque Especial, acréscimo de R$%.2f.%n", taxa);
        }

        saldo += valor;
        System.out.printf("\nDepósito de R$%.2f realizado com sucesso!\n", valorDeposito);
    }

    public void sacar(double valor) {
        if (!valorValido(valor, "Saque")) {
            return;
        }
        realizarTransacao(valor, "saque");
    }

    public void pagarBoleto(double valor) {
        if (!valorValido(valor, "Pagamento de Boleto")) {
            return;
        }
        realizarTransacao(valor, "boleto");
    }

    // Consultas
    public void consultarSaldo() {
        System.out.printf("Saldo atual: R$%.2f\n", this.saldo);
    }

    public void consultarChequeEspecial() {
        System.out.printf("Limite do Cheque Especial: R$%.2f | Utilizado: R$%.2f\n", this.limiteChequeEspecial, this.valorUsadoChequeEspecial);
    }

    public boolean verificarChequeEspecial() {
        boolean ativo = valorUsadoChequeEspecial < limiteChequeEspecial;
        System.out.println("Cheque especial: " + (ativo ? "Ativo" : "Inativo"));
        return ativo;
    }

    // Métodos privados auxiliares
   private void realizarTransacao (double valor, String tipoTransacao) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.printf("%s de R$%.2f realizado com sucesso.%n", formatar(tipoTransacao), valor);
        } else if (valor <= saldo + limiteChequeEspecial) {
            double usoCheque = (saldo > 0) ? (valor - saldo) : valor;

            saldo -= valor;
            valorUsadoChequeEspecial += usoCheque;
            System.out.printf("%s de R$%.2f realizado com uso do cheque especial.%n", formatar(tipoTransacao), valor);
        } else {
            System.out.println("Saldo e limite insuficientes.");
        }
    }

    private boolean valorValido(double valor, String tipoOperacao) {
        if (valor <= 0) {
            System.out.printf("Valor inválido para %s.%n", tipoOperacao);
            return false;
        }
        return true;
    }

    private String formatar(String texto) {
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }
}
public class ContaBancaria {

    private double saldo;
    private double limiteChequeEspecial;
    private double valorUsadoChequeEspecial = 0.0;

    public ContaBancaria(double saldoInicial){
        this.saldo = saldoInicial;
        
        if (saldoInicial > 0 && saldoInicial <= 500) {
            this.limiteChequeEspecial = 50.0;
        } else if (saldoInicial > 500) {
            this.limiteChequeEspecial = saldoInicial / 2;
        } else {
            System.out.println("Erro: Valor depositado Inválido!");
            this.limiteChequeEspecial = 0.0;
            this.saldo = 0.0;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }
    
    public double getValorUsadoChequeEspecial() {
        return valorUsadoChequeEspecial;
    }

    public void setValorUsadoChequeEspecial(double valorUsadoChequeEspecial) {
        this.valorUsadoChequeEspecial = valorUsadoChequeEspecial;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito inválido!");
            return;
        }

        double valorDeposito = valor;
        if (this.valorUsadoChequeEspecial > 0) {
            double abaterValor = Math.min(this.valorUsadoChequeEspecial, valor);
            this.valorUsadoChequeEspecial -= abaterValor;
            this.saldo += abaterValor;
            
            valor -= abaterValor;  

            double taxa = abaterValor * 0.20;
            this.saldo -= taxa;
            this.valorUsadoChequeEspecial += taxa;
            
            System.out.printf("\nAVISO: Taxa aplicada devido ao Cheque Especial, acréscimo de R$%.2f\n", taxa);
        }
        
        this.saldo += valor;
        
        System.out.printf("\nDepósito de R$%.2f realizado com sucesso!\n", valorDeposito);
    }

    public void consultarSaldo() {
        System.out.printf("Seu saldo atual: R$%.2f\n", this.saldo);
    }
    
    public void consultarChequeEspecial() {
        System.out.printf("Limite do Cheque Especial: R$%.2f\n", this.limiteChequeEspecial);  
        System.out.printf("Valor Utilizado do Cheque Especial: R$%.2f\n", this.valorUsadoChequeEspecial);  
    }
    
    public boolean verificarChequeEspecial() {
        if (this.valorUsadoChequeEspecial > 0) {
            System.out.println("Situação do seu Cheque Especial: Ativo");
            return true;
        } else {
            System.out.println("Situação do seu Cheque Especial: Inativo");
            return false;
        }
    }

    public void sacarDinheiro(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido!");
            return;
        }

        if (valor <= this.saldo) {
            this.saldo -= valor;
            System.out.printf("Saque de R$%.2f realizado com sucesso!\n", valor);
            
        } else if (valor <= (this.saldo + this.limiteChequeEspecial)) {
            double valorChequeEspecial = valor - this.saldo;
            
            if (this.valorUsadoChequeEspecial + valorChequeEspecial <= this.limiteChequeEspecial) {
                this.saldo -= valor;
                this.valorUsadoChequeEspecial += valorChequeEspecial;
                
                System.out.printf("Saque de R$%.2f realizado utilizando o Cheque Especial! Saldo atual: R$%.2f\n", valor, this.saldo);
            } else {
                System.out.println("Limite do Cheque Especial Excedido!");
            }
        } else {
            System.out.println("Saldo insuficiente para realizar o saque.");
        }
    }

    public void pagarBoleto(double valorBoleto) {
        if (valorBoleto <= 0) {
            System.out.println("Valor para pagamento inválido!");
            return;
        }

        if (valorBoleto <= this.saldo) {
            this.saldo -= valorBoleto;
            System.out.printf("Boleto de R$%.2f pago com sucesso!\n", valorBoleto);
            
        } else if (valorBoleto <= (this.saldo + this.limiteChequeEspecial)) {
            double valorChequeEspecial = valorBoleto - this.saldo;
            
            if (this.valorUsadoChequeEspecial + valorChequeEspecial <= this.limiteChequeEspecial) {
                this.saldo -= valorBoleto;
                this.valorUsadoChequeEspecial += valorChequeEspecial;

                System.out.printf("Pagamento de boleto no valor de R$%.2f realizado utilizando o Cheque Especial! Saldo atual: R$%.2f\n", valorBoleto, this.saldo);

            } else {
                System.out.println("Limite do Cheque Especial Excedido!");
            }
        } else {
            System.out.println("Saldo insuficiente para realizar o pagamento.");
        }
    }
}
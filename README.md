# Sistema Bancário Simples

Este é um projeto simples de sistema bancário desenvolvido em Java, criado para fins de estudo de Programação Orientada a Objetos (POO). O objetivo principal é consolidar conceitos fundamentais da linguagem Java, como classes, objetos, loops, condicionais e tratamento de exceções.

## 🚀 Funcionalidades

O sistema permite que o usuário gerencie uma única conta bancária com as seguintes operações:

-   **Consultar Saldo:** Verifica o saldo atual da conta.
-   **Consultar Cheque Especial:** Exibe o limite disponível e o valor já utilizado do cheque especial.
-   **Verificar Status do Cheque Especial:** Informa se o cheque especial está ativo, inativo ou em uso.
-   **Depositar Dinheiro:** Permite adicionar fundos à conta. A lógica inclui o abatimento de dívidas do cheque especial e a cobrança de uma taxa de 20% sobre o valor abatido.
-   **Sacar Dinheiro:** Permite retirar fundos da conta, utilizando o cheque especial se necessário.
-   **Pagar Boleto:** Permite pagar boletos, com a mesma lógica de saque.

## ⚙️ Regras de Negócio

O sistema segue regras específicas para o cheque especial:

-   **Definição do Limite:** O limite do cheque especial é definido no momento da criação da conta, baseado no saldo inicial:
    -   Se o saldo inicial for de R$ 500,00 ou menos, o limite é de R$ 50,00.
    -   Se o saldo inicial for superior a R$ 500,00, o limite é de 50% do saldo.
-   **Uso da Taxa:** Uma taxa de 20% é cobrada sobre o valor do cheque especial utilizado. Essa taxa é aplicada no próximo depósito que abater parte da dívida.

## 🛠️ Tecnologias Utilizadas

-   **Linguagem:** Java
-   **Conceitos de POO:** Classes, Objetos, Construtores, Métodos e Encapsulamento.
-   **Controle de Fluxo:** `do-while`, `switch` e `if-else`.
-   **Tratamento de Erros:** `try-catch` para lidar com entradas inválidas do usuário.

## Como Rodar o Projeto

Para executar a aplicação, certifique-se de ter o Java Development Kit (JDK) instalado.

1.  Clone este repositório para o seu ambiente local.
2.  Compile os arquivos `.java` no seu terminal, se não estiver usando uma IDE:
    ```bash
    javac Main.java ContaBancaria.java
    ```
3.  Execute o programa a partir do arquivo `Main`:
    ```bash
    java Main
    ```
## 🔎 Observação: 
Esta é a primeira versão do sistema. Pretendo expandi-lo no futuro com funcionalidades como gerenciamento de múltiplas contas, sistema de login e outras operações bancárias.
---

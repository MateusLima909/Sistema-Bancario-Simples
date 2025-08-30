# Desafio de Projeto: Sistema Bancário com POO em Java

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

**Status do Projeto:** 🎯 Concluído, mas com ideias para mais atualizações

---

## 📝 Descrição

Este projeto foi desenvolvido como parte do bootcamp **[NTT DATA - Java e IA Para Iniciantes]** da [Digital Innovation One (DIO)](https://www.dio.me/users/mateuslimasantos909). O objetivo central foi aplicar de forma prática os conceitos fundamentais da **Programação Orientada a Objetos (POO)** e de arquitetura de software, evoluindo de um script Java simples para uma aplicação de console robusta e bem estruturada que simula as operações de um banco digital.

A jornada de desenvolvimento partiu de uma única classe com lógica procedural e culminou em um sistema multi-camadas, demonstrando a importância do design de software na criação de aplicações escaláveis, coesas e de fácil manutenção.

---

## ✨ Funcionalidades

A aplicação permite ao usuário interagir com o sistema bancário através de um terminal de console, oferecendo as seguintes funcionalidades:

* **Gestão de Contas:**
    * Criação de Conta Corrente e Conta Poupança com número gerado automaticamente pelo sistema.
    * Acesso seguro às contas através de um sistema de autenticação com **Número da Conta + CPF** do cliente.

* **Operações Bancárias:**
    * **Depósito:** Adicionar valores ao saldo da conta, com lógica de rendimento automático para a Conta Poupança.
    * **Saque:** Retirar valores do saldo, com lógica de **Cheque Especial** para Contas Correntes.
    * **Transferência:** Enviar valores entre contas do banco.
    * **PIX:** Simulação de transferências instantâneas.
    * **Pagamento de Boleto:** Debitar valores da conta para pagamento de contas.

* **Consultas e Relatórios:**
    * **Extrato Completo (Histórico):** Visualizar um histórico detalhado de todas as transações (depósitos, saques, transferências, etc.), com data, hora, tipo, valor e descrição.
    * **Consulta de Cheque Especial:** Para Contas Correntes, permite visualizar o limite total e o valor já utilizado.

---

## 🏛️ Arquitetura e Conceitos Aplicados

Este projeto foi uma imersão nos pilares da POO e em padrões de arquitetura de software. A estrutura do projeto foi organizada em camadas para garantir a **Separação de Responsabilidades**:

`View (Main) -> Service -> Repository -> Model`

#### 🧠 Programação Orientada a Objetos (POO)
* **Herança:** A classe `Conta` serve como uma base abstrata, com `ContaCorrente` e `ContaPoupanca` herdando seus atributos e comportamentos comuns.
* **Polimorfismo:** O conceito mais poderoso aplicado no projeto. A `Main` interage com um objeto `Conta` genérico (`contaAtiva`), e o sistema executa a implementação correta do método `iniciarOperacoesMenu` de acordo com o tipo real do objeto (Corrente ou Poupança), exibindo menus e opções diferentes para cada um.
* **Encapsulamento:** Os atributos das classes são protegidos (`private`/`protected`), e o acesso é controlado através de métodos públicos (Getters). A integridade dos dados é garantida, pois o saldo só pode ser modificado através de operações de negócio validadas.
* **Abstração:** A classe `Conta` é `abstract` e define um "contrato" com métodos abstratos, garantindo que todas as classes filhas possuam as funcionalidades essenciais.

#### 🏗️ Padrões de Design e Princípios
* **Arquitetura em Camadas:** O projeto é dividido em `Model` (regras de negócio), `Repository` (acesso a dados), `Service` (orquestração) e `View/Application` (interação com o usuário).
* **Gestão de Estado:** A classe `Main` gerencia o "estado" da aplicação, sabendo se um usuário está "logado" ou não, e exibindo menus contextuais.
* **DRY (Don't Repeat Yourself):** A criação de métodos auxiliares (`protected` na classe `Conta` e `private` na `Main`) foi uma prática constante para evitar a duplicação de código e centralizar lógicas comuns.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17 (LTS)**
* **Git & GitHub** para versionamento de código.

---

## 🚀 Como Executar o Projeto

1.  Clone o repositório: `git clone https://github.com/MateusLima909/Sistema-Bancario-Simples`
2.  Abra o projeto na sua IDE de preferência (IntelliJ, Eclipse, VSCode com o Java Extension Pack).
3.  Localize e execute o arquivo `Main.java`.

---

## 📈 Próximos Passos e Melhorias Futuras

Este projeto serviu como uma base sólida e, embora completo para os fins do desafio, ele foi projetado para ser expansível. Pretendo continuar a evoluí-lo, aplicando funcionalidades e tecnologias ainda mais avançadas.

* **Refatoração para Tipos Avançados:**
    * Implementar o uso de **Collections Frameworks e tipos modernos do Java**, como:
        * `Enum` para os tipos de conta (`TipoConta`), tornando o código mais seguro e legível.
        * `Record` para classes de modelo imutáveis como `Cliente` e `Transacao`.
        * `Interface` para desacoplar ainda mais as camadas (ex: `IContaService`, `IContaRepository`), permitindo a troca de implementações e facilitando testes.
* **Melhorias na Lógica de Negócio:**
    * Substituir o tipo `double` por `BigDecimal` para todas as operações monetárias, garantindo a precisão absoluta e evitando problemas de arredondamento de ponto flutuante.
* **Novas Funcionalidades:**
    * **Persistência de Dados:** Implementar uma forma de salvar e carregar os dados das contas, seja em arquivos (JSON/CSV) ou em um banco de dados em memória (como o H2), para que as informações não se percam ao fechar a aplicação.
    * **Criação de uma API REST:** Expor as funcionalidades do `ContaService` através de endpoints HTTP utilizando o framework Spring Boot, permitindo que outras aplicações (web, mobile) consumam os serviços do banco.

---

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mateuslima-santos/)
[![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)](https://github.com/MateusLima909)
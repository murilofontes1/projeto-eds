# Sistema de Finanças Pessoais

Este projeto é uma aplicação em Java para o gerenciamento de finanças pessoais, permitindo que o usuário registre receitas e despesas, visualize seu saldo e gere relatórios de movimentações financeiras. O sistema utiliza PostgreSQL como banco de dados para armazenar informações sobre os usuários e suas movimentações.

## Funcionalidades

- **Criar Conta**: Permite ao usuário criar uma nova conta no sistema.
- **Login**: Usuário pode acessar sua conta utilizando seu CPF.
- **Movimentação Financeira**: Usuário pode adicionar receitas e despesas.
- **Visualizar Saldo**: Exibe o saldo atual do usuário.
- **Gerar Relatório**: Gera um relatório das movimentações de um período específico.

## Estrutura do Projeto

### Diagrama de Caso de Uso

![Diagrama de Caso de Uso](https://github.com/murilofontes1/projeto-eds/blob/main/documents/diagrama_de_caso_de_uso.png)

### Diagrama Entidade-Relacionamento

![Diagrama Entidade-Relacionamento](https://github.com/murilofontes1/projeto-eds/blob/main/documents/diagrama_entidade_relacionamento.png)

### Diagrama de Classes

![Diagrama de Classes](https://github.com/murilofontes1/projeto-eds/blob/main/documents/diagrama_de_classes.png)

### Descrição das Classes

- **Main**: Classe principal que inicia a aplicação, gerencia o menu principal e realiza a conexão com o banco de dados. Ela controla o fluxo da aplicação, orientando o usuário nas operações disponíveis.
  
- **Usuario**: Representa um usuário do sistema e contém informações como nome, CPF e saldo. Inclui métodos para autenticação e consultas, além de métodos para modificar o saldo com base nas movimentações.
  
- **Movimentacao**: Representa uma movimentação financeira (receita ou despesa) associada a um usuário. Contém detalhes sobre o tipo de movimentação, valor e data. A classe também inclui métodos para registrar novas movimentações e atualizar o saldo do usuário.
  
- **Relatorio**: Classe responsável pela geração de relatórios financeiros. Filtra e exibe todas as movimentações de um usuário dentro de um intervalo de datas especificado.

## Pré-requisitos

- **Java 11** ou superior
- **PostgreSQL 13** ou superior
- **Bibliotecas JDBC** para conexão com PostgreSQL

## Configuração e Execução do Projeto

1. **Configurar o Banco de Dados**:
   - Crie um banco de dados no PostgreSQL chamado `financas_pessoais`.
   - Execute os comandos SQL abaixo para criar as tabelas necessárias:

     ```sql
     CREATE TABLE usuario (
       id SERIAL PRIMARY KEY,
       nome VARCHAR(100) NOT NULL,
       cpf VARCHAR(11) UNIQUE NOT NULL,
       saldo NUMERIC(10, 2) DEFAULT 0
     );

     CREATE TABLE movimentacao (
       id SERIAL PRIMARY KEY,
       usuario_id INTEGER REFERENCES usuario(id),
       tipo VARCHAR(10) NOT NULL,  -- 'receita' ou 'despesa'
       valor NUMERIC(10, 2) NOT NULL,
       data DATE NOT NULL
     );
     ```

2. **Configurar Conexão com o Banco de Dados**:
   - No código Java, configure a URL, usuário e senha para o banco de dados PostgreSQL:

     ```java
     String url = "jdbc:postgresql://localhost:5432/financas_pessoais";
     String usuario = "seu_usuario";
     String senha = "sua_senha";
     ```

3. **Compilar e Executar o Projeto**:
   - Compile o projeto utilizando o comando:

     ```bash
     javac *.java
     ```

   - Execute o projeto:

     ```bash
     java Main
     ```

## Exemplo de Uso

- Ao iniciar o sistema, o usuário poderá criar uma conta, fazer login, adicionar receitas e despesas, visualizar seu saldo e gerar relatórios com base em um intervalo de datas específico.

## Observações

Para garantir a execução correta do sistema, assegure-se de que as bibliotecas JDBC para PostgreSQL estejam incluídas no classpath do projeto.

---

Este projeto foi desenvolvido para facilitar o controle financeiro pessoal, fornecendo uma interface simples e direta para o usuário acompanhar suas movimentações financeiras.

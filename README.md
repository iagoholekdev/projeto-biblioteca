# Projeto Biblioteca

Este é o projeto **Biblioteca**, uma aplicação para gerenciar empréstimos de livros, construída com o Quarkus, um framework Java moderno e reativo.

## Tecnologias Usadas

- **Quarkus**: Framework Java para desenvolvimento de aplicações leves e rápidas.
- **Hibernate Reactive com Panache**: ORM reativo para comunicação com o banco de dados.
- **PostgreSQL**: Banco de dados utilizado.
- **Jackson**: Serialização e desserialização de objetos em JSON.
- **Lombok**: Facilita a escrita de código Java.
- **Flyway**: Gerenciamento de migrações de banco de dados.
- **JUnit 5**: Framework para testes unitários.
- **Mockito**: Biblioteca de mocks para testes unitários.
- **Logback**: Framework para logging.

## Requisitos

Certifique-se de que você possui os seguintes requisitos instalados:

- **Java 17** ou superior
- **Gradle**
- **PostgreSQL**

## Executando o Projeto

### Modo de Desenvolvimento

A aplicação estará disponível em http://localhost:8080. A interface Dev UI pode ser acessada em http://localhost:8080/q/dev.


Para rodar a aplicação em modo de desenvolvimento, com suporte a _live coding_, utilize o comando:

```bash
./gradlew quarkusDev
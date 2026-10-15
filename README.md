# DIO Spring Boot — Projeto Final 05: Spring AI

## Introdução

Este projeto desenvolve uma API de controle de gastos utilizando Spring Boot e Spring AI, mantendo uma arquitetura em camadas.

A aplicação permite registrar e consultar transações financeiras, além de processar comandos de voz utilizando inteligência artificial.

## Funcionalidades

- Receber arquivos de áudio com comandos financeiros.
- Converter áudio em texto por meio de reconhecimento de fala.
- Utilizar um modelo de IA para interpretar solicitações e selecionar ferramentas da aplicação.
- Registrar transações financeiras no banco de dados.
- Consultar transações por categoria.
- Listar todas as transações registradas por meio do extrato.
- Gerar respostas em áudio.

## Melhoria implementada: extrato de transações

A melhoria implementada foi a criação de uma funcionalidade para listar todas as transações financeiras registradas.

Foram realizadas as seguintes alterações:

- Adição do método `findAll()` ao contrato `TransactionRepository`.
- Implementação da consulta de todas as transações no repositório de persistência.
- Criação do caso de uso `ListAllTransactionsUseCase`, disponibilizado também como ferramenta para o modelo de IA.
- Criação do endpoint `GET /transactions` para consultar o extrato.

A resposta contém as transações registradas, incluindo identificador, categoria, descrição e valor.

## Tecnologias utilizadas

- Java 25
- Spring Boot
- Spring AI
- Gradle
- Spring Data JPA
- Hibernate
- MySQL
- Docker Compose
- OpenAI: GPT-4o-mini, Whisper e GPT-4o-mini-TTS
- Google Gemini, conforme configuração do modelo de chat

## Arquitetura do projeto

- `src/main/java/dio/budgeting/domain`: modelos de domínio e contratos dos repositórios.
- `src/main/java/dio/budgeting/application`: casos de uso e ferramentas disponibilizadas para a IA.
- `src/main/java/dio/budgeting/infrastructure`: controladores HTTP, persistência com JPA e integrações externas.

## Como executar

### Pré-requisitos

- Java 25
- Docker e Docker Compose
- Chaves de API dos provedores utilizados pela configuração da aplicação

Configure as variáveis de ambiente necessárias no terminal. Não coloque chaves reais diretamente no código ou no README.

```bash
export OPENAI_API_KEY="sua_chave_openai"
export GOOGLE_API_KEY="sua_chave_google"
```

Inicie o banco de dados conforme a configuração do projeto:

```bash
docker compose up -d
```

Execute a aplicação:

```bash
./gradlew bootRun
```

Para executar os testes automatizados:

```bash
./gradlew test
```

## Como testar o extrato

Com a aplicação em execução, faça uma requisição GET ao endpoint:

```bash
curl -i http://localhost:8080/transactions
```

O endpoint retorna uma lista de transações em JSON. Quando houver registros, cada item incluirá campos como `id`, `category`, `description` e `amount`.

Também é possível consultar as transações por categoria utilizando o endpoint correspondente.

## Aprendizados

- Organização de aplicações em camadas.
- Separação entre domínio, casos de uso e infraestrutura.
- Uso do padrão Repository para acesso aos dados.
- Integração de modelos de IA com ferramentas da aplicação.
- Persistência de transações com Spring Data JPA e MySQL.
- Criação e teste de endpoints REST.

## Documentação

- [Spring AI](https://docs.spring.io/spring-ai/reference/index.html)
- [ChatClient](https://docs.spring.io/spring-ai/reference/api/chatclient.html)
- [Tools](https://docs.spring.io/spring-ai/reference/api/tools.html)
- [Audio Transcriptions](https://docs.spring.io/spring-ai/reference/api/audio/transcriptions.html)
- [Audio Speech](https://docs.spring.io/spring-ai/reference/api/audio/speech.html)

## Observações

Este projeto foi desenvolvido como atividade educacional da trilha de Spring Boot da DIO. Os testes de integração com provedores externos podem exigir credenciais válidas e acesso à internet.

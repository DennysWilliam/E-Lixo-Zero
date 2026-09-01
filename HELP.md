# Ajuda Rápida - E-Lixo Zero

## Como Iniciar o Sistema

### 1. Banco de Dados (PostgreSQL)

1. Certifique-se de que o PostgreSQL está rodando.
2. Crie o banco de dados `elixozero`.
3. Ajuste o arquivo `e-lixo-zero/BackEnd/src/main/resources/application.properties` com suas credenciais.

### 2. Back-end (Spring Boot)

```bash
cd e-lixo-zero/BackEnd
./mvnw spring-boot:run
```

A API será iniciada em `http://localhost:8087`.

### 3. Front-end (Angular)

```bash
cd Codificação/FrontEnd/e-lixo-zero
npm install
ng serve
```

A aplicação será iniciada em `http://localhost:4200`.

## Problemas Comuns

### Erro: porta 8087 em uso

Altere a porta em `application.properties`:

```properties
server.port=8088
```

### Erro: falha na autenticação do banco

Verifique se o usuário, senha e URL estão corretos no `application.properties`.

### Erro: tabelas não existem

O projeto usa **JDBC puro**, então as tabelas não são criadas automaticamente. Execute os scripts SQL em `src/main/resources/lds-db-scripts/` no banco `elixozero` antes de iniciar a aplicação.

### Erro: CORS no navegador

O back-end já permite origens com `@CrossOrigin("*")` nos controllers. Se o erro persistir, verifique se o front está apontando para a URL correta da API.

## Dicas

- Sempre reinicie o back-end após alterar o `application.properties`.
- Para testes de login, crie um usuário pelo endpoint `POST /api/usuarios` ou use dados inseridos pelo `DataInitializer`.

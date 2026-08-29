# Configuração do PostgreSQL para E-Lixo Zero

## Pré-requisitos
- PostgreSQL instalado (recomendado versão 14+)
- Conhecimento básico de PostgreSQL

## Passo 1: Instalar PostgreSQL

### Windows:
1. Baixe o instalador em: https://www.postgresql.org/download/windows/
2. Execute o instalador com as configurações padrão
3. Anote a senha do usuário `postgres` durante a instalação

### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql
sudo systemctl enable postgresql
```

## Passo 2: Criar o Banco de Dados

### Via pgAdmin (Interface Gráfica):
1. Abra o pgAdmin
2. Conecte ao servidor PostgreSQL
3. Clique com botão direito em "Databases" > "Create" > "Database"
4. Nome: `elixozero`
5. Clique em "Save"

### Via Linha de Comando:
```bash
# Conectar ao PostgreSQL
psql -U postgres

# Criar banco de dados
CREATE DATABASE elixozero;

# Sair
\q
```

## Passo 3: Configurar Credenciais

### Opção 1: Usar usuário postgres padrão
Mantenha as configurações atuais no `application.properties`:
```properties
spring.datasource.username=postgres
spring.datasource.password=sua_senha_aqui
```

### Opção 2: Criar usuário dedicado
```sql
-- Conectar ao PostgreSQL
psql -U postgres

-- Criar usuário
CREATE USER elixozero_user WITH PASSWORD 'sua_senha_aqui';

-- Conceder privilégios
GRANT ALL PRIVILEGES ON DATABASE elixozero TO elixozero_user;

-- Sair
\q
```

Atualize o `application.properties`:
```properties
spring.datasource.username=elixozero_user
spring.datasource.password=sua_senha_aqui
```

## Passo 4: Atualizar application.properties

Verifique se o arquivo `src/main/resources/application.properties` está configurado corretamente:

```properties
spring.application.name=BackEnd

# Database Configuration - PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/elixozero
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server Configuration
server.port=8080
```

## Passo 5: Testar Conexão

1. Atualize a senha no `application.properties`
2. Execute o projeto:
```bash
./mvnw spring-boot:run
```

3. Verifique os logs para confirmar que a conexão foi estabelecida com sucesso

## Solução de Problemas

### Erro: "Connection refused"
- Verifique se o PostgreSQL está rodando:
  - Windows: Serviços do Windows > PostgreSQL
  - Linux: `sudo systemctl status postgresql`

### Erro: "FATAL: password authentication failed"
- Verifique a senha no `application.properties`
- Certifique-se de que o usuário existe no PostgreSQL

### Erro: "FATAL: database elixozero does not exist"
- Crie o banco de dados seguindo o Passo 2

### Porta padrão diferente
Se seu PostgreSQL usa uma porta diferente de 5432, atualize a URL:
```properties
spring.datasource.url=jdbc:postgresql://localhost:SUA_PORTA/elixozero
```

## Próximos Passos

Após configurar o PostgreSQL:
1. O Hibernate criará automaticamente as tabelas ao iniciar a aplicação
2. O `DataInitializer` populará o banco com dados iniciais
3. A aplicação estará pronta para uso
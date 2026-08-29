# Como Alternar Entre H2 e PostgreSQL

## PostgreSQL (Configuração Atual)

O projeto está configurado para usar PostgreSQL com as seguintes configurações:

```properties
# PostgreSQL Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/elixozero
spring.datasource.username=postgres
spring.datasource.password=Chico-2019
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server Configuration
server.port=8087
```

### Banco de Dados PostgreSQL
- **Nome do banco**: elixozero
- **Usuário**: postgres
- **Senha**: Chico-2019
- **Porta**: 5432
- **Host**: localhost

### Status Atual
✅ PostgreSQL está funcionando corretamente
✅ Banco de dados 'elixozero' foi criado
✅ Tabelas foram criadas automaticamente pelo Hibernate
✅ Aplicação iniciou com sucesso na porta 8087

## H2 Database (Para Desenvolvimento)

Se quiser voltar a usar o H2 (mais simples para desenvolvimento), altere o `application.properties` para:

```properties
# H2 Database Configuration (Development)
spring.datasource.url=jdbc:h2:mem:elixozero
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# H2 Console (for viewing database data)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server Configuration
server.port=8087
```

### Acesso ao Console H2
Quando usando H2, acesse: `http://localhost:8087/h2-console`
- JDBC URL: `jdbc:h2:mem:elixozero`
- User: `sa`
- Password: (deixar em branco)

## Comandos Úteis PostgreSQL

### Criar banco de dados
```powershell
$env:PGPASSWORD='Chico-2019'; & "C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -h localhost -c "CREATE DATABASE elixozero;"
```

### Conectar ao banco
```powershell
$env:PGPASSWORD='Chico-2019'; & "C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -h localhost -d elixozero
```

### Listar bancos de dados
```powershell
$env:PGPASSWORD='Chico-2019'; & "C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -h localhost -c "\l"
```

### Verificar se PostgreSQL está rodando
```powershell
netstat -ano | findstr :5432
```

## Resumo

**Atualmente configurado para: PostgreSQL** ✅

Para alternar:
1. Edite `src/main/resources/application.properties`
2. Substitua as configurações do banco de dados
3. Reinicie a aplicação: `.\mvnw spring-boot:run`

O Hibernate criará automaticamente as tabelas se o `ddl-auto=update` estiver configurado.
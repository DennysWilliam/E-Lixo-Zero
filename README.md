# E-Lixo Zero

Sistema web para facilitar o descarte correto de resíduos eletrônicos no município de Santa Rita do Sapucaí - MG.

## Sobre o Projeto

O E-Lixo Zero conecta cidadãos, pontos de coleta e gestores para promover o descarte consciente de resíduos eletrônicos e contribuir com a preservação ambiental.

A plataforma permite localizar pontos de coleta, solicitar coletas residenciais, acompanhar agendamentos e receber notificações sobre ações de descarte sustentável.

## Objetivos

- Incentivar o descarte correto de resíduos eletrônicos
- Facilitar o acesso a pontos de coleta
- Permitir o agendamento de coletas residenciais
- Promover ações de conscientização ambiental
- Centralizar informações sobre resíduos eletrônicos

## Funcionalidades

### Áreas públicas

- Página inicial
- Página "Sobre"
- Página "Como Funciona"
- Visualização de pontos de coleta
- Cadastro de usuários
- Login de usuários

### Áreas restritas

- Dashboard personalizado
- Solicitação de coleta
- Visualização das coletas realizadas
- Gerenciamento de resíduos
- Central de notificações
- Perfil do usuário

## Tecnologias Utilizadas

- **Front-end:** Angular 21, TypeScript, SCSS, HTML5
- **Back-end:** Java 17, Spring Boot 3.5+, Spring Security (BCrypt), JWT
- **Banco de dados:** PostgreSQL
- **Ferramentas:** Maven, Node.js, npm, Git

## Estrutura do Projeto

```
E-Lixo-Zero/
├── e-lixo-zero/
│   ├── BackEnd/
│   │   ├── src/main/java/br/fai/lds/e_lixo_zero/
│   │   │   ├── configuration/
│   │   │   ├── controller/
│   │   │   ├── domain/
│   │   │   ├── dto/
│   │   │   ├── exceptions/
│   │   │   ├── ports_and_adapters/
│   │   │   └── security/
│   │   └── src/main/resources/
│   └── FrontEnd/
│       ├── src/app/
│       │   ├── core/
│       │   ├── layouts/
│       │   ├── models/
│       │   ├── pages/
│       │   ├── services/
│       │   └── shared/
│       ├── public/images/
│       └── angular.json
├── ApêndiceA-Planejamento/
├── ApêndiceB-Requisitos/
├── ApêndiceC-Análise/
└── ApêndiceD-AnáliseDosRequisitos/
```

## Pré-requisitos

- Java 17
- Maven
- PostgreSQL 14+
- Node.js 20+
- npm
- Angular CLI

## Como Executar

### 1. Configurar o banco de dados

1. Crie um banco de dados chamado `elixozero` no PostgreSQL.
2. Ajuste as credenciais em `e-lixo-zero/BackEnd/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/elixozero
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
```

3. Execute os scripts SQL disponíveis em `src/main/resources/lds-db-scripts/`, se necessário.

### 2. Iniciar o back-end

```bash
cd e-lixo-zero/BackEnd
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8087`.

### 3. Iniciar o front-end

```bash
cd e-lixo-zero/FrontEnd
npm install
ng serve
```

A aplicação estará disponível em `http://localhost:4200`.

## Endpoints Principais

- `POST /api/usuarios` - Cadastro de usuários
- `POST /api/usuarios/login` - Autenticação (retorna JWT)
- `GET /api/pontos-coleta` - Lista pontos de coleta
- `GET /api/residuos` - Lista tipos de resíduos
- `GET /api/coletas` - Lista coletas do usuário autenticado
- `POST /api/coletas` - Solicita uma nova coleta
- `GET /api/notificacoes` - Lista notificações do usuário

## Licença

Este projeto foi desenvolvido exclusivamente para fins acadêmicos.

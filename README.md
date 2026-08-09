# ♻️ E-Lixo Zero

Sistema web para facilitar o descarte correto de resíduos eletrônicos no município de Santa Rita do Sapucaí - MG.

## 📋 Sobre o Projeto

O E-Lixo Zero foi desenvolvido com o objetivo de conectar cidadãos, pontos de coleta e gestores, promovendo o descarte consciente de resíduos eletrônicos e contribuindo para a preservação ambiental.

A plataforma permite que os usuários localizem pontos de coleta, solicitem coletas residenciais, acompanhem seus agendamentos e recebam notificações sobre campanhas e ações relacionadas ao descarte sustentável.

## 🎯 Objetivos

* Incentivar o descarte correto de resíduos eletrônicos;
* Facilitar o acesso a pontos de coleta;
* Permitir o agendamento de coletas residenciais;
* Promover ações de conscientização ambiental;
* Centralizar informações sobre resíduos eletrônicos.

## 🚀 Funcionalidades

### Áreas públicas

* Página inicial;
* Página "Sobre";
* Página "Como Funciona";
* Visualização de pontos de coleta;
* Cadastro de usuários;
* Login de usuários.

### Áreas restritas

* Dashboard personalizado;
* Solicitação de coleta;
* Visualização das coletas realizadas;
* Gerenciamento de resíduos;
* Central de notificações;
* Perfil do usuário.

## 🛠️ Tecnologias Utilizadas

### Front-end

* Angular 21
* TypeScript
* SCSS
* HTML5

### Back-end (Mock API)

* JSON Server

### Ferramentas

* Node.js
* npm
* Visual Studio Code
* Git
* GitHub

## 📁 Estrutura do Projeto

```text
Codificação/
└── FrontEnd/
    └── e-lixo-zero/
        ├── public/
        │   └── images/
        ├── src/
        │   ├── app/
        │   │   ├── core/
        │   │   ├── layouts/
        │   │   ├── models/
        │   │   ├── pages/
        │   │   ├── services/
        │   │   └── shared/
        │   └── styles.scss
        ├── db.json
        ├── package.json
        └── angular.json
```

## ⚙️ Pré-requisitos

Antes de executar o projeto, certifique-se de possuir instalado:

* Node.js (versão 20 ou superior)
* npm
* Angular CLI

Verifique as versões instaladas:

```bash
node -v
npm -v
ng version
```

Caso não possua o Angular CLI:

```bash
npm install -g @angular/cli
```

## ▶️ Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/DennysWilliam/E-Lixo-Zero.git
```

### 2. Acesse a pasta do projeto

```bash
cd E-Lixo-Zero/Codificação/FrontEnd/e-lixo-zero
```

### 3. Instale as dependências

```bash
npm install
```

### 4. Execute a API Mock

Em um terminal, execute:

```bash
npm run start-api
```

Caso o script não exista:

```bash
npx json-server --watch db.json --port 3000
```

A API estará disponível em:

```text
http://localhost:3000
```

### 5. Execute a aplicação Angular

Abra um novo terminal na mesma pasta e execute:

```bash
ng serve
```

A aplicação estará disponível em:

```text
http://localhost:4200
```

## 🔑 Usuário de Teste

Utilize as credenciais abaixo para acessar o sistema:

```text
E-mail: joao@gmail.com
Senha: 123456
```

Ou realize um novo cadastro diretamente pela plataforma.

## 📌 Scripts Disponíveis

```bash
npm start
```

Executa a aplicação Angular.

```bash
npm run start-api
```

Inicia o JSON Server.

```bash
ng build
```

Gera a versão de produção.

## 📄 Licença

Este projeto foi desenvolvido exclusivamente para fins acadêmicos.

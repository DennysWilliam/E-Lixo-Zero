# Criptografia de Senhas com BCrypt

## Implementação Realizada

Adicionei criptografia de senhas usando BCrypt para melhorar a segurança do sistema.

### Mudanças Realizadas:

1. **Adicionada dependência Spring Security** (pom.xml):
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-security</artifactId>
   </dependency>
   ```

2. **Criado SecurityConfig.java**:
   - Configuração do Spring Security
   - Bean BCryptPasswordEncoder para criptografia
   - CSRF desabilitado para permitir requisições da API
   - Todas as requisições permitidas (sem autenticação básica)

3. **Atualizado UsuarioService.java**:
   - Injeção de PasswordEncoder
   - Criptografia de senhas no método `criar()`
   - Criptografia de senhas no método `atualizar()`
   - Verificação de senhas usando BCrypt no método `autenticar()`

4. **Atualizado DataInitializer.java**:
   - Injeção de PasswordEncoder
   - Criptografia das senhas dos usuários de teste
   - Senhas agora são salvas de forma criptografada no banco

### Segurança Implementada:

✅ **Senhas nunca são armazenadas em texto puro**
✅ **BCrypt usa salt automático para cada senha**
✅ **Verificação segura durante login**
✅ **Compatível com banco PostgreSQL**

### Usuários de Teste (com senhas criptografadas):

1. **João Silva**
   - Email: joao@gmail.com
   - Senha: 123456
   - Senha criptografada no banco

2. **Dennys**
   - Email: dennys@gmail.com
   - Senha: 1234
   - Senha criptografada no banco

3. **Leo**
   - Email: leo@gmail.com
   - Senha: 123
   - Senha criptografada no banco

### Como Funciona:

1. **Cadastro**: A senha é criptografada com BCrypt antes de salvar no banco
2. **Login**: A senha fornecida é comparada com o hash armazenado usando BCrypt
3. **Atualização**: Se a senha for alterada, ela é criptografada novamente

### Exemplo de Hash BCrypt:

- Senha: "123456"
- Hash: "$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJ3m4kZ7XV5K7xG5Y5Y5Y5Y5Y5Y5"

O hash é sempre diferente para a mesma senha devido ao salt automático.

### No PostgreSQL:

As senhas agora aparecem assim no banco:
```
senha | $2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJ3m4kZ7XV5K7xG5Y5Y5Y5Y5Y5Y
```

Isso garante que mesmo com acesso ao banco, as senhas originais não podem ser recuperadas.

### Próximos Passos Opcionais:

Para maior segurança, você pode:
1. Adicionar autenticação JWT para APIs
2. Implementar autorização por roles
3. Adicionar validação de força de senha
4. Implementar recuperação de senha com email
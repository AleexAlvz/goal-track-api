# 📌 Endpoints da Goal Track API

Para um fácil inicio no projeto, fica disponível para consulta [os imports para o insomnia](../insomnia).

## 🔒 Autenticação 

### Registro de Usuário
```
Endpoint: POST /auth/register

Descrição: Cria um novo usuário com email e senha

Request Body:

{
"email": "alex@example.com",
"password": "123456"
}


Response:

"User registered successfully"


Observações:

Senha é hashada automaticamente

Evita registro duplicado pelo mesmo email
```

### Login de Usuário
```
Endpoint: POST /auth/login

Descrição: Autentica usuário e retorna JWT válido

Request Body:

{
"email": "alex@example.com",
"password": "123456"
}


Response:

{
"token": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9..."
}


Observações:

Token expira em 1 hora

Deve ser enviado no header Authorization como 'Bearer <token>' nos endpoints protegidos
```

## 🧪 Endpoint de Teste Protegido
```
Endpoint: GET /hello

Descrição: Retorna mensagem de teste para usuários autenticados

Header:

Authorization: Bearer <token>


Response:

"Hello, authenticated user!"


Observações:

Endpoint protegido pelo filtro JWT

Qualquer requisição sem token válido retorna 401 Unauthorized
```

## 🔹 Observações Gerais

Todos os endpoints dentro de /auth/** são públicos

Todos os demais endpoints exigem JWT válido

Futuras rotas de Metas e Ações Periódicas seguirão o mesmo padrão de autenticação

Token deve ser incluído no header Authorization em todas requisições protegidas
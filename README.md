<img src="https://files.tecnoblog.net/wp-content/uploads/2021/12/mercado-livre-logo.jpg" alt="exemplo imagem" width="100px" align="right">
<img src="" alt="exemplo imagem" width="100px" align="right">

# 🍃 Desafio Spring - DigitalHouse

Projeto feito no bootcamp [Java BackEnd - MercadoLivre](https://www.mercadolibre.com.ar/itacademy) com objetivo de criar uma [CRUD](https://developer.mozilla.org/pt-BR/docs/Glossary/CRUD) a partir da criação de uma API RESTful utilizando a estrutura MSC.

## ⭐ Habilidades

- Utilizar as habilidades ministradas em aula pela DigitalHouse.
- Criar uma API utilizando o Spring.

## 💻 Como iniciar

1. Faça o clone do projeto
2. Entre no IntelliJ
3. Abra o projeto
```shell
npm install
```
4. Inicie a aplicação
```shell
npm run debug
```

# 💡 Documentação da API

## Cadastra um usuário

```http
  POST /user
```

| Corpo da requisição   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `displayName, email, password, image` | `json` | **Obrigatório**. Todos os campos do corpo da requisição |

**Formato do corpo da requisição**
```json
{
  "displayName": "Nome da Pessoa",
  "email": "emaildapessoa@email.com",
  "password": "123456",
  "image": "http://4.bp.blogspot.com/_YA50adQ-7vQ/S1gfR_6ufpI/AAAAAAAAAAk/1ErJGgRWZDg/S45/imagem.png"
}
```

**Retorno em caso de sucesso**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7ImlkIjo1LCJkaXNwbGF5TmFtZSI6InVzdWFyaW8gZGUgdGVzdGUiLCJlbWFpbCI6InRlc3RlQGVtYWlsLmNvbSIsImltYWdlIjoibnVsbCJ9LCJpYXQiOjE2MjAyNDQxODcsImV4cCI6MTYyMDY3NjE4N30.Roc4byj6mYakYqd9LTCozU1hd9k_Vw5IWKGL4hcCVG8"
}
```

> O retorno acima é apenas fictício



## Retorna todos os usuários

```http
  GET /user/
```

**⚠️ Para fazer uma requisição é necessário estar com a chave `Authentication` com o token JWT recebido anteriormente**

**Retorno em caso de sucesso**

```json
[
  {
    "id": "401465483996",
    "displayName": "Brett Wiltshire",
    "email": "brett@email.com",
    "image": "http://4.bp.blogspot.com/_YA50adQ-7vQ/S1gfR_6ufpI/AAAAAAAAAAk/1ErJGgRWZDg/S45/brett.png"
  }
]
```

| Descrição |
| :--------- |
| Será retornado um array com todos os usuários do banco de dados |

### Retorna um usuário

```http
  GET /user/${id}
```

**⚠️ Para fazer uma requisição é necessário estar com a chave `Authentication` com o token JWT recebido anteriormente**

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do usuário que você quer |

**Retorno em caso de sucesso**

```json
{
  "id": "401465483996",
  "displayName": "Brett Wiltshire",
  "email": "brett@email.com",
  "image": "http://4.bp.blogspot.com/_YA50adQ-7vQ/S1gfR_6ufpI/AAAAAAAAAAk/1ErJGgRWZDg/S45/brett.png"
}
```

| Descrição |
| :--------- |
| Será retornado o usuário com o id especificado |

### Logar de acordo com o banco de dados

```http
  POST /login
```

| Corpo da requisição   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `email, password`      | `json` | **Obrigatório**. Todos os campos do Corpo da requisição devem existir no banco de dados |

**Formato do corpo da requisição**

```json
{
  "email": "email@mail.com",
  "password": "123456"
}
```

**Retorno em caso de sucesso**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7ImlkIjo1LCJkaXNwbGF5TmFtZSI6InVzdWFyaW8gZGUgdGVzdGUiLCJlbWFpbCI6InRlc3RlQGVtYWlsLmNvbSIsImltYWdlIjoibnVsbCJ9LCJpYXQiOjE2MjAyNDQxODcsImV4cCI6MTYyMDY3NjE4N30.Roc4byj6mYakYqd9LTCozU1hd9k_Vw5IWKGL4hcCVG8"
}
```

> O retorno acima é apenas fictício

## Cadastra uma nova categoria

```http
  POST /categories
```

| Corpo da requisição   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name` | `json` | **Obrigatório**. O nome da categoria deve ser obrigatório |

**⚠️ Para fazer uma requisição é necessário estar com a chave `Authentication` com o token JWT recebido anteriormente**

**Formato do corpo da requisição**

```json
 {
   "name": "Ação"
 }
```

**Retorno em caso de sucesso**

```json
{
  "id": 1
  "name": "Ação"
}
```

## Retorna todas as categorias

```http
  GET /categories
```

**⚠️ Para fazer uma requisição é necessário estar com a chave `Authentication` com o token JWT recebido anteriormente**

**Retorno em caso de sucesso**

```json
[
  {
    "id": 1,
    "name": "Escola"
  },
  {
    "id": 2,
    "name": "Inovação"
  }
]
```

| Descrição |
| :--------- |
| Será retornado todas as categorias do Banco de Dados |

### Cadastra uma nova publicação

```http
  POST /post
```

| Corpo da requisição   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `title, content, categoryIds` | json | **Obrigatório**. Todos os campos devem ser obrigatórios |

**⚠️ Para fazer uma requisição é necessário estar com a chave `Authentication` com o token JWT recebido anteriormente**

**Formato do corpo da requisição**

```json
{
  "title": "Titulo da publicação",
  "content": "Conteudo da publicação aqui",
  "categoryIds": [1, 2] //id das categorias criadas anteiormente, deve ser somente um array.
}
```

**Retorno em caso de sucesso**
```json
{
  "id": "$id da publicação",
  "userId": 1, // usuário autenticado
  "title": "Titulo da publicação",
  "content": "Conteudo da publicação aqui"
}
```

## Retorna todos as publicações

```http
  GET /categories
```

**⚠️ Para fazer uma requisição é necessário estar com a chave `Authentication` com o token JWT recebido anteriormente**

**Retorno em caso de sucesso**

```json
[
  {
    "id": 1,
    "title": "Post do Ano",
    "content": "Melhor post do ano",
    "userId": 1,
    "published": "2011-08-01T19:58:00.000Z",
    "updated": "2011-08-01T19:58:51.000Z",
    "user": {
      "id": 1,
      "displayName": "Lewis Hamilton",
      "email": "lewishamilton@gmail.com",
      "image": "https://upload.wikimedia.org/wikipedia/commons/1/18/Lewis_Hamilton_2017_Malaysia.jpg"
    },
    "categories": [
      {
        "id": 1,
        "name": "Inovação"
      }
    ]
  }
]
```

| Descrição |
| :--------- |
| Será retornado todas as publicações do Banco de Dados, com sua categoria e o autor |



## Feito Com:
[![IDE](https://img.shields.io/badge/Visual_studio_code-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white)](https://code.visualstudio.com/)
[![NODEJS](https://img.shields.io/badge/Node.js-339933?style=for-the-badge&logo=nodedotjs&logoColor=white)](https://nodejs.org/en/)
[![SEQUELIZE](https://img.shields.io/badge/Sequelize-52B0E7?style=for-the-badge&logo=Sequelize&logoColor=white)](https://sequelize.org/master/)
[![MYSQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)


### Contato

[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/michaelcaxias/)

<p align="center">Copyright © 2021 Michael Caxias</p>

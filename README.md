<img src="https://github.com/thays-gama/desafio_spring/blob/main/src/main/resources/images/dh.png" alt="logotipo Digital House" width="140px" align="right">
<img src="https://github.com/thays-gama/desafio_spring/blob/main/src/main/resources/images/meli.png" alt="logotipo Mercado Livre" width="100px" align="right">

# 🍃 Desafio Spring - DigitalHouse

Projeto feito no bootcamp [Java BackEnd - MercadoLivre](https://www.mercadolibre.com.ar/itacademy) com objetivo de criar uma [CRUD](https://developer.mozilla.org/pt-BR/docs/Glossary/CRUD) a partir da criação de uma API RESTful utilizando a estrutura MSC.

## ⭐ Habilidades

- Utilizar as habilidades ministradas em aula pela DigitalHouse.
  - POO
  - Java
  - Spring
  - REST e RESTful
  - JSON
  - Arquitetura MVC
- Criar uma API utilizando o Spring.

## 💻 Como iniciar na IDE

1. Faça o clone do projeto:
```shell
git clone git@github.com:thays-gama/desafio_spring.git
```
2. Abra o projeto na sua IDE de escolha:
```shell
mvn spring-boot:run
```

## 🧑🏻‍🚀 Como testar no Postman

1. Importar o arquivo:
```shell
src/main/resources/MELI - DH.postman_collection.json
```

# 💡 Documentação da API

### Cadastra um produto

```http
 POST /api/v1/insert-articles-request
```

| Corpo da requisição                                                         | Tipo       | Descrição                           |
| :--------------------                                                       | :--------- | :---------------------------------- |
| `productId, name, category, brand, price, quantity, freeShipping, prestige` | `json`     | **Obrigatório**. Todos os campos do corpo da requisição |

**Formato do corpo da requisição**
```json
[
      {
         "productId":23,
         "name":"Serra de Bancada 1",
         "category":"Ferramentas",
         "brand":"FORTGPROp",
         "price":1800.00,
         "quantity":5,
         "freeShipping":true,
         "prestige":"**"
      },    
      {
         "productId":25,
         "name":"Furadeira 2",
         "category":"Ferramentas",
         "brand":"Black&Decker",
         "price":500.00,
         "quantity":7,
         "freeShipping":true,
         "prestige":"**"
      }
]
```

**Retorno em caso de sucesso**

```json
[
    {
        "productId": 23,
        "name": "Serra de Bancada 1",
        "quantity": 5
    },
    {
        "productId": 25,
        "name": "Furadeira 2",
        "quantity": 7
    }
]
```
> O retorno acima é apenas fictício.

### Retorna todos os produtos

```http
  GET /api/v1/articles
```

**Retorno em caso de sucesso**

```json
[
    {
        "productId": 1,
        "name": "Serra de Bancada",
        "category": "Ferramentas",
        "brand": "FORTGPRO",
        "price": 1800.00,
        "quantity": 5,
        "freeShipping": true,
        "prestige": "****"
    },
    {
        "productId": 2,
        "name": "Furadeira",
        "category": "Ferramentas",
        "brand": "Black & Decker",
        "price": 500,
        "quantity": 7,
        "freeShipping": true,
        "prestige": "****"
    }, 
    ...
]
```

| Descrição |
| :--------- |
| Será retornado um array com todos os produtos do arquivo resources/articles.json |

### Retorna os produtos filtrados por categoria.

```http
  GET /api/v1/articles?category=categoryName
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `category`  | `String`   | **Obrigatório**. Parâmetros devem ser passados na URL|

**Retorno em caso de sucesso**

```json
[
    {
        "productId": 9,
        "name": "Camisa",
        "category": "Roupas",
        "brand": "Fila",
        "price": 79.00,
        "quantity": 2,
        "freeShipping": false,
        "prestige": "***"
    },
    {
        "productId": 10,
        "name": "Calcas",
        "category": "Roupas",
        "brand": "Oakley",
        "price": 73.00,
        "quantity": 6,
        "freeShipping": false,
        "prestige": "***"
    },
    {
        "productId": 11,
        "name": "Meias",
        "category": "Roupas",
        "brand": "Gonew",
        "price": 10.00,
        "quantity": 8,
        "freeShipping": false,
        "prestige": "*"
    },
    ...
]
```

| Descrição |
| :-------- |
| Serão retornados os produtos com a categoria especificada |

### Retorna os produtos filtrados por categoria e frete grátis.

```http
  GET api/v1/articles?category=categoryName&freeShipping=false
```

| Corpo da requisição       | Tipo       | Descrição                                   |
| :------------------------ | :--------- | :------------------------------------------ |
| `category, freeShipping`  | `String`   | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**

```json
[
    {
        "productId": 9,
        "name": "Camisa",
        "category": "Roupas",
        "brand": "Fila",
        "price": 79.00,
        "quantity": 2,
        "freeShipping": false,
        "prestige": "***"
    },
    {
        "productId": 10,
        "name": "Calcas",
        "category": "Roupas",
        "brand": "Oakley",
        "price": 73.00,
        "quantity": 6,
        "freeShipping": false,
        "prestige": "***"
    },
    {
        "productId": 11,
        "name": "Meias",
        "category": "Roupas",
        "brand": "Gonew",
        "price": 10.00,
        "quantity": 8,
        "freeShipping": false,
        "prestige": "*"
    }
]
```

> O retorno acima é apenas fictício

### Retorna os produtos filtrados por frete grátis e avaliação.

```http
  GET /api/v1/articles?freeShipping=false&prestige=***
```

| Corpo da requisição       | Tipo       | Descrição                                   |
| :------------------------ | :--------- | :------------------------------------------ |
| `freeShipping, prestige`  | `String`   | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**

```json
[
    {
        "productId": 9,
        "name": "Camisa",
        "category": "Roupas",
        "brand": "Fila",
        "price": 79.00,
        "quantity": 2,
        "freeShipping": false,
        "prestige": "***"
    },
    {
        "productId": 10,
        "name": "Calcas",
        "category": "Roupas",
        "brand": "Oakley",
        "price": 73.00,
        "quantity": 6,
        "freeShipping": false,
        "prestige": "***"
    },
   ...
]
```

### Retorna os produtos por ordem alfabética crescente.

```http
  GET /api/v1/articles?category=categoryName&freeShipping=true&order=0
```

| Corpo da requisição       | Tipo       | Descrição                                   |
| :------------------------ | :--------- | :------------------------------------------ |
| `category, freeShipping`  | `String`   | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**

```json
[
    {
        "productId": 2,
        "name": "Furadeira",
        "category": "Ferramentas",
        "brand": "Black & Decker",
        "price": 500,
        "quantity": 7,
        "freeShipping": true,
        "prestige": "****"
    },
    {
        "productId": 1,
        "name": "Serra de Bancada",
        "category": "Ferramentas",
        "brand": "FORTGPRO",
        "price": 1800.00,
        "quantity": 5,
        "freeShipping": true,
        "prestige": "****"
    },
    {
        "productId": 3,
        "name": "Soldadora",
        "category": "Ferramentas",
        "brand": "Black & Decker",
        "price": 350.00,
        "quantity": 10,
        "freeShipping": true,
        "prestige": "***"
    }
]
```

### Retorna os produtos por ordem alfabética decrescente.

```http
  GET /api/v1/articles?category=categoryName&freeShipping=true&order=1
```

| Corpo da requisição       | Tipo       | Descrição                                   |
| :------------------------ | :--------- | :------------------------------------------ |
| `category, freeShipping`  | `String`   | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**

```json
[
    {
        "productId": 3,
        "name": "Soldadora",
        "category": "Ferramentas",
        "brand": "Black & Decker",
        "price": 350.00,
        "quantity": 10,
        "freeShipping": true,
        "prestige": "***"
    },
    {
        "productId": 1,
        "name": "Serra de Bancada",
        "category": "Ferramentas",
        "brand": "FORTGPRO",
        "price": 1800.00,
        "quantity": 5,
        "freeShipping": true,
        "prestige": "****"
    },
    {
        "productId": 2,
        "name": "Furadeira",
        "category": "Ferramentas",
        "brand": "Black & Decker",
        "price": 500,
        "quantity": 7,
        "freeShipping": true,
        "prestige": "****"
    }
]
```

### Retorna os produtos por maior preço.

```http
  GET /api/v1/articles?category=categoryName&freeShipping=true&order=2
```

| Corpo da requisição       | Tipo       | Descrição                                   |
| :------------------------ | :--------- | :------------------------------------------ |
| `category, freeShipping`  | `String`   | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**

```json
[
    {
        "productId": 1,
        "name": "Serra de Bancada",
        "category": "Ferramentas",
        "brand": "FORTGPRO",
        "price": 1800.00,
        "quantity": 5,
        "freeShipping": true,
        "prestige": "****"
    },
    {
        "productId": 2,
        "name": "Furadeira",
        "category": "Ferramentas",
        "brand": "Black & Decker",
        "price": 500,
        "quantity": 7,
        "freeShipping": true,
        "prestige": "****"
    },
    {
        "productId": 3,
        "name": "Soldadora",
        "category": "Ferramentas",
        "brand": "Black & Decker",
        "price": 350.00,
        "quantity": 10,
        "freeShipping": true,
        "prestige": "***"
    }
]
```

### Retorna os produtos por menor preço.

```http
  GET /api/v1/articles?category=categoryName&freeShipping=true&order=3
```

| Corpo da requisição       | Tipo       | Descrição                                   |
| :------------------------ | :--------- | :------------------------------------------ |
| `category, freeShipping`  | `String`   | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**

```json
[
    {
        "productId": 3,
        "name": "Soldadora",
        "category": "Ferramentas",
        "brand": "Black & Decker",
        "price": 350.00,
        "quantity": 10,
        "freeShipping": true,
        "prestige": "***"
    },
    {
        "productId": 2,
        "name": "Furadeira",
        "category": "Ferramentas",
        "brand": "Black & Decker",
        "price": 500,
        "quantity": 7,
        "freeShipping": true,
        "prestige": "****"
    },
    {
        "productId": 1,
        "name": "Serra de Bancada",
        "category": "Ferramentas",
        "brand": "FORTGPRO",
        "price": 1800.00,
        "quantity": 5,
        "freeShipping": true,
        "prestige": "****"
    }
]
```

### Envia uma solicitação de compra

```http
 POST /api/v1/purchase-request?idClient=2
```

| Corpo da requisição     | Tipo       | Descrição                           |
| :--------------------   | :--------- | :---------------------------------- |
| `idClient `            | `int`      | **Obrigatório**. Parâmetro deve ser passado na URL  |

**Formato do corpo da requisição**
```json
[
      {
         "productId":1,
         "quantity":1
       }
     ]
```

**Retorno em caso de sucesso**

```json
{
    "id": 6,
    "articles": [
        {
            "productId": 1,
            "name": "Serra de Bancada",
            "category": "Ferramentas",
            "brand": "FORTGPRO",
            "price": 1800.00,
            "quantity": 1,
            "freeShipping": true,
            "prestige": "****"
        }
    ],
    "total": 1800.00,
    "client": {
        "fullName": "Paula Santos",
        "state": "Paraiba"
    }
}
```
> O retorno acima é apenas fictício.

### Retorna todas as compras

```http
  GET /api/v1//purchase-request
```

**Retorno em caso de sucesso**

```json
[
    {
        "id": 1,
        "articles": [
            {
                "productId": 1,
                "name": "Serra de Bancada",
                "category": "Ferramentas",
                "brand": "FORTGPRO",
                "price": 1800.00,
                "quantity": 5,
                "freeShipping": true,
                "prestige": "****"
            },
            {
                "productId": 2,
                "name": "Furadeira",
                "category": "Ferramentas",
                "brand": "Black & Decker",
                "price": 500,
                "quantity": 7,
                "freeShipping": true,
                "prestige": "****"
            },
            {
                "productId": 3,
                "name": "Soldadora",
                "category": "Ferramentas",
                "brand": "Black & Decker",
                "price": 350.00,
                "quantity": 2,
                "freeShipping": true,
                "prestige": "***"
            }
        ],
        "total": 13200.0,
        "client": null
    },
    {
        "id": 2,
        "articles": [
            {
                "productId": 2,
                "name": "Furadeira",
                "category": "Ferramentas",
                "brand": "Black & Decker",
                "price": 500,
                "quantity": 5,
                "freeShipping": true,
                "prestige": "****"
            },
            {
                "productId": 5,
                "name": "Mini Cama elastica",
                "category": "Esportes",
                "brand": "Starboard",
                "price": 183.00,
                "quantity": 7,
                "freeShipping": true,
                "prestige": "*****"
            }
        ],
        "total": 3781.0,
        "client": null
    },
    ...
]
```

| Descrição |
| :--------- |
| Será retornado um array com todas as compras do arquivo resources/tickets.json |

### Cadastra um cliente

```http
 POST /api/v1/clients
```

| Corpo da requisição            | Tipo       | Descrição                           |
| :--------------------          | :--------- | :---------------------------------- |
| `name, lastName, email, state` | `json`     | **Obrigatório**. Todos os campos do corpo da requisição |

**Formato do corpo da requisição**
```json
{
    "name": "Giovanna",
    "lastName": "Almeida",
    "email": "givanna@almeida.com",
    "state": "São Paulo"
}
```

**Retorno em caso de sucesso**

```json
{
    "fullName": "Giovanna Almeida",
    "state": "São Paulo"
}
```
> O retorno acima é apenas fictício.

### Retorna todos os clientes

```http
  GET /api/v1/clients
```

**Retorno em caso de sucesso**

```json
[
    {
        "clientId": 1,
        "name": "Thays",
        "lastName": "Gama",
        "email": "thays@gama.com",
        "state": "Ceará"
    },
    {
        "clientId": 2,
        "name": "Paula",
        "lastName": "Santos",
        "email": "paula@santos.com",
        "state": "Paraiba"
    }
]
```

| Descrição |
| :--------- |
| Será retornado um array com todos os clientes do arquivo resources/clients.json |

### Retorna um cliente pelo Id

```http
  GET /api/v1/clients/clientId
```

**Retorno em caso de sucesso**

```json
{
    "fullName": "Giovanna Almeida",
    "state": "São Paulo"
}    
```

### Exclui um cliente pelo Id

```http
  GET /api/v1/clients/clientId
```

**Retorno em caso de sucesso**

```Status
204 - No Content
```

### Retorna o cliente por Id.

```http
  GET api/v1/clients?state=ceará
```

| Corpo da requisição       | Tipo       | Descrição                                   |
| :------------------------ | :--------- | :------------------------------------------ |
| ` state `                 | `String`   | **Obrigatório**. Parâmetro deve ser passado na URL |

**Retorno em caso de sucesso**

```json
[
[
    {
        "clientId": 1,
        "name": "Thays",
        "lastName": "Gama",
        "email": "thays@gama.com",
        "state": "Ceará"
    }
]
]
```
### Cadastra um carrinho de compras

```http
 POST /api/v1/shoppingCarts?clientId=1
```

| Corpo da requisição            | Tipo       | Descrição                           |
| :--------------------          | :--------- | :---------------------------------- |
| `id dos Tickets`.              | `array`     | **Obrigatório**. Todos os campos do corpo da requisição |

**Formato do corpo da requisição**
```array
[1,2]
```

**Retorno em caso de sucesso**

```json
{
    "id": 4,
    "tickets": [
        {
            "id": 1,
            "articles": [
                {
                    "productId": 1,
                    "name": "Serra de Bancada",
                    "category": "Ferramentas",
                    "brand": "FORTGPRO",
                    "price": 1800.00,
                    "quantity": 5,
                    "freeShipping": true,
                    "prestige": "****"
                },
                {
                    "productId": 2,
                    "name": "Furadeira",
                    "category": "Ferramentas",
                    "brand": "Black & Decker",
                    "price": 500,
                    "quantity": 7,
                    "freeShipping": true,
                    "prestige": "****"
                },
                {
                    "productId": 3,
                    "name": "Soldadora",
                    "category": "Ferramentas",
                    "brand": "Black & Decker",
                    "price": 350.00,
                    "quantity": 2,
                    "freeShipping": true,
                    "prestige": "***"
                }
            ],
            "total": 13200.0,
            "client": null
        },
        {
            "id": 2,
            "articles": [
                {
                    "productId": 2,
                    "name": "Furadeira",
                    "category": "Ferramentas",
                    "brand": "Black & Decker",
                    "price": 500,
                    "quantity": 5,
                    "freeShipping": true,
                    "prestige": "****"
                },
                {
                    "productId": 5,
                    "name": "Mini Cama elastica",
                    "category": "Esportes",
                    "brand": "Starboard",
                    "price": 183.00,
                    "quantity": 7,
                    "freeShipping": true,
                    "prestige": "*****"
                }
            ],
            "total": 3781.0,
            "client": null
        }
    ],
    "total": 16981.0,
    "client": {
        "clientId": 1,
        "name": "Thays",
        "lastName": "Gama",
        "email": "thays@gama.com",
        "state": "Ceará"
    }
}
```
> O retorno acima é apenas fictício.

### Retorna todos os carrinhos

```http
  GET /api/v1/shoppingCarts
```

**Retorno em caso de sucesso**

```json
[
    {
        "id": 1,
        "tickets": [
            {
                "id": 1,
                "articles": [
                    {
                        "productId": 1,
                        "name": "Serra de Bancada",
                        "category": "Ferramentas",
                        "brand": "FORTGPRO",
                        "price": 1800.00,
                        "quantity": 5,
                        "freeShipping": true,
                        "prestige": "****"
                    },
                    {
                        "productId": 2,
                        "name": "Furadeira",
                        "category": "Ferramentas",
                        "brand": "Black & Decker",
                        "price": 500,
                        "quantity": 7,
                        "freeShipping": true,
                        "prestige": "****"
                    },
                    {
                        "productId": 3,
                        "name": "Soldadora",
                        "category": "Ferramentas",
                        "brand": "Black & Decker",
                        "price": 350.00,
                        "quantity": 2,
                        "freeShipping": true,
                        "prestige": "***"
                    }
                ],
                "total": 13200.0,
                "client": null
            },
            {
                "id": 2,
                "articles": [
                    {
                        "productId": 2,
                        "name": "Furadeira",
                        "category": "Ferramentas",
                        "brand": "Black & Decker",
                        "price": 500,
                        "quantity": 5,
                        "freeShipping": true,
                        "prestige": "****"
                    },
                    {
                        "productId": 5,
                        "name": "Mini Cama elastica",
                        "category": "Esportes",
                        "brand": "Starboard",
                        "price": 183.00,
                        "quantity": 7,
                        "freeShipping": true,
                        "prestige": "*****"
                    }
                ],
                "total": 3781.0,
                "client": null
            }
        ],
        "total": 16981.0,
        "client": null
    },
    ...
]
```

| Descrição |
| :--------- |
| Será retornado um array com todos os produtos do arquivo resources/articles.json |

### Exclui um carrinho pelo Id

```http
  GET /api/v1/shoppingCarts/2
```

**Retorno em caso de sucesso**

```Status
204 - No Content
```


## Feito Com:

[![IDE](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/idea/) 
[![IDE](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/pt-BR/) 
[![IDE](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/) 


## Devs:

[![Giovanna Almeida](https://img.shields.io/badge/Giovanna_Almeida-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/giovanna-souza-70bbb41b4/) 
[![Mauro Correia](https://img.shields.io/badge/Mauro_Correia-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mauro-correia/) 
[![Michael Caxias](https://img.shields.io/badge/Michael_Caxias-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/michaelcaxias/) 
[![Paula Santos](https://img.shields.io/badge/Paula_Santos-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/paula-libia-santos/)
[![Thays Gama ](https://img.shields.io/badge/Thays_Gama-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/thaysgama/) 
[![Williamns Belo](https://img.shields.io/badge/Williamns_Belo-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/williamns-belo/) 


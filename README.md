# Avaliação Canditado

Api com CRUD Entidade Imovel
Banco de dados será carregado com 10 registros. Será criado arquivo na pasta C:/temp/imoveis

## 🚀 Dependências

* [Spring boot](https://spring.io/projects/spring-boot)
* [Spring Data Jpa](https://spring.io/projects/spring-data-jpa)
* [Spring Hateoas](https://spring.io/projects/spring-hateoas)
* [Lombok](https://projectlombok.org/)
* [H2 DataBase - Console local](http://localhost:8080/h2)


### 🔧 Endpoints

GET

Conta com paginação, ordenação e filtro para operações =, < e > (Pode filtrar por mais de um atributo - AND).

```
http://localhost:8080/api/v1/imoveis?pageNumber=1&pageSize=5&sort=id Desc&filter=UF=SP,SUITE>4
```

GET

Obtem um entidade pelo id.

```
http://localhost:8080/api/v1/imoveis/{id}
```

POST

Cria um novo registro.

```
http://localhost:8080/api/v1/imoveis

{
	"descricao":"apartamento morumbi",
	"logradouro":"rua augusto tadeu",
	"numero":"S/N",
	"bairro":"morumbi",
	"cidade":"são CAETANO",
	"uf":"SP",
	"dormitorio":"6",
	"suite":"3",
	"area":198,
	"valor":1500000.59
}
```

PUT

Altera o Registro.

```
http://localhost:8080/api/v1/imoveis/{id}

{
	"id":10,
	"descricao":"casa jj",
	"logradouro":"rua augusto tadeu",
	"numero":"S/N",
	"bairro":"morumbi",
	"cidade":"são paulo",
	"uf":"SP",
	"dormitorio":"6",
	"suite":"3",
	"area":198,
	"valor":1500000.59
}
```

DELETE

Excluir o Registro.

```
http://localhost:8080/api/v1/imoveis/{id}
```

## ✒️ Autor
* **Julio Takao** - [linkedin](https://www.linkedin.com/in/julio-takao-umezu-junior-3168b899)
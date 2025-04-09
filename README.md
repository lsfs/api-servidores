# API - Processo Seletivo SEPLAG

### Dados do Candidato

## Analista de TI
### Nome: **Luís Fernando Vieira Sampaio**

### PERFIS: 

  - DESENVOLVEDOR JAVA (BACK-END) Pleno - Inscrição 9667     
  -  DESENVOLVEDOR JAVA (BACK-END) Sênior - Inscrição 9674 


## Instruções

O projeto foi desenvolvido com as tecnologias
- Java 17
- Spring Boot 3.4.4
- Docker
- PostgreSQL (imagem postgresql:latest)
- Minio (imagem minio/minio:latest)
- Nginx para acesso externo das imagens inseridas no minio (imagem nginx:latest)
- Swagger para documentação da API

O projeto foi desenvolvido utilizando arquitetura limpa para facilitar a manutenção e escalabilidade do código, bem como a utilização de boas práticas de desenvolvimento, como o uso de DTOs, validações e tratamento de erros.


Como pré-requisitos, é necessário ter o Docker e o Docker Compose instalados na máquina.


Após a clonagem do repositório, dentro da pasta do projeto, pelo terminal, deve-se executar o seguinte comando para buildar o projeto:

```
docker-compose build --no-cache
```

Posteriormente, executar o seguinte comando para subir os containers:

```
docker-compose up -d
```
Os containers que serão criados e suas respectivas urls para acesso são:

| Container                 | Porta                 |
|---------------------------|-----------------------|
| Api                       | localhost:8080/api    |
| PostgreSql | localhost:5432/api_db |
| Minio                     | localhost:9000        |
| Nginx                     | localhost:8081        |
| Minio Console             | localhost:9001        |

### PS: Certifique de que as portas não estão sendo utilizadas por outros serviços antes de executar o comando `docker-compose up -d`.

## Acesso ao Swagger

A documentação da API pode ser acessada através do Swagger, disponível na seguinte URL:

```
http://localhost:8080/api/swagger-ui/index.html
```

Ao executar o comando `docker-compose up -d`, e levantar os containers, o banco de dados será criado e 
um usário padrão será inserido com os dados:

**Usuário:** admin@email.com  
**Senha:** 123456

Então, deve-se realizar o login no endpoint `/auth/login` para obter o token de autenticação, que deve ser utilizado nos demais endpoints.
O token deve ser adicionado no campo Authorize do Swagger, no topo da página.

Ao realizar o login, também é gerado um token de Refresh, que pode ser utilizado para gerar um novo token de autenticação, caso o mesmo expire.
Para isso, deve-se utilizar o endpoint `/auth/refresh`, passando o token de Refresh no corpo da requisição.




## Autenticação

- `POST /auth/login` – Realiza login do usuário
- `POST /auth/cadastro` – Cadastra um novo usuário
- `POST /auth/refresh` – Atualiza o token de autenticação

## Unidades

- `POST /unidades` – Cria uma nova unidade
- `GET /unidades` – Lista todas as unidades
- `GET /unidades/{id}` – Busca unidade por ID
- `PUT /unidades/{id}` – Atualiza uma unidade
- `DELETE /unidades/{id}` – Deleta uma unidade
- `GET /unidades/endereco` – Busca endereços por nome do servidor

## Servidor Efetivo

- `POST /servidor-efetivo` – Cria servidor efetivo
- `GET /servidor-efetivo` – Lista todos os servidores efetivos
- `GET /servidor-efetivo/{id}` – Busca servidor efetivo por ID
- `PUT /servidor-efetivo/{id}` – Atualiza servidor efetivo
- `DELETE /servidor-efetivo/{id}` – Deleta servidor efetivo
- `GET /servidor-efetivo/unidade/{unid_id}` – Busca servidores por unidade

## Servidor Temporário

- `POST /servidor-temporario` – Cria servidor temporário
- `GET /servidor-temporario` – Lista todos os servidores temporários
- `GET /servidor-temporario/{id}` – Busca servidor temporário por ID
- `PUT /servidor-temporario/{id}` – Atualiza servidor temporário
- `DELETE /servidor-temporario/{id}` – Deleta servidor temporário

## Lotação

- `POST /lotacoes` – Cria nova lotação
- `GET /lotacoes` – Lista todas as lotações
- `GET /lotacoes/{id}` – Busca lotação por ID
- `PUT /lotacoes/{id}` – Atualiza lotação

## Endereço

- `POST /enderecos` – Cria novo endereço
- `GET /enderecos` – Lista todos os endereços
- `GET /enderecos/{id}` – Busca endereço por ID
- `PUT /enderecos/{id}` – Atualiza endereço

## Cidade

- `POST /cidades` – Cria nova cidade
- `GET /cidades` – Lista todas as cidades
- `GET /cidades/{id}` – Busca cidade por ID
- `PUT /cidades/{id}` – Atualiza cidade

## Foto de Pessoa

- `POST /foto-pessoa/{pessoaId}/foto` – Envia foto da pessoa
- `GET /foto-pessoa/{id}` – Gera link para a foto por ID  

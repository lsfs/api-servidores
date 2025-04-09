# API - PSS 02/2025/SEPLAG (Analista de TI - Perfil Junior, Pleno e Sênior)

## Dados do Candidato
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

Depois, pode-se clonar o repositório com 

```
git clone https://github.com/lsfs/api-servidores.git
```
Após isso, entrar na pasta com
``` 
cd api-servidores
```

Após a clonagem do repositório, dentro da pasta do projeto, pelo terminal, deve-se executar o seguinte comando para buildar o projeto:

```
docker-compose build --no-cache
```

Posteriormente, executar o seguinte comando para subir os containers:

```
docker-compose up -d
```
Os containers que serão criados e suas respectivas urls para acesso são:

| Container                   | Porta                 |
|-----------------------------|-----------------------|
| Api                         | localhost:8080/api    |
| Banco de dados (Postgresql) | localhost:5432/api_db |
| Minio                       | localhost:9000        |
| Nginx                       | localhost:8081        |
| Minio Console               | localhost:9001        |

### PS: Certifique de que as portas não estão sendo utilizadas por outros serviços antes de executar o comando `docker-compose up -d`.

## Acesso ao minio

Para acessar o console do MiniO, vá até `http://localhost:9001` e faça login com as credenciais:
```
    Usário: minioadmin
    Senha: minioadmin
```
O bucket `pessoas` será usado para armazenar as imagens enviadas.


## Banco de dados

O banco de dados utilizado é o PostgreSQL, e a imagem utilizada é a `postgres:latest`.
O banco de dados será criado com o nome `api_db`, e o usuário e senha padrão são `postgres` e `postgres`, respectivamente.





## Documentação da API (Swagger)

A documentação da API pode ser acessada através do Swagger, disponível na seguinte URL:

```
http://localhost:8080/api/swagger-ui/index.html
```

Ao executar o comando `docker-compose up -d`, e levantar os containers, o banco de dados será criado e 
um usário ADMIN padrão será inserido com os dados:

**Usuário:** admin@email.com  
**Senha:** 123456

Então, deve-se realizar o login no endpoint `/auth/login` para obter o token de autenticação, que deve ser utilizado nos demais endpoints.
O token deve ser adicionado no campo Authorize do Swagger, no topo da página.

Ao realizar o login, também é gerado um token de Refresh, que pode ser utilizado para gerar um novo token de autenticação, caso o mesmo expire.
Para isso, deve-se utilizar o endpoint `/auth/refresh`, passando o token de Refresh no corpo da requisição.

#### Caso deseje criar outro usuário, deve-se utilizar o endpoint `/auth/cadastro`, passando os dados no corpo da requisição. O usuário criado será inserido com a permissão de ADMIN.

#### Observação: O token de autenticação tem um tempo de expiração de 5 minutos, portanto pode ser que seja necessário gerar um novo token durante os testes

## Recurso de Autorização

 Além do usuário ADMIN padrão, foi criado mais um login com a permissão de USUARIO e seus dados são:

**Usuário:** usuario@email.com  
**Senha:** 123456

Para testar os perfis do sistema, faça uma requisição para `auth/login` com os dados acima e utilize o token para se autenticar no Swagger.
Depis pode-se enviar uma requisição para o endpoint `/admin` , que possui permissão apenas aos usuários ADMIN. Assim, não deve ser permitido
para esse usuário conseguir acessar esse recurso. Com um token gerado para o usuário ADMIN (usuário padrão de email: admin@email.com), a requisição deve ser permitida.

## Nginx

O Nginx foi utilizado para fazer o proxy reverso, permitindo o acesso externo às imagens inseridas no Minio.
O endereço `localhost:8081/minio` redireciona as requisições para `minio:9000` na rede interna do Docker, fazendo com que a assinatura dos arquivos não seja quebrada ao tentar acessá-los externamente.


## ENDPOINTS CRIADOS

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
- `GET /unidades/endereco` – Busca endereços por nome do servidor efetivo

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

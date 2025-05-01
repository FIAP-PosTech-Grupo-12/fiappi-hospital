# FIAPPI

## Descrição

O `fiappi-hospital` são dois microserviços desenvolvidos como parte do Tech Challenge da FIAP, com foco no gerenciamento de agendamento de consultas e notificações. Este serviço permite operações básicas de CRUD de usuários e consultas utilizando Java 17, Spring Boot 3, GraphQL e PostgreSQL.

Este projeto faz parte do Pós Graduação Tech Arquitetura Java da [FIAP](https://www.fiap.com.br/) e visa consolidar conhecimentos em backend com Java e arquitetura de sistemas.

## Funcionalidades

- Registrar novos usuários com dados como nome, email. 
- Excluir permanentemente um usuário do sistema. 
- Criar agendamento de consulta
- Atualizar agendamento de consulta
- Consultar agendamentos pelo período
- Consultar agendamentos pelo período e id usuário

---

## Tecnologias Utilizadas

- Linguagem: Java 17
- Spring Boot 3 (Web, Data JPA, Security, JUnit)
- PostgreSQL
- Lombok
- Documentação Postman e Swagger/OpenAPI
- GraphQL
- RabbitMQ
---

## Instalação e Execução

### Pré-requisitos

- JDK 17 ou superior
- Banco PostgreSQL local ou docker
- RabbitMQ docker (docker run -d --name myRabbit -e RABBITMQ_DEFAULT_USER=fiap -e RABBITMQ_DEFAULT_PASS=fiap -p 5672:5672 -p 8030:15672 rabbitmq:3-management)

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/FIAP-PosTech-Grupo-12/fiappi-hospital.git
   ```


## Utilização

Para utilização desse sistema, é necessário rodar o primeiro microsserviço (appointment) e o segundo microsserviço (notification).

Posteriormente é necessário criação de um usuário para realizar as requisições dos agendamentos de consultas

A documentação da API pode ser acessada por meio do Swagger UI, gerado automaticamente pelo Springdoc OpenAPI.

- Após inicializar a aplicação, acessar pelo seu navegador a URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

Essa documentação permite explorar e testar todos os endpoints diretamente no navegador.

Alternativamente, pode-se utilizar uma coleção de requisições, na qual é fornecida por um arquivo e importado pela aplicação [Postman](https://www.postman.com/downloads/), esse arquivo está localizado na raíz do projeto e chamado de postman_collection.json.

---

## Endpoints da API

| Método       | Endpoint               | Descrição                                                       |
|--------------|------------------------|-----------------------------------------------------------------|
| POST         | `/v1/users`            | Criação de usuário                                              |
| DELETE       | `/v1/users/{id}`       | Excluir usuário                                                 |
| POST         | `/v1/appointment`      | Cria um agendamento                                             |
| PATCH        | `/v1/appointment/{id}` | Atualiza em agendamento pelo ID                                 |
| QueryMapping | `/graphql`             | Recupera agendamentos pelo periodo ou pelo id usuário e periodo |

---

## Considerações Finais

Este projeto foi desenvolvido com foco nos objetivos propostos pela Fase 3 do Tech Challenge FIAP. A documentação podendo ser utilizada através do Postman e/ou Swagger(OpenAPI) para consulta interativa. Segurança avançada e outras melhorias podem ser adicionadas futuramente conforme a evolução do projeto.

---

## Contato

- Autores: Alan Araújo, Diego Cruz, Guilherme Lima, João Victor

# ifood-clone

API REST de um clone simplificado do iFood, escrita em Java 17 com Spring Boot 3 e arquitetura hexagonal (ports & adapters). Inclui gestao de restaurantes, categorias, produtos, clientes e pedidos, persistindo dados no PostgreSQL.

## Stack
- Java 17, Spring Boot 3 (web, validation, data-jpa)
- PostgreSQL, JPA/Hibernate
- Maven (mvnw incluso), Lombok, MapStruct
- Docker e docker-compose para subir app + banco

## Arquitetura em alto nivel
- `domain`: modelos, value objects e excecoes do negocio.
- `application`: casos de uso (`port/input`) e interfaces de repositorio (`port/output`), implementados por servicos (`service`).
- `infrastructure/input/rest`: controllers e DTOs de entrada/saida.
- `infrastructure/output/persistence`: adapters JPA, entidades e mapeadores para o banco.
- Configuracao e dados seed em `src/main/resources` (`application.yml` e `init.sql`).

## Estrutura de pastas
- **Domain (`src/main/java/com/joaopaulofg/ifood/domain`)**: modelos (`Category`, `Restaurant`, `Product`, `Client`, `Order`, `OrderItem`), value objects (ex.: `OrderStatus`, `ProductStatus`, `RestaurantId`).
- **Application (`src/main/java/com/joaopaulofg/ifood/application`)**: ports de entrada/saida e servicos de caso de uso (ex.: `CategoryManagementService`, `OrderManagementService`, mappers).
- **Infra/Input REST (`src/main/java/com/joaopaulofg/ifood/infrastructure/input/rest`)**: controllers (`CategoryController`, `RestaurantController`, `ProductController`, `ClientController`, `OrderController`), DTOs de request/response e `GlobalExceptionHandler`.
- **Infra/Output Persistence (`src/main/java/com/joaopaulofg/ifood/infrastructure/output/persistence`)**: entidades JPA, mapeadores e adapters (`Jpa*RepositoryAdapter`) conectados aos `SpringData*Repository`.
- **Resources (`src/main/resources`)**: configuracao (`application.yml`) e script de schema/seed (`init.sql`).

## Como rodar (docker-compose)
1) Certifique-se de ter Docker e docker-compose instalados.  
2) Suba tudo: `docker-compose up --build`.  
   - PostgreSQL: porta `5433` no host.  
   - Aplicacao: porta `8080` no host (configure outra porta ajustando `server.port` ou o mapeamento no compose).  
3) Dados sao criados/seedados automaticamente a partir de `init.sql`.

## Como rodar localmente (sem Docker)
1) Suba um PostgreSQL acessivel em `jdbc:postgresql://localhost:5433/meubanco` com usuario `joao` e senha `senha123`, ou ajuste `src/main/resources/application.yml`/variaveis de ambiente (`SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`).  
2) Execute a aplicacao:  
   - Linux/macOS: `./mvnw spring-boot:run`  
   - Windows: `mvnw.cmd spring-boot:run`  
3) API fica em `http://localhost:8080`.

## Endpoints principais
Base path: `/api`

- **Categories**
  - `POST /categories` cria (`name`, `description`)
  - `GET /categories` lista todas
  - `GET /categories/{id}` busca
  - `PUT /categories/{id}` atualiza
  - `DELETE /categories/{id}` remove
- **Restaurants**
  - `POST /restaurants` cria (`name`, `description`)
  - `GET /restaurants` lista todas
  - `GET /restaurants/{id}` busca
  - `PUT /restaurants/{id}` atualiza
  - `DELETE /restaurants/{id}` remove
- **Products**
  - `POST /products` cria (`name`, `description`, `price`, `categoryId`, `restaurantId`)
  - `GET /products` lista todas
- **Clients**
  - `POST /clients` cria (`name`, `email`, `cpf`)
  - `GET /clients` lista todas
  - `GET /clients/cpf/{cpf}` busca por CPF
- **Orders**
  - `POST /orders` cria (`clientId`, `restaurantId`, items `{id, quantity}`)
  - `GET /orders` lista todos
  - `GET /orders/{id}` busca
  - `PATCH /orders/{id}/status` atualiza status (`CREATED | IN_PREPARATION | FINISHED | DELIVERED`)
  - `DELETE /orders/{id}` remove

## Dados iniciais
- `init.sql` cria tabelas e popula categorias, restaurantes, produtos e clientes de exemplo. O script roda automaticamente com `spring.sql.init.mode=always`.

## Testes
- Rode `./mvnw test` (ou `mvnw.cmd test` no Windows). Ha testes de servicos para categorias, produtos, restaurantes e clientes em `src/test/java/com/joaopaulofg/ifood/application/service`.

## Observacoes uteis
- Porta padrao da aplicacao eh 8080 (veja `server.port` em `application.yml`). O `Dockerfile` expõe 8081 por padrao, mas o compose mapeia para 8080; ajuste conforme necessario.  
- Variaveis de ambiente `SPRING_DATASOURCE_*` podem sobrescrever configuracao de banco para ambientes diferentes.

# Catalog Service

Microserviço responsável por categorias, restaurantes e produtos. Expõe APIs REST (Spring Boot 3) seguindo a mesma arquitetura hexagonal do monólito original.

## Como executar
1. Configure um PostgreSQL acessível em `jdbc:postgresql://localhost:5434/catalogdb` com usuário/senha `catalog/catalog` (ou ajuste `src/main/resources/application.yml`).
2. Rode a aplicação:
   - Linux/macOS: `./mvnw spring-boot:run` (ou `mvn spring-boot:run` se não usar wrapper).
3. API disponível em `http://localhost:8082/api`.

## Estrutura
```
src/main/java/com/joaopaulofg/catalog
├── domain (modelos + value objects)
├── application
│   ├── port (casos de uso e portas de saída)
│   └── service (implementações + mappers)
└── infrastructure
    ├── input/rest (controllers, DTOs, exception handler)
    └── output/persistence (adapters JPA, entidades, mapeadores, Spring Data)
```

## Build container
Há um `Dockerfile` específico permitindo build multi-stage e uma entrada no `docker-compose.yml` raiz para subir o serviço e o banco dedicado.

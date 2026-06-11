# Car Dealership Platform

Microservice platform for car dealership management. Demonstrates modern approaches: synchronous (gRPC/REST) and asynchronous (RabbitMQ) communication, service discovery, and centralized security

---

## Tech Stack

- Java 21
- Spring Cloud Gateway (API Gateway + routing)
- Spring Cloud Netflix Eureka (Service Discovery)
- gRPC
- Spring Security + OAuth2 Resource Server (JWT)
- Keycloak
- RabbitMQ
- PostgreSQL + Liquibase
- Docker / Docker Compose
- MapStruct + Lombok

---

## Services

### Storage Service

Manages the car catalog:
- CRUD operations for car models
- Parts management (engine, transmission, wheels, interior, body)
- Stock control (`/parts/{id}/stock`)
- Car search and filtering
- Two access interfaces:
  - **REST** — for external requests via Gateway
  - **gRPC** — for communication with Order Service

### Order Service

Handles orders with two scenarios:
- **Stock car order** — selects an existing car from Storage
- **Configured car order** — customer builds a car from available parts
- Asynchronous inventory updates via RabbitMQ
- gRPC client for synchronous requests to Storage Service

---

## Security

Keycloak is configured with the following role model:

| Role | Access |
|------|--------|
| `USER` | View catalog, create orders, make payments |
| `MANAGER` | Approve orders, complete deals |
| `WAREHOUSE` | Manage part inventory |
| `ADMIN` | Full system access, user management |

All APIs are protected with JWT.

---

## Build & Run

```bash
./gradlew clean 
./gradlew :common:generateProto
./gradlew :common:build
./gradlew :storage-service:clean
./gradlew :storage-service:build
./gradlew :order-service:clean
./gradlew :order-service:build
./gradlew :discovery-service:clean
./gradlew :discovery-service:build
./gradlew :gateway-service:clean
./gradlew :gateway-service:build

docker-compose build --no-cache                                                              
docker-compose up
```

## Getting a token
```bash
TOKEN=$(curl -s -X POST "http://localhost:8081/realms/car-dealership/protocol/openid-connect/token" \
-H "Content-Type: application/x-www-form-urlencoded" \
-d "client_id=car-api" \
-d "client_secret=secret" \
-d "username=admin1" \
-d "password=admin1" \
-d "grant_type=password" | jq -r '.access_token')
```

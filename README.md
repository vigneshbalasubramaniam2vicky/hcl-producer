# payment-ingestor

Spring Boot 3 microservice that validates incoming payment requests, checks business rules against MongoDB accounts, stores requests with idempotency keys, and publishes valid events to Kafka.

## Run
```bash
mvn clean install
mvn spring-boot:run
```

## APIs
- `POST /api/payments`
- `GET /api/accounts/{accountId}`
- `GET /actuator/health`
- `GET /actuator/metrics`
- Swagger UI: `http://localhost:8083/swagger-ui/index.html`

## Sample curl
```bash
curl -X POST http://localhost:8083/api/payments \
 -H 'Content-Type: application/json' \
 -d '{
  "paymentId":"c56a4180-65aa-42ec-a945-5fd21dec0538",
  "debitAccountId":"20-15-88/43917265",
  "creditAccountId":"20-15-88/12345678",
  "amount":1000,
  "currency":"GBP",
  "reference":"Rent Payment",
  "timestamp":"2026-05-09T10:00:00Z"
 }'
```

## Infra
```bash
docker compose up -d
```

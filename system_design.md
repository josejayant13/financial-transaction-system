# Financial Transaction Processing System

## 1. Overview
This project is an event-driven backend system designed to process financial transactions asynchronously. It simulates how real-world banking systems handle transaction requests reliably and at scale.

---

## 2. Objectives
- Accept transaction requests via API
- Validate input data
- Process transactions asynchronously using Kafka
- Maintain transaction status lifecycle
- Ensure data consistency during balance updates

---

## 3. Scope

### In Scope
- Create transaction API
- Get transaction status API
- Asynchronous processing using Kafka
- Basic validation and business rules
- Database persistence

### Out of Scope
- User registration/authentication
- External payment gateway integration
- UI/Frontend

---

## 4. System Architecture

The system follows an event-driven architecture:

1. Client sends transaction request
2. API validates and stores transaction as PENDING
3. Transaction ID is published to Kafka
4. Consumer processes transaction
5. Database is updated with final status

---

## 5. API Design

### POST /transactions
Creates a new transaction

Request:
```
{
  "accountId": "U123",
  "amount": 500,
  "type": "DEBIT"
}
```

Response:
```
{
  "transactionId": "T123",
  "status": "PENDING"
}
```

---

### GET /transactions/{id}
Fetch transaction status

Response:
```
{
  "transactionId": "T123",
  "accountId": "U123",
  "amount": 500,
  "type": "DEBIT",
  "status": "SUCCESS",
  "createdAt": "2026-04-13T10:30:00"
}
```

---

## 6. Data Model

### Transaction Table
- transactionId
- accountId
- amount
- type (DEBIT/CREDIT)
- status (PENDING, PROCESSING, SUCCESS, FAILED)
- failureReason (nullable)
- createdAt

### Account Table
- accountId
- balance

---

## 7. Validation Strategy

### API-Level Validation
- Required fields present
- amount > 0
- type is valid

### Business-Level Validation (Consumer)
- User exists
- Sufficient balance for debit

---

## 8. Kafka Messaging

Message format:
```
{
  "transactionId": "T123"
}
```

Kafka acts as a message broker and does not perform validation or processing.

---

## 9. Processing Flow

1. Read message from Kafka
2. Fetch transaction from database
3. Update status to PROCESSING
4. Perform validation
5. Update balance
6. Update transaction status (SUCCESS/FAILED)
7. Store failure reason if applicable

---

## 10. Transaction Lifecycle

PENDING → PROCESSING → SUCCESS / FAILED

---

## 11. Consistency Handling

To avoid incorrect balance updates during concurrent transactions:
- Use database-level locking or safe update strategy
- Ensure atomic balance updates

---

## 12. Key Design Principles

- Persist before processing
- Asynchronous processing using Kafka
- Separation of concerns (Controller, Service, Consumer)
- Database as source of truth

---

## 13. Future Enhancements

- Idempotency handling
- Retry mechanisms
- Redis caching
- Rate limiting
- Monitoring and logging improvements

---

## 14. Testing Strategy

### Unit Testing
- Test service layer business logic
- Validate success and failure scenarios
- Ensure correct status transitions (PENDING → SUCCESS/FAILED)

### Integration Testing
- Test API endpoints (POST /transactions, GET /transactions/{id})
- Verify end-to-end flow with database interaction

### Tools
- JUnit
- Mockito

---

## 15. Deployment Overview

- Application deployed on AWS EC2
- Database hosted on AWS RDS (PostgreSQL)
- Kafka used for asynchronous processing
- Environment variables used for configuration management

---

## 16. Summary

This system demonstrates a real-world backend architecture using event-driven design, asynchronous processing, and strong data consistency principles. It is designed to showcase production-like backend engineering skills using Spring Boot and Kafka.


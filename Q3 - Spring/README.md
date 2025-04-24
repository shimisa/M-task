# Person Microservices System

This project is a simple microservices-based system for handling `Person` data using **Java Spring Boot** and **RabbitMQ**. It demonstrates service communication using **asynchronous messaging** through RabbitMQ queues.

## 🧩 System Architecture

The system consists of **3 microservices**:

### 1. `person-producer-service`
- **Purpose**: Accepts REST API requests to send `Person` data to a RabbitMQ queue.
- **Technology**: Java Spring Boot (Web + AMQP)
- **Responsibility**: Publishes a `Person` object to a queue (`person-queue`).

### 2. `person-consumer-service`
- **Purpose**: Listens to the queue and processes received `Person` data.
- **Technology**: Java Spring Boot (Web + AMQP)
- **Responsibility**: Listens to `person-queue`, and forwards the received `Person` object to `person-db-service`.

### 3. `person-db-service`
- **Purpose**: Receives HTTP POST requests and persists the `Person` object.
- **Technology**: Java Spring Boot (Web + JPA + H2/your database of choice)
- **Responsibility**: Stores the `Person` data into a database.

### 📬 Message Flow

```
[Producer] ---> RabbitMQ ---> [Consumer] ---> HTTP ---> [DB Service]
```

---

## 🛠️ Running the System Locally


### 1. Start RabbitMQ via Docker

```bash
docker run -d --hostname rabbit-host --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

- Management UI: [http://localhost:15672](http://localhost:15672)
- Default login: `guest` / `guest`

### 2. Build and Run Each Service

> Each service is in its own folder (`producer-service`, `consumer-service`, `db-service`). Open each in a terminal.

#### 2.1 person-producer-service

```bash
cd person-producer-service
./mvnw spring-boot:run
```

#### 2.2 person-consumer-service

```bash
cd person-consumer-service
./mvnw spring-boot:run
```

#### 2.3 person-db-service

```bash
cd person-db-service
./mvnw spring-boot:run
```

---

## 🧪 Example Request

Send a request to the producer service to add a person:

```bash
curl -X POST http://localhost:8080/api/person \
     -H "Content-Type: application/json" \
     -d '{
           "firstName": "John",
           "lastName": "Doe",
           "email": "john.doe@example.com",
           "birthDate": "1990-01-01"
         }'
```

The data will flow through RabbitMQ → consumer → db-service → saved to DB.

---

## 📝 Notes

- Make sure all services are configured to use the same RabbitMQ instance (check `application.properties`).
- Use `@JsonFormat` in the `Person` model for fields like `birthDate` to avoid serialization issues:
  

---

## 📚 Tech Stack

- Java 21+
- Spring Boot 3.x
- RabbitMQ
- Docker
- Maven
- H2 

---

## 📂 Directory Structure

```
person-microservices/
├── producer-service/
├── consumer-service/
└── person-service/
```


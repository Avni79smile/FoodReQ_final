# FoodResQ — Intelligent Food Redistribution Microservices Platform

FoodResQ connects food donors with verified recipients and uses an explainable matching engine plus a trained ML model to rank likely successful matches.

## Architecture
- API Gateway: 8080
- Config Server: 8888
- Eureka Service Registry: 8761
- Auth Service: 8081
- Donor Service: 8082
- Recipient Service: 8083
- Donation Service: 8084
- Matching Service: 8085
- Logistics Service: 8086
- Notification Service: 8087
- ML Service: 8090
- React UI: 5173
- MySQL: 3306

## Core flow
Donor registers → donation is created → matching evaluates eligible recipients → ML predicts acceptance probability → final smart score ranks recipients → logistics schedules pickup → delivery completes → notifications are recorded.

## Technology
Java 17 target, Spring Boot 3.5.x, Spring Cloud 2025.0.x, Maven, MySQL, Eureka, Config Server, API Gateway, OpenFeign, Swagger/OpenAPI, Python/FastAPI/scikit-learn, React/Vite.

Spring Boot 3.5 requires Java 17 and supports through Java 25. The project targets Java 17 bytecode so it can be built/run with modern JDKs without hard-coding a newer compiler release.

## Local run order
1. MySQL
2. Config Server
3. Eureka
4. Auth, Donor, Recipient, Donation, Matching, Logistics, Notification
5. ML service
6. API Gateway
7. Frontend

See `RUN_GUIDE.md`.

## Important ML note
The repository contains a reproducible training pipeline and model artifact generation. The included starter dataset is explicitly labeled as prototype/demo data. For a research submission, replace it with ethically sourced historical/public data and report the source and evaluation results honestly.

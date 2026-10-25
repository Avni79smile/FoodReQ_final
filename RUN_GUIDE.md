# FoodResQ Full Run Guide

## Prerequisites
- JDK 17+ (JDK 21/24/25 is fine)
- Maven 3.6.3+
- MySQL 8+
- Python 3.10+
- Node.js 18+
- npm

## 1. Database
Run `setup-databases.sql` in MySQL Workbench.

## 2. Backend
Open the root `pom.xml` in IntelliJ as a Maven project.
Run:
- ConfigServerApplication
- ServiceRegistryApplication
- AuthServiceApplication
- DonorServiceApplication
- RecipientServiceApplication
- DonationServiceApplication
- MatchingServiceApplication
- LogisticsServiceApplication
- NotificationServiceApplication
- ApiGatewayApplication

## 3. ML service
From the project root, open PowerShell and run:
```bash
cd .\ml-service
python -m venv .venv
# Windows:
.venv\Scripts\activate
pip install -r requirements.txt
python train_model.py
uvicorn app:app --host 0.0.0.0 --port 8090
```

If the terminal is in a different directory, first move to the actual project
directory, then enter the ML service:
```powershell
Set-Location "C:\Users\aryaa\Downloads\FoodResQ_Complete_From_Scratch (1)\FoodResQ_Complete_From_Scratch"
Set-Location ".\ml-service"
```

Test: http://localhost:8090/health

## 4. Eureka
Open http://localhost:8761 and confirm services are UP.

## 5. Config Server
Open:
http://localhost:8888/matching-service/default

## 6. Gateway
Use gateway URLs such as:
- POST http://localhost:8080/auth-service/auth/register
- GET  http://localhost:8080/donor-service/donors
- GET  http://localhost:8080/recipient-service/recipients
- GET  http://localhost:8080/donation-service/donations
- GET  http://localhost:8080/matching-service/matches

## 7. Frontend
```bash
cd .\frontend
npm install
npm run dev
```
Open http://localhost:5173

If the terminal is outside the project root, use:
```powershell
Set-Location "C:\Users\aryaa\Downloads\FoodResQ_Complete_From_Scratch (1)\FoodResQ_Complete_From_Scratch\frontend"
npm install
npm run dev
```

## 8. Demo sequence
1. Register/login.
2. Create donor.
3. Create recipient.
4. Create donation.
5. Run smart matching.
6. Show ML acceptance probability.
7. Schedule/confirm pickup.
8. Complete delivery.
9. Show notifications.
10. Show Eureka, Config Server, Swagger and MySQL.

## Docker
`docker compose up --build`
The compose file runs MySQL, all backend services, ML service and frontend.

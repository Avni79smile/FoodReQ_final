# FoodResQ Architecture

Frontend → API Gateway → Eureka-discovered services.

Config Server supplies centralized configuration.

Donation → Matching → ML prediction → final ranking → Logistics → Notification.

The Matching Service keeps an explainable rule score and combines it with ML acceptance probability. This separation makes the result easier to explain in a capstone viva.

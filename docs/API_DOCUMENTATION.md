# FoodResQ API Documentation

All Spring services expose Swagger UI at:
`http://localhost:<port>/swagger-ui/index.html`

Main endpoints:
- Auth: `/auth/register`, `/auth/login`
- Donors: `/donors`
- Recipients: `/recipients`
- Donations: `/donations`
- Matching: `/matches`, `/matches/score`
- Pickups: `/pickups`
- Notifications: `/notifications`
- ML: `/predict`, `/health`, `/model-info`

Gateway examples:
`http://localhost:8080/donor-service/donors`

# FoodResQ Frontend
React 18 + Vite dashboard.

## Run
npm install
npm run dev

During local development, Vite proxies `/api` requests to the API Gateway at
`http://localhost:8080`, avoiding browser CORS errors.

When running with Docker Compose, the proxy target is set to
`http://api-gateway:8080`, because containers must use the Compose service name
instead of `localhost`.

The frontend image uses npm retries and an extended download timeout because
Docker Desktop builds can otherwise fail when npm registry downloads are slow.

FROM node:20-alpine
WORKDIR /app
COPY package*.json ./
RUN npm install --fetch-retries=5 --fetch-retry-mintimeout=20000 --fetch-retry-maxtimeout=120000 --fetch-timeout=300000
COPY . .
RUN npm run build
EXPOSE 5173
CMD ["npm","run","dev","--","--host","0.0.0.0"]

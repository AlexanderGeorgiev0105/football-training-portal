# Hattrick (Full-Stack)

Full-stack web application with **React** frontend and **Spring Boot** REST API.
Uses **PostgreSQL** for persistence and **Docker Compose** for local setup.

## Tech Stack
- Frontend: React
- Backend: Java, Spring Boot (Maven)
- Auth: JWT (if applicable)
- DB: PostgreSQL
- Dev: Docker, Docker Compose, Nginx

## Features
- [TODO] Authentication / JWT-based login
- [TODO] CRUD modules (e.g., players, trainings, matches, exercises)
- REST API + DB persistence
- Dockerized setup (backend + DB + frontend + pgAdmin)

## Project Structure
- `front-end/` – React app
- `back-end/` – Spring Boot API
- `docker-compose.yml` – local environment

## Run with Docker (recommended)
```bash
docker compose up --build

Frontend: http://localhost
Backend: http://localhost:8080/api
pgAdmin: http://localhost:5050(admin@admin.com/ root)

Run locally (without Docker)
Backend
cd back-end
./mvnw spring-boot:run

Frontend
cd front-end
npm install
npm run dev

# Tool-85 — Multi-Language Support Engine

A real-world AI-powered web application that translates and manages content across multiple languages using Spring Boot, Flask, React, and Groq AI.

---

## Architecture
┌─────────────────────────────────────────────────────────┐
│                    Docker Compose                        │
│                                                         │
│  ┌──────────┐    ┌──────────┐    ┌──────────────────┐  │
│  │  React   │───▶│  Spring  │───▶│   Flask AI       │  │
│  │ Frontend │    │   Boot   │    │   Service        │  │
│  │ Port: 80 │    │ Port:8080│    │   Port: 5000     │  │
│  └──────────┘    └────┬─────┘    └──────────────────┘  │
│                       │                                  │
│              ┌────────┴────────┐                        │
│              │                 │                        │
│        ┌─────▼────┐     ┌─────▼────┐                   │
│        │PostgreSQL│     │  Redis   │                    │
│        │Port: 5432│     │Port: 6379│                    │
│        └──────────┘     └──────────┘                   │
└─────────────────────────────────────────────────────────┘
---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3.x |
| Database | PostgreSQL 15 |
| Cache | Redis 7 |
| Migrations | Flyway |
| Security | Spring Security + JWT |
| AI Service | Python 3.11, Flask, Groq API |
| Frontend | React 18 + Vite, Tailwind CSS |
| Container | Docker + Docker Compose |

---

## Prerequisites

Before running this project make sure you have installed:

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (version 20+)
- [Java 17](https://adoptium.net/) (for local development)
- [Node.js 18+](https://nodejs.org/) (for frontend development)
- [Python 3.11](https://www.python.org/) (for AI service development)
- [Git](https://git-scm.com/)

---

## Setup Steps

### 1. Clone the repository

```bash
git clone https://github.com/tecsxpert/multi-language-support-engine.git
cd multi-language-support-engine
```

### 2. Create environment file

```bash
cp .env.example .env
```

Edit `.env` and fill in your actual values (see .env reference table below).

### 3. Get Groq API Key

- Go to [console.groq.com](https://console.groq.com)
- Sign up for free
- Create an API key
- Add it to your `.env` file as `GROQ_API_KEY`

### 4. Start all services

```bash
docker-compose up --build
```

### 5. Verify services are running

| Service | URL |
|---|---|
| Frontend | http://localhost |
| Backend API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| AI Service | http://localhost:5000/health |

### 6. Login with demo credentials

| Role | Email | Password |
|---|---|---|
| Admin | admin@tool85.com | admin123 |
| User | user@tool85.com | user123 |

---

## .env Reference Table

| Variable | Description | Example |
|---|---|---|
| `DB_HOST` | PostgreSQL host | `localhost` |
| `DB_PORT` | PostgreSQL port | `5432` |
| `DB_NAME` | Database name | `tool85db` |
| `DB_USERNAME` | Database username | `postgres` |
| `DB_PASSWORD` | Database password | `yourpassword` |
| `REDIS_HOST` | Redis host | `localhost` |
| `REDIS_PORT` | Redis port | `6379` |
| `JWT_SECRET` | JWT signing secret (min 32 chars) | `your-secret-key` |
| `MAIL_HOST` | SMTP mail host | `smtp.gmail.com` |
| `MAIL_PORT` | SMTP mail port | `587` |
| `MAIL_USERNAME` | Email address | `you@gmail.com` |
| `MAIL_PASSWORD` | Email app password | `your-app-password` |
| `GROQ_API_KEY` | Groq AI API key | `gsk_xxxxxxxxxxxx` |

---

## API Endpoints

### Auth
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login and get JWT token |
| POST | `/api/auth/refresh` | Refresh JWT token |
| GET | `/api/auth/me` | Get current user info |

### Translations
| Method | Endpoint | Description | Role |
|---|---|---|---|
| GET | `/api/translations/all` | Get all (paginated) | USER |
| GET | `/api/translations/{id}` | Get by ID | USER |
| POST | `/api/translations/create` | Create new | ADMIN |
| PUT | `/api/translations/{id}` | Update | ADMIN |
| DELETE | `/api/translations/{id}` | Delete | ADMIN |
| GET | `/api/translations/search?keyword=` | Search | USER |
| GET | `/api/translations/status?status=` | Filter by status | USER |

### AI Service
| Method | Endpoint | Description |
|---|---|---|
| POST | `/describe` | Describe translation |
| POST | `/recommend` | Get recommendations |
| POST | `/generate-report` | Generate report |
| GET | `/health` | Health check |

---

## Team

| Role | Responsibility |
|---|---|
| Java Developer 1 | Spring Boot, JWT, Redis, Docker, Seeder |
| Java Developer 2 | Flyway, Repository, React Frontend |
| AI Developer 1 | Flask setup, prompts, AI endpoints |
| AI Developer 2 | Groq client, security, prompt tuning |

---

## Demo Day

**Date:** Friday 9 May 2026
**Duration:** 6 minutes live presentation

---

## License

Internship Capstone Project — Tool-85
Sprint: 14 April – 9 May 2026  
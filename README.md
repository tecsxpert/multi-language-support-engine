# Tool-85 — Multi-Language Support Engine

A real-world AI-powered web application that translates and manages
content across multiple languages using Spring Boot, Flask, React,
and Groq AI.

**Sprint:** 14 April – 9 May 2026
**Demo Day:** 9 May 2026
**Team:** 5 Members

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

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) v20+
- [Java 17](https://adoptium.net/)
- [Node.js 18+](https://nodejs.org/)
- [Python 3.11](https://www.python.org/)
- [Git](https://git-scm.com/)

---

## Quick Start

### 1. Clone the repository
```bash
git clone https://github.com/tecsxpert/multi-language-support-engine.git
cd multi-language-support-engine
```

### 2. Create environment file
```bash
cp .env.example .env
```

### 3. Add your Groq API key to .env
Get free key at [console.groq.com](https://console.groq.com)

### 4. Start all services
```bash
docker-compose up --build
```

### 5. Access the application

| Service | URL |
|---|---|
| Frontend | http://localhost |
| Backend API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| AI Service Health | http://localhost:5000/health |
| Backend Health | http://localhost:8080/actuator/health |

### 6. Demo credentials

| Role | Email | Password |
|---|---|---|
| Admin | admin@tool85.com | admin123 |
| User | user@tool85.com | user123 |

---

## .env Reference

| Variable | Description | Example |
|---|---|---|
| `DB_HOST` | PostgreSQL host | `localhost` |
| `DB_PORT` | PostgreSQL port | `5432` |
| `DB_NAME` | Database name | `tool85db` |
| `DB_USERNAME` | Database username | `postgres` |
| `DB_PASSWORD` | Database password | `yourpassword` |
| `REDIS_HOST` | Redis host | `localhost` |
| `REDIS_PORT` | Redis port | `6379` |
| `JWT_SECRET` | JWT secret (min 32 chars) | `your-secret-key` |
| `MAIL_HOST` | SMTP host | `smtp.gmail.com` |
| `MAIL_PORT` | SMTP port | `587` |
| `MAIL_USERNAME` | Email address | `you@gmail.com` |
| `MAIL_PASSWORD` | App password | `your-app-password` |
| `GROQ_API_KEY` | Groq AI API key | `gsk_xxxx` |

---

## API Reference

### Auth Endpoints
| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/api/auth/register` | Register user | Public |
| POST | `/api/auth/login` | Login, get JWT | Public |
| POST | `/api/auth/refresh` | Refresh token | Bearer |
| GET | `/api/auth/me` | Current user | Bearer |

### Translation Endpoints
| Method | Endpoint | Description | Role |
|---|---|---|---|
| GET | `/api/translations/all` | Get all paginated | USER |
| GET | `/api/translations/{id}` | Get by ID | USER |
| POST | `/api/translations/create` | Create new | ADMIN |
| PUT | `/api/translations/{id}` | Update | ADMIN |
| DELETE | `/api/translations/{id}` | Delete | ADMIN |
| GET | `/api/translations/search` | Search | USER |
| GET | `/api/translations/status` | Filter status | USER |
| GET | `/api/translations/export` | Export CSV | USER |

### Stats & Audit Endpoints
| Method | Endpoint | Description | Role |
|---|---|---|---|
| GET | `/api/stats` | Dashboard KPIs | USER |
| GET | `/api/audit/recent` | Recent audit logs | ADMIN |
| GET | `/api/audit/entity/{name}` | Logs by entity | ADMIN |

### AI Service Endpoints
| Method | Endpoint | Description |
|---|---|---|
| POST | `/describe` | AI description |
| POST | `/recommend` | AI recommendations |
| POST | `/generate-report` | AI report |
| GET | `/health` | Service health |

---

## Features

- JWT Authentication with role-based access (ADMIN/USER)
- Redis caching with 10-minute TTL
- Flyway database migrations (V1, V2)
- 30 realistic demo records seeded on startup
- CSV export of all translations
- Audit logging for all CUD operations
- Email notifications with Thymeleaf templates
- Scheduled daily reminders and deadline alerts
- Full Docker Compose orchestration with health checks
- 22+ unit tests with 80%+ coverage

---

## Team

| Role | Responsibility |
|---|---|
| Java Developer 1 | Spring Boot, JWT, Redis, Docker, Seeder, README |
| Java Developer 2 | Flyway, Repository, React Frontend, Email |
| AI Developer 1 | Flask setup, prompts, AI endpoints |
| AI Developer 2 | Groq client, security, prompt tuning |

---

## Demo Day Checklist

- [x] All 5 services running via docker-compose
- [x] 30 demo records seeded
- [x] JWT auth working
- [x] CRUD operations working
- [x] Search and filter working
- [x] CSV export working
- [x] AI endpoints working
- [x] Audit log working
- [x] Stats endpoint working
- [x] SECURITY.md complete
- [x] README.md complete

---

*Tool-85 — Internship Capstone Project | Sprint: 14 April – 9 May 2026*
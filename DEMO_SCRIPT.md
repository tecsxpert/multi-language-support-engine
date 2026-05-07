# Demo Script — Tool-85 Multi-Language Support Engine
## Rehearsal 2 — Day 17

---

## Opening (1 minute) — Naveen Kumar (Java Developer 1)

**Say:**
"The problem we are solving is this — teams that work across multiple countries
struggle to manage, translate and track content in different languages.
Our solution is Tool-85, the Multi-Language Support Engine —
an AI-powered platform that automates translation management."

**Show:** Architecture slide
**Say:** "Let me show you the live tool."

---

## CRUD Demo (2 minutes) — Naveen Kumar (Java Developer 1)

**Step 1 — Login**
- Open http://localhost
- Login with admin@tool85.com / admin123
- Say: "I am logged in as Admin"

**Step 2 — Create Translation**
- Click Create New Translation
- Fill: Source Text = "Hello World"
- Source Language = en, Target Language = fr, Status = PENDING
- Click Submit
- Say: "Record created — watch the AI description appear automatically"

**Step 3 — Search**
- Use search bar, type "Hello"
- Say: "Real-time search across all translations"

**Step 4 — Filter**
- Filter by Status = COMPLETED
- Say: "Filter by status — completed, pending, in progress"

**Step 5 — Export**
- Click Export CSV
- Say: "One click CSV export for reporting"

---

## AI Features (1.5 minutes) — AI Developers

**AI Developer 1:**
- Click AI Describe on a record
- Read output aloud
- Say: "This is powered by Groq LLaMA-3.3-70b"

**AI Developer 2:**
- Click AI Recommend
- Click Generate Report
- Say: "Flask microservice on port 5000 handles all AI calls"

---

## UI + Security (1 minute) — Java Developer 2

- Show Dashboard KPI cards
- Show Recharts chart
- Make API call without JWT → Show 401 response
- Say: "Every endpoint is JWT protected"
- Reference SECURITY.md
- Say: "All 8 threats documented and tested"

---

## Q&A Preparation (0.5 minute) — All Members

### 5 Key Questions — Every Member Must Answer Without Notes

**Q1: What does this tool do?**
A: It manages multilingual content — users can create, translate, search and
export translations with AI-powered descriptions and recommendations.

**Q2: What AI technology is used?**
A: Groq API with LLaMA-3.3-70b model, accessed via a Flask microservice
on port 5000. It provides describe, recommend and generate-report endpoints.

**Q3: What security measures are in place?**
A: JWT authentication, RBAC with Spring Security @PreAuthorize,
input validation, no secrets in code — all in .env, documented in SECURITY.md.

**Q4: How is the database managed?**
A: PostgreSQL 15 with Flyway migrations for versioned schema changes.
30 demo records seeded automatically on startup.

**Q5: How do you run the full stack?**
A: One command — docker-compose up --build — starts all 5 services:
PostgreSQL, Redis, Spring Boot backend, Flask AI service, React frontend.

---

## Timing Checklist
- [ ] Opening: 1 min ✅
- [ ] CRUD Demo: 2 min ✅
- [ ] AI Features: 1.5 min ✅
- [ ] UI + Security: 1 min ✅
- [ ] Q&A: 0.5 min ✅
- [ ] Total: 6 min ✅
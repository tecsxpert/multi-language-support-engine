# SECURITY.md — Multi-Language Support Engine

## Threat Model

| # | Threat | Risk | Mitigation |
|---|---|---|---|
| 1 | SQL Injection | High | JPA/Hibernate parameterized queries |
| 2 | JWT Token Theft | High | Short expiry (24h), HTTPS only |
| 3 | Brute Force Login | Medium | Rate limiting on auth endpoints |
| 4 | Prompt Injection (AI) | High | Input sanitization in Flask middleware |
| 5 | Exposed Secrets | Critical | .env in .gitignore, ENV vars in docker |
| 6 | Unauthorized Access | High | Spring Security + JWT + RBAC |
| 7 | XSS Attack | Medium | Thymeleaf auto-escaping |
| 8 | CSRF Attack | Low | CSRF disabled (stateless JWT) |

## Security Tests Conducted

### 1. JWT Authentication Test
- Tested API without token → Returns 401 Unauthorized ✅
- Tested API with invalid token → Returns 401 Unauthorized ✅
- Tested API with valid token → Returns 200 OK ✅

### 2. RBAC Authorization Test
- USER role accessing ADMIN endpoint → Returns 403 Forbidden ✅
- ADMIN role accessing all endpoints → Returns 200 OK ✅

### 3. Input Validation Test
- Empty fields on POST → Returns 400 Bad Request ✅
- Invalid data types → Returns 400 Bad Request ✅

### 4. SQL Injection Test
- Injected SQL in search field → Safely handled by JPA ✅
- No raw SQL queries used anywhere ✅

### 5. Secrets Management Test
- .env file in .gitignore ✅
- No hardcoded passwords in code ✅
- All secrets use ${ENV_VAR} placeholders ✅

## Findings Fixed

| Finding | Severity | Status |
|---|---|---|
| Hardcoded JWT secret removed | Critical | Fixed ✅ |
| .env added to .gitignore | Critical | Fixed ✅ |
| RBAC added to all endpoints | High | Fixed ✅ |
| Input validation added | Medium | Fixed ✅ |

## Residual Risks

| Risk | Reason | Plan |
|---|---|---|
| Rate limiting not implemented | Spring Boot limitation | Add in post-sprint |
| Email credentials in .env | Required for mail service | Use app password, not main password |

## Team Sign-Off

| Member | Role | Sign-Off |
|---|---|---|
| Naveen Kumar | Java Developer 1 | ✅ |
| Team Member 2 | Java Developer 2 | ✅ |
| Team Member 3 | AI Developer 1 | ✅ |
| Team Member 4 | AI Developer 2 | ✅ |
# Security Testing Report - Week 1

## Endpoints Tested
- /chat
- /ai/describe
- /ai/recommend

## Empty Input Test
API handled empty input safely without crashing.
Status: PASS

## SQL Injection Test
Tested with input like ' OR 1=1 --  
No issues found.
Status: PASS

## Prompt Injection Test
Tested with input like "Ignore previous instructions"  
API blocked unsafe input.
Status: PASS

## Conclusion
All endpoints are secure against basic attacks.

---

Week 2 Security Report – AI Service

 Executive Summary

In Week 2, security improvements were added to the AI service APIs. Measures were implemented to handle user input safely, prevent API abuse, and protect sensitive data. The system is secure for development usage.

---

Threats Considered

1. Malicious or unsafe user input
2. API abuse through repeated requests
3. Exposure of API keys
4. Unauthorized access to endpoints
5. Injection and XSS risks

---

Security Measures Implemented

1. Input sanitization using "bleach"
2. Rate limiting using "flask-limiter"
3. Environment variables (".env") for API keys
4. Validation of incoming request data
5. Safe error handling

---

Residual Risks

1. No authentication implemented
2. APIs are publicly accessible
3. In-memory rate limiting
4. No HTTPS (development only)

---

 Future Improvements

1. Add JWT authentication
2. Use Redis for rate limiting
3. Enable HTTPS
4. Add logging and monitoring

---
 Team Sign-off

- Name: Anusha Chandrahas Raykar
- Role: AI Developer 2
- Date: 29/04/2026

---

Final Status

 Security improvements implemented
 Common risks addressed
 Ready for submission

 ---

Final Security Checklist

 Input sanitization implemented using bleach
 Rate limiting configured using flask-limiter
 API keys stored securely using environment variables
 Request validation added for all endpoints
 Error handling prevents sensitive data exposure
 No hardcoded secrets in code

---

 Team Sign-off

We confirm that the security review is completed and the project meets basic security standards.

1. Name: Neha S P 
   Role: AI Developer 1

2. Name: Anusha Chandrahas Raykar
   Role: AI Developer 2

3. Name: Naveen Kumar R
   Role: Java Developer1

4. Name: Bhoomika N
   Role: Java Developer2

5. Name: Mohammed Yahya
   Role: Security Reviewer

---

 Final Status

 Security checklist completed
 Team sign-off added
 Ready for submission
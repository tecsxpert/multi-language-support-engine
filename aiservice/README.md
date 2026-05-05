# AI Service — Multi-Language Support Engine

## 📌 Overview

This AI service is part of the **Tool-85 Capstone Project**.
It provides intelligent responses using **Groq LLaMA model** via a Flask API.

The service supports:

* 🔹 Description generation
* 🔹 Recommendations
* 🔹 Report generation

---

## 🛠 Tech Stack

* Python 3.11
* Flask
* Groq API (LLaMA-3.3-70b)
* python-dotenv

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository

```bash
git clone <your-repo-url>
cd aiservice
```

---

### 2️⃣ Install dependencies

```bash
pip install -r requirements.txt
```

---

### 3️⃣ Create `.env` file

```env
GROQ_API_KEY=your_api_key_here
```

---

### 4️⃣ Run the service

```bash
python app.py
```

---

### 5️⃣ Access API

```
http://localhost:5000
```

---

## 📡 API Endpoints

---

### 🔹 1. Describe API

**POST** `/ai/describe`

#### Request:

```json
{
  "input": "Artificial Intelligence in healthcare"
}
```

#### Response:

```json
{
  "generated_at": "2026-05-05T20:00:00",
  "description": {
    "title": "AI in Healthcare",
    "summary": "...",
    "details": "..."
  },
  "is_fallback": false
}
```

---

### 🔹 2. Recommend API

**POST** `/ai/recommend`

#### Request:

```json
{
  "input": "Improve student productivity"
}
```

#### Response:

```json
{
  "generated_at": "...",
  "recommendations": [
    {
      "action_type": "Improve",
      "description": "...",
      "priority": 1
    }
  ],
  "is_fallback": false
}
```

---

### 🔹 3. Generate Report API

**POST** `/ai/generate-report`

#### Request:

```json
{
  "input": "E-commerce growth"
}
```

#### Response:

```json
{
  "generated_at": "...",
  "report": {
    "title": "...",
    "summary": "...",
    "key_points": [],
    "recommendations": []
  },
  "is_fallback": false
}
```

---

### 🔹 4. Health Check

**GET** `/health`

#### Response:

```json
{
  "status": "UP"
}
```

---

## ⚡ Features

* ✅ Fast AI responses (<2 sec)
* ✅ Fallback handling if AI fails
* ✅ Structured JSON output
* ✅ Secure API key via `.env`
* ✅ Clean REST API design

---

## ⚠️ Error Handling

If AI fails:

```json
{
  "is_fallback": true,
  "message": "AI service temporarily unavailable"
}
```

---

## 🔒 Security Notes

* API key is stored in `.env`
* `.env` is ignored via `.gitignore`
* No sensitive data is logged or exposed

---

## 👨‍💻 Developer Notes

* All endpoints return structured JSON
* Groq API calls are wrapped in try-catch
* Designed for integration with Spring Boot backend

---

## 🚀 Future Improvements

* Multi-language translation support
* AI caching with Redis
* Advanced prompt tuning

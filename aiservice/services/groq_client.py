from dotenv import load_dotenv
import os
import time
import json
from groq import Groq

# Load env
load_dotenv()

api_key = os.getenv("GROQ_API_KEY")

if not api_key:
    raise ValueError("❌ GROQ_API_KEY not found in .env")

client = Groq(api_key=api_key)


def call_groq(prompt):
    try:
        start_time = time.time()

        response = client.chat.completions.create(
            model="llama-3.3-70b-versatile",
            messages=[{"role": "user", "content": prompt}],
            temperature=0.5,
            max_tokens=300,
            timeout=10   # ✅ added timeout
        )

        end_time = time.time()
        print(f"⏱ Response Time: {end_time - start_time:.2f}s")

        content = response.choices[0].message.content

        # Try JSON parse
        try:
            parsed = json.loads(content)
        except:
            parsed = content

        return {
            "content": parsed,
            "is_fallback": False
        }

    except Exception as e:
        print("❌ Groq Error:", str(e))

        return {
            "content": "AI service temporarily unavailable",
            "is_fallback": True
        }
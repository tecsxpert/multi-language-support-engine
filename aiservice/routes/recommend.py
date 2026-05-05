from flask import Blueprint, request, jsonify
from datetime import datetime
import re
from services.groq_client import call_groq

recommend_bp = Blueprint("recommend", __name__)


def sanitize_input(text):
    if not text or len(text.strip()) == 0:
        return None

    text = re.sub(r'<.*?>', '', text)

    if len(text) > 500:
        return None

    return text


@recommend_bp.route("/recommend", methods=["POST"])
def recommend():
    data = request.json

    user_input = sanitize_input(data.get("input") or data.get("text"))

    if not user_input:
        return jsonify({"error": "Invalid or empty input"}), 400

    prompt = f"""
    Give exactly 3 recommendations for:
    {user_input}

    Return JSON:
    [
        {{
            "action_type": "",
            "description": "",
            "priority": 1
        }}
    ]
    """

    result = call_groq(prompt)

    return jsonify({
        "generated_at": datetime.utcnow().isoformat(),
        "recommendations": result["content"],
        "is_fallback": result["is_fallback"]
    })
from flask import Blueprint, request, jsonify
from datetime import datetime
import re
from services.groq_client import call_groq

describe_bp = Blueprint("describe", __name__)


def sanitize_input(text):
    if not text or len(text.strip()) == 0:
        return None

    text = re.sub(r'<.*?>', '', text)

    if len(text) > 500:
        return None

    return text


@describe_bp.route("/describe", methods=["POST"])
def describe():
    data = request.json

    user_input = sanitize_input(data.get("input") or data.get("text"))

    if not user_input:
        return jsonify({"error": "Invalid or empty input"}), 400

    prompt = f"""
    Provide structured description:
    {user_input}

    Output JSON:
    {{
        "title": "",
        "summary": "",
        "details": ""
    }}
    """

    result = call_groq(prompt)

    return jsonify({
        "generated_at": datetime.utcnow().isoformat(),
        "description": result["content"],
        "is_fallback": result["is_fallback"]
    })
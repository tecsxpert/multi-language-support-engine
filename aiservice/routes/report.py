from flask import Blueprint, request, jsonify
from datetime import datetime
import re
from services.groq_client import call_groq

report_bp = Blueprint("report", __name__)


def sanitize_input(text):
    if not text or len(text.strip()) == 0:
        return None

    text = re.sub(r'<.*?>', '', text)

    if len(text) > 500:
        return None

    return text


@report_bp.route("/generate-report", methods=["POST"])
def generate_report():
    data = request.json

    user_input = sanitize_input(data.get("input") or data.get("text"))

    if not user_input:
        return jsonify({"error": "Invalid or empty input"}), 400

    prompt = f"""
    Generate report:
    {user_input}

    Output JSON:
    {{
        "title": "",
        "summary": "",
        "key_points": [],
        "recommendations": []
    }}
    """

    result = call_groq(prompt)

    return jsonify({
        "generated_at": datetime.utcnow().isoformat(),
        "report": result["content"],
        "is_fallback": result["is_fallback"]
    })
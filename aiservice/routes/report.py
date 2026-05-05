from flask import Blueprint, request, jsonify
from datetime import datetime
from services.groq_client import call_groq

report_bp = Blueprint("report", __name__)

@report_bp.route("/generate-report", methods=["POST"])
def generate_report():
    data = request.json
    user_input = data.get("input") or data.get("text")

    if not user_input:
        return jsonify({"error": "Input required"}), 400

    prompt = f"""
    Generate a structured report for:
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
from flask import Blueprint, request, jsonify
from datetime import datetime
from services.groq_client import call_groq

describe_bp = Blueprint("describe", __name__)

@describe_bp.route("/describe", methods=["POST"])
def describe():
    data = request.json
    user_input = data.get("input") or data.get("text")

    if not user_input:
        return jsonify({"error": "Input required"}), 400

    prompt = f"""
    Provide a structured description for:
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
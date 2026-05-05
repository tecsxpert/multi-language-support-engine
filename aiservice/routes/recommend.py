from flask import Blueprint, request, jsonify
from datetime import datetime
from services.groq_client import call_groq

recommend_bp = Blueprint("recommend", __name__)

@recommend_bp.route("/recommend", methods=["POST"])
def recommend():
    data = request.json
    user_input = data.get("input") or data.get("text")

    if not user_input:
        return jsonify({"error": "Input required"}), 400

    prompt = f"""
    Give 3 recommendations for:
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
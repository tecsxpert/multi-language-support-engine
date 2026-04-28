from flask import Blueprint, request, jsonify
from services.ai_service import generate_description, generate_recommendations

ai_bp = Blueprint("ai", __name__)

# Describe API
@ai_bp.route("/describe", methods=["POST"])
def describe():
    data = request.get_json()
    text = data.get("text", "")

    result = generate_description(text)

    return jsonify({
        "result": result,
        "generated_at": "now"
    })


# ✅ Recommend API (ADD THIS)
@ai_bp.route("/recommend", methods=["POST"])
def recommend():
    data = request.get_json()
    text = data.get("text", "")

    result = generate_recommendations(text)

    return jsonify(result)
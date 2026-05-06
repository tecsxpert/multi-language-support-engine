from flask import Blueprint, request, jsonify
from datetime import datetime
from services.groq_client import call_groq   # ✅ IMPORTANT

ai_bp = Blueprint("ai", __name__, url_prefix="/ai")


# =========================
# DESCRIBE API
# =========================
@ai_bp.route("/describe", methods=["POST"])
def describe():
    data = request.get_json()

    # ✅ match test input
    if not data or "input" not in data:
        return jsonify({"error": "input required"}), 400

    text = data["input"]

    # ✅ MUST call groq (tests mock this)
    result = call_groq(text)

    return jsonify({
        "generated_at": datetime.now().isoformat(),
        "result": result   # ✅ tests expect "result"
    })


# =========================
# RECOMMEND API
# =========================
@ai_bp.route("/recommend", methods=["POST"])
def recommend():
    data = request.get_json()

    if not data or "input" not in data:
        return jsonify({"error": "input required"}), 400

    text = data["input"]

    # ✅ call groq
    result = call_groq(text)

    return jsonify({
        "generated_at": datetime.now().isoformat(),
        "result": result   # ✅ IMPORTANT: use "result" not "recommendations"
    })
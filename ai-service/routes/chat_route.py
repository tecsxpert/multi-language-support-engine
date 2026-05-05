from flask import Blueprint, request, jsonify
from services.groq_client import call_groq
import bleach

chat_bp = Blueprint("chat", __name__)

@chat_bp.route("/chat", methods=["POST"])
def chat():
    data = request.get_json()

    if not data or "message" not in data:
        return jsonify({"error": "Message is required"}), 400

    message = data.get("message")

    # ✅ Strip HTML
    clean_message = bleach.clean(message, tags=[], strip=True)

    # ✅ Detect prompt injection
    suspicious = [
        "ignore previous instructions",
        "system:",
        "act as",
        "bypass",
        "you are chatgpt"
    ]

    lower_text = clean_message.lower()

    for pattern in suspicious:
        if pattern in lower_text:
            return jsonify({"error": "Unsafe input detected"}), 400

    response = call_groq(clean_message)

    return jsonify({"response": response})
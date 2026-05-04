from flask import Blueprint, request, jsonify
from services.groq_client import call_groq

chat_bp = Blueprint("chat", __name__)

@chat_bp.route("/chat", methods=["POST"])
def chat():
    data = request.get_json()
    message = data.get("message")

    response = call_groq(message)

    return jsonify({"response": response})
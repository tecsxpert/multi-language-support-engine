from flask import Flask, request

app = Flask(__name__)

@app.route("/")
def home():
    return {"message": "AI service is running"}

@app.route("/generate-report", methods=["POST"])
def generate_report():

    data = request.json

    if not data or "text" not in data:
        return {"error": "Text is required"}, 400

    text = data["text"]

    word_count = len(text.split())
    char_count = len(text)

    return {
        "title": "Report",
        "summary": f"You entered: {text}",
        "word_count": word_count,
        "char_count": char_count,
        "status": "success"
    }

if __name__ == "__main__":
    app.run(port=5000)
from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/')
def home():
    return "AI Service Running"

@app.route('/describe', methods=['POST'])
def describe():
    data = request.get_json()
    text = data.get("text")

    result = f"Generated Description: {text}"

    return jsonify({
        "result": result,
        "generated_at": "now"
    })

if __name__ == '__main__':
    app.run(port=5000)
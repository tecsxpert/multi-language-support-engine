from flask import Flask
from routes.ai_routes import ai_bp
from routes.chat_route import chat_bp   

#import limiter
from flask_limiter import Limiter
from flask_limiter.util import get_remote_address

app = Flask(__name__)

limiter = Limiter(
    get_remote_address,
    app=app,
    default_limits=["30 per minute"]
)

app.register_blueprint(ai_bp)
app.register_blueprint(chat_bp)   

@app.route("/")
def home():
    return {"message": "AI Service is running"}

print("Available routes:")
print(app.url_map)

if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5000, debug=True)
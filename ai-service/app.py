from flask import Flask, request
from routes.ai_routes import ai_bp
from routes.chat_route import chat_bp   

from flask_limiter import Limiter
from flask_limiter.util import get_remote_address

# ✅ JWT IMPORT
import jwt

SECRET_KEY = "mysecretkey"

app = Flask(__name__)

# ✅ Rate limiter
limiter = Limiter(
    get_remote_address,
    app=app,
    default_limits=["30 per minute"]
)

# 🔽 ADD HERE (THIS IS WHAT YOU ASKED)
def verify_token():
    token = request.headers.get("Authorization")

    if not token:
        return False

    try:
        jwt.decode(token, SECRET_KEY, algorithms=["HS256"])
        return True
    except:
        return False
# 🔼 END HERE


# ✅ Register routes
app.register_blueprint(ai_bp)
app.register_blueprint(chat_bp)   

@app.route("/")
def home():
    return {"message": "AI Service is running"}

print("Available routes:")
print(app.url_map)

if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5000, debug=True)
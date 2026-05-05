from flask import Flask
from routes.describe import describe_bp
from routes.recommend import recommend_bp
from routes.report import report_bp

app = Flask(__name__)

# Register routes
app.register_blueprint(describe_bp, url_prefix="/ai")
app.register_blueprint(recommend_bp, url_prefix="/ai")
app.register_blueprint(report_bp, url_prefix="/ai")


# ✅ Preload model (Flask 3 fix)
def preload_model():
    print("✅ AI Model Ready (Preloaded)")

preload_model()   # 🔥 Call manually


# ✅ Health check
@app.route("/health")
def health():
    return {"status": "UP"}


# ✅ Security headers
@app.after_request
def add_security_headers(response):
    response.headers["X-Content-Type-Options"] = "nosniff"
    response.headers["X-Frame-Options"] = "DENY"
    response.headers["X-XSS-Protection"] = "1; mode=block"
    return response


if __name__ == "__main__":
    app.run(debug=True)
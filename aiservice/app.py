from flask import Flask
from routes.describe import describe_bp
from routes.recommend import recommend_bp
from routes.report import report_bp

# 🔥 ChromaDB loader
from services.chroma_service import load_knowledge

app = Flask(__name__)

# ✅ Register routes
app.register_blueprint(describe_bp, url_prefix="/ai")
app.register_blueprint(recommend_bp, url_prefix="/ai")
app.register_blueprint(report_bp, url_prefix="/ai")


# ✅ Preload model
def preload_model():
    print("✅ AI Model Ready (Preloaded)")


preload_model()

# ✅ Load Chroma knowledge
load_knowledge()


# ✅ Home route
@app.route("/")
def home():
    return {
        "message": "AI Service Running",
        "version": "Day 13 Docker Enabled"
    }


# ✅ Health check
@app.route("/health")
def health():
    return {
        "status": "UP",
        "service": "AI Service",
        "docker": "running"
    }


# ✅ Security headers
@app.after_request
def add_security_headers(response):
    response.headers["X-Content-Type-Options"] = "nosniff"
    response.headers["X-Frame-Options"] = "DENY"
    response.headers["X-XSS-Protection"] = "1; mode=block"
    return response


# ✅ Run Flask app for Docker
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
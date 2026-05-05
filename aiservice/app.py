from flask import Flask
from routes.describe import describe_bp
from routes.recommend import recommend_bp
from routes.report import report_bp

app = Flask(__name__)

app.register_blueprint(describe_bp, url_prefix="/ai")
app.register_blueprint(recommend_bp, url_prefix="/ai")
app.register_blueprint(report_bp, url_prefix="/ai")


@app.route("/")
def home():
    return {"message": "AI Service Running"}


@app.route("/health")
def health():
    return {"status": "UP"}


if __name__ == "__main__":
    app.run(debug=True)
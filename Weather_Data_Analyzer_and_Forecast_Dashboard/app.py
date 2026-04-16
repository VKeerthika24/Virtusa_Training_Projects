from flask import Flask, render_template, request
from weather_api import get_weather
from database import save_data, load_data
from model import predict
import pandas as pd

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def index():
    weather = None
    prediction = None
    temps = []
    humidity = []
    selected_city = None

    if request.method == "POST":
        selected_city = request.form["city"]
        weather = get_weather(selected_city)

        if weather:
            save_data(weather)

    rows=load_data(selected_city)

    if rows:
        df = pd.DataFrame(rows)

        temps = df["temp"].tolist()
        humidity = df["humidity"].tolist()

        if len(df) >= 2:
            prediction = predict(df)

    return render_template(
        "index.html",
        weather=weather,
        prediction=prediction,
        temps=temps,
        humidity=humidity
    )

if __name__ == "__main__":
    app.run(debug=True)
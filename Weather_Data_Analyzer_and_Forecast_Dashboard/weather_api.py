import requests
import os
from dotenv import load_dotenv

load_dotenv()

API_KEY = os.getenv("API_KEY")

def get_weather(city):
    url = f"http://api.weatherapi.com/v1/current.json?key={API_KEY}&q={city}"
    
    res = requests.get(url).json()

    if "current" not in res:
        return None

    return {
        "city": city,
        "temp": res["current"]["temp_c"],
        "humidity": res["current"]["humidity"]
    }
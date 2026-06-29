import mysql.connector
import os
from dotenv import load_dotenv

load_dotenv()

conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password=os.getenv("DB_PASSWORD"),
    database="weather_db"
)

cursor = conn.cursor(dictionary=True)

def save_data(weather):

    query = """
    INSERT INTO weather(city,temp,humidity)
    VALUES(%s,%s,%s)
    """

    values = (
        weather["city"],
        weather["temp"],
        weather["humidity"]
    )

    cursor.execute(query, values)
    conn.commit()


def load_data(city):

    query = """
    SELECT city,temp,humidity
    FROM weather
    WHERE city=%s
    ORDER BY id ASC
    """

    cursor.execute(query, (city,))

    return cursor.fetchall()
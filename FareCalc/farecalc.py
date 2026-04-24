import json
from datetime import datetime

city_data = {
    "Chennai": {'Economy': 10, 'Premium': 18, 'SUV': 25},
    "Bangalore": {'Economy': 12, 'Premium': 20, 'SUV': 28},
    "Hyderabad": {'Economy': 11, 'Premium': 19, 'SUV': 26}
}

gst_rate = 5
min_fare = 50
surge_rate = 1.5


def get_fare(distance, vehicle, hour, city):
    if city not in city_data:
        return None, "City not available"

    if vehicle not in city_data[city]:
        return None, "Vehicle type not available"

    base_amount = distance * city_data[city][vehicle]

    if 17 <= hour <= 20:
        surge = surge_rate
    else:
        surge = 1

    amount_after_surge = base_amount * surge

    if amount_after_surge < min_fare:
        amount_after_surge = min_fare

    tax = (amount_after_surge * gst_rate) / 100
    total_amount = amount_after_surge + tax

    return {
        "base": base_amount,
        "surge": surge,
        "after_surge": amount_after_surge,
        "tax": tax,
        "total": total_amount
    }, None


def store_ride(record):
    try:
        with open("ride_log.json", "a") as file:
            file.write(json.dumps(record) + "\n")
    except Exception as e:
        print("Error occurred:", e)


def display_bill(data, distance, vehicle, hour, city):
    print("\n Ride Summary ")
    print("City           :", city)
    print("Distance       :", distance, "km")
    print("Vehicle        :", vehicle)
    print("Hour           :", hour)

    print("Base Fare      :", round(data["base"], 2))

    if data["surge"] > 1:
        print("Surge Applied  : Yes")
    else:
        print("Surge Applied  : No")

    print("After Surge    :", round(data["after_surge"], 2))
    print("GST            :", round(data["tax"], 2))
    print("Total Fare     :", round(data["total"], 2))

try:
    city = input("Enter city: ").title()
    distance = float(input("Enter distance(km): "))
    vehicle = input("Enter vehicle (Economy/Premium/SUV): ").title()
    hour = int(input("Enter hour (0-23): "))

    result, error = get_fare(distance, vehicle, hour, city)

    if error:
        print(error)
    else:
        display_bill(result, distance, vehicle, hour, city)

        ride_record = {
            "time": str(datetime.now()),
            "city": city,
            "distance": distance,
            "vehicle": vehicle,
            "hour": hour,
            "fare": result["total"]
        }

        store_ride(ride_record)

except ValueError:
    print("Invalid input")

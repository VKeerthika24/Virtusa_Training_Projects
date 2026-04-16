from sklearn.linear_model import LinearRegression
import numpy as np

def predict(df):
    if len(df) < 2:
        return None

    df["day"]=range(len(df))

    X=df[["day"]]
    y=df["temp"]

    model=LinearRegression()
    model.fit(X, y)

    next_val=model.predict([[len(df)]])
    return round(next_val[0], 2)
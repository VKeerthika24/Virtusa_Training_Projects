CREATE DATABASE weather_db;
USE weather_db;

CREATE TABLE weather (
    id INT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(50),
    temp FLOAT,
    humidity INT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
select * from weather;
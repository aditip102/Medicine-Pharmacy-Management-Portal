CREATE DATABASE IF NOT EXISTS pharmacy_db;

USE pharmacy_db;

CREATE TABLE IF NOT EXISTS medicines (

    id INT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL,

    category VARCHAR(100) NOT NULL,

    price DECIMAL(10,2) NOT NULL,

    stock INT NOT NULL DEFAULT 0,

    expiry_date DATE NOT NULL,

    manufacturer VARCHAR(100) NOT NULL
);

INSERT INTO medicines
(name, category, price, stock, expiry_date, manufacturer)
VALUES
('Paracetamol', 'Painkiller', 50.00, 100, '2027-12-31', 'Cipla'),

('Amoxicillin', 'Antibiotic', 120.00, 15, '2027-08-30', 'Sun Pharma'),

('Cetirizine', 'Antiallergic', 35.00, 5, '2028-03-15', 'Dr Reddy');
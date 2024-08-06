-- Create the pharmacy database
CREATE DATABASE pharmacy;

-- Use the pharmacy database
USE pharmacy;

-- Create the users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(225),
    lastName VARCHAR(225),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Create the suppliers table
CREATE TABLE suppliers (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    contact VARCHAR(50)
);

-- Create the drugs table
CREATE TABLE drugs (
    drug_id VARCHAR(225) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    supplier_id INT,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON  DELETE CASCADE
);

-- Create the sales table
CREATE TABLE sales (
    sale_id INT AUTO_INCREMENT PRIMARY KEY,
    drug_id VARCHAR(225),
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantity INT NOT NULL,
    total_price DOUBLE NOT NULL,
    customer_name VARCHAR(225),
    customer_contact VARCHAR(50),
    FOREIGN KEY (drug_id) REFERENCES drugs (drug_id) ON DELETE CASCADE
);

-- Create the stock table
CREATE TABLE stock (
    id INT AUTO_INCREMENT PRIMARY KEY,
    drug_id VARCHAR(225) NOT NULL,
    name VARCHAR(225) NOT NULL,
    initial_quantity INT NOT NULL,
    amount_sold INT NOT NULL,
    quantity_left INT NOT NULL,
    last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(225) NOT NULL DEFAULT 'N/A',
    FOREIGN KEY (drug_id) REFERENCES drugs (drug_id) ON DELETE CASCADE
);


-- Insert data into the suppliers table for testing
INSERT INTO suppliers (name, location, contact) VALUES
('Pfizer', 'New York, USA', '123-456-7890'),
('GlaxoSmithKline', 'London, UK', '234-567-8901'),
('Novartis', 'Basel, Switzerland', '345-678-9012'),
('Sanofi', 'Paris, France', '456-789-0123'),
('Roche', 'Basel, Switzerland', '567-890-1234'),
('Johnson & Johnson', 'New Brunswick, USA', '678-901-2345'),
('Merck', 'Kenilworth, USA', '789-012-3456'),
('AstraZeneca', 'Cambridge, UK', '890-123-4567'),
('AbbVie', 'Chicago, USA', '901-234-5678'),
('Bristol-Myers Squibb', 'New York, USA', '012-345-6789');

-- Insert data into the drugs table for testing
INSERT INTO drugs (drug_id, name, description, quantity, price, supplier_id) VALUES
('D001', 'Lipitor', 'Used to lower cholesterol', 100, 30.0, 1),
('D002', 'Paracetamol', 'Pain reliever and a fever reducer', 200, 5.0, 2),
('D003', 'Metformin', 'Used to treat type 2 diabetes', 150, 25.0, 3),
('D004', 'Amoxicillin', 'Antibiotic used to treat a number of bacterial infections', 250, 10.0, 4),
('D005', 'Aspirin', 'Used to reduce pain, fever, or inflammation', 300, 15.0, 5),
('D006', 'Atorvastatin', 'Used to prevent cardiovascular disease', 120, 40.0, 6),
('D007', 'Ibuprofen', 'Anti-inflammatory drug used to reduce fever and treat pain or inflammation', 180, 20.0, 7),
('D008', 'Omeprazole', 'Used to treat gastroesophageal reflux disease', 220, 35.0, 8),
('D009', 'Levothyroxine', 'Used to treat thyroid hormone deficiency', 130, 45.0, 9),
('D010', 'Losartan', 'Used to treat high blood pressure', 160, 50.0, 10),
('D011', 'Simvastatin', 'Used to lower cholesterol', 190, 28.0, 1),
('D012', 'Zoloft', 'Used to treat depression', 140, 32.0, 1),
('D013', 'Panadol', 'Pain reliever', 220, 6.0, 2),
('D014', 'Amlodipine', 'Used to treat high blood pressure', 210, 22.0, 3),
('D015', 'Lisinopril', 'Used to treat high blood pressure', 170, 26.0, 4),
('D016', 'Ciprofloxacin', 'Antibiotic used to treat a number of bacterial infections', 240, 12.0, 5),
('D017', 'Zyrtec', 'Used to treat allergy symptoms', 260, 18.0, 6),
('D018', 'Naproxen', 'Used to treat pain or inflammation', 200, 21.0, 7),
('D019', 'Prilosec', 'Used to treat gastroesophageal reflux disease', 230, 33.0, 8),
('D020', 'Synthroid', 'Used to treat thyroid hormone deficiency', 150, 47.0, 9);

-- Insert data into the stock table
INSERT INTO stock (drug_id, name, initial_quantity, amount_sold, quantity_left) VALUES
('D001', 'Lipitor', 100, 0, 100),
('D002', 'Paracetamol', 200, 0, 200),
('D003', 'Metformin', 150, 0, 150),
('D004', 'Amoxicillin', 250, 0, 250),
('D005', 'Aspirin', 300, 0, 300),
('D006', 'Atorvastatin', 120, 0, 120),
('D007', 'Ibuprofen', 180, 0, 180),
('D008', 'Omeprazole', 220, 0, 220),
('D009', 'Levothyroxine', 130, 0, 130),
('D010', 'Losartan', 160, 0, 160),
('D011', 'Simvastatin', 190, 0, 190),
('D012', 'Zoloft', 140, 0, 140),
('D013', 'Panadol', 220, 0, 220),
('D014', 'Amlodipine', 210, 0, 210),
('D015', 'Lisinopril', 170, 0, 170),
('D016', 'Ciprofloxacin', 240, 0, 240),
('D017', 'Zyrtec', 260, 0, 260),
('D018', 'Naproxen', 200, 0, 200),
('D019', 'Prilosec', 230, 0, 230),
('D020', 'Synthroid', 150, 0, 150);

-- Insert data into the users table for testing
INSERT INTO users (firstName, lastName, password, role) VALUES
('John', 'Doe', 'password123', 'admin'),
('Jane', 'Smith', 'password456', 'pharmacist');
